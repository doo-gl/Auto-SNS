package doo.gl.autosns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;

import com.amazonaws.services.sns.model.PublishResult;

public class AutoSNSPublisher {

    private AmazonSNS amazonSNS;
    private String topicArn;
    private String topicName;

    AutoSNSPublisher(AmazonSNS amazonSNS, String topicArn, String topicName) {
        this.amazonSNS = amazonSNS;
        this.topicArn = topicArn;
        this.topicName = topicName;
    }

    public AmazonSNS getAmazonSNS() {
        return amazonSNS;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public String getTopicName() {
        return topicName;
    }

    public String publish(String subject, String message) {
        PublishResult result = amazonSNS.publish(topicArn, message, subject);
        return result.getMessageId();
    }

    public String publish(String message) {
        PublishResult result = amazonSNS.publish(topicArn, message);
        return result.getMessageId();
    }

}
