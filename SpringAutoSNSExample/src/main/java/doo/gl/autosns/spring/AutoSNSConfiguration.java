package doo.gl.autosns.spring;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

import doo.gl.autosns.AutoSNSClient;
import doo.gl.autosns.AutoSNSClientFactory;

@Configuration
public class AutoSNSConfiguration {

    @Inject
    private SNSProperties properties;

    @Bean
    public AutoSNSClient autoSNSClient() {
        return AutoSNSClientFactory.create(
            AmazonSNSClientBuilder.standard()
                .withCredentials(
                    new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        properties.getAccessKey(),
                        properties.getSecretKey()
                    ))
                )
                .withRegion(properties.getRegion())
        );
    }

}
