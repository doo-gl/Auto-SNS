package doo.gl.autosns;

import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import doo.gl.autosns.AutoSNSClient;

public class AutoSNSClientFactory {

    public static AutoSNSClient create(AmazonSNSClientBuilder builder) {
        return new AutoSNSClient(
            builder.build()
        );
    }

}
