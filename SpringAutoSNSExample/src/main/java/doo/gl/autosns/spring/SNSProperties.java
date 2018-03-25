package doo.gl.autosns.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SNSProperties {

    @Value("${auto-sns.access-key}")
    private String accessKey;

    @Value("${auto-sns.secret-key}")
    private String secretKey;

    @Value("${auto-sns.region}")
    private String region;

    @Value("${auto-sns.topic-prefix:}")
    private String topicPrefix;

    @Value("${auto-sns.subscription-endpoint}")
    private String subscriptionEndpoint;

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRegion() {
        return region;
    }

    public String getTopicPrefix() {
        return topicPrefix;
    }

    public String getSubscriptionEndpoint() {
        return subscriptionEndpoint;
    }
}
