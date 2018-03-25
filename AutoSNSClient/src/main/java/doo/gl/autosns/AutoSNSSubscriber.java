package doo.gl.autosns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;

import java.net.URL;

public class AutoSNSSubscriber {

    private AmazonSNS amazonSNS;
    private String topicName;
    private URL endpoint;
    private String topicArn;
    private String subscriptionArn;

    public AutoSNSSubscriber(
        AmazonSNS amazonSNS,
        String topicName,
        URL endpoint,
        String topicArn,
        String subscriptionArn
    ) {
        this.amazonSNS = amazonSNS;
        this.topicName = topicName;
        this.endpoint = endpoint;
        this.topicArn = topicArn;
        this.subscriptionArn = subscriptionArn;
    }

    public String confirmSubscription(String token) {
        ConfirmSubscriptionResult result = amazonSNS.confirmSubscription(topicArn, token);
        return result.getSubscriptionArn();
    }

    public AmazonSNS getAmazonSNS() {
        return amazonSNS;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public String getSubscriptionArn() {
        return subscriptionArn;
    }

    public URL getEndpoint() {
        return endpoint;
    }
}
