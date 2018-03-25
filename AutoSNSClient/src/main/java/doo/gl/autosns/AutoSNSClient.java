package doo.gl.autosns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.Topic;

import java.net.URL;
import java.util.Optional;
import java.util.function.Consumer;

public class AutoSNSClient {

    private AmazonSNS amazonSNS;

    AutoSNSClient(AmazonSNS amazonSNS) {
        this.amazonSNS = amazonSNS;
    }

    public AutoSNSPublisher registerPublisher(String topicName) throws FailedToCreateTopicException {

        String topicArn = getTopicArn(topicName)
            .orElseGet(() -> createTopic(topicName));

        return new AutoSNSPublisher(amazonSNS, topicArn, topicName);
    }

    public AutoSNSSubscriber registerSubscriber(String topicName, URL endpoint) throws FailedToCreateTopicException {

        validateEndpoint(endpoint);

        String topicArn = getTopicArn(topicName)
            .orElseGet(() -> createTopic(topicName));

        String subscriptionArn = getSubscriptionArn(topicArn, endpoint)
            .orElseGet(() -> createSubscription(topicArn, endpoint));

        return new AutoSNSSubscriber(amazonSNS, topicName, endpoint, topicArn, subscriptionArn);
    }

    private void validateEndpoint(URL endpoint) {
        String protocol = endpoint.getProtocol();

        if (!protocol.equals("http") && !protocol.equals("https")) {
            throw new FailedToRegisterSubscriberException(
                "Failed to register subscriber for endpoint: " + endpoint.toString() +
                    ", the protocol for the endpoint should be either http or https"
            );
        }
    }

    private void forEachTopic(Consumer<Topic> topicConsumer) {

        ListTopicsResult initialResult = amazonSNS.listTopics();
        initialResult.getTopics().forEach(topicConsumer);
        String nextToken = initialResult.getNextToken();

        while (nextToken != null) {
            ListTopicsResult result = amazonSNS.listTopics(nextToken);
            result.getTopics().forEach(topicConsumer);
            nextToken = result.getNextToken();
        }
    }

    private Optional<String> getTopicArn(String topicName) {
        final String[] matchingTopicArn = { null };
        forEachTopic(topic -> {
            // See https://docs.aws.amazon.com/general/latest/gr/aws-arns-and-namespaces.html#arn-syntax-sns
            // for SNS ARN format
            String[] splitArn = topic.getTopicArn().split(":");
            if (splitArn.length < 6) {
                return;
            }
            String name = splitArn[5];
            if (topicName.equals(name)) {
                matchingTopicArn[0] = topic.getTopicArn();
            }
        });
        return Optional.ofNullable(matchingTopicArn[0]);
    }

    private String createTopic(String topicName) {
        try {

            CreateTopicResult result = amazonSNS.createTopic(topicName);
            return result.getTopicArn();

        } catch (Exception ex) {

            throw new FailedToCreateTopicException(
                "Failed to create topic with name: " + topicName,
                ex
            );

        }
    }

    private Optional<String> getSubscriptionArn(String topicArn, URL endpoint) {
        final String[] subscriptionArn = { null };

        forEachSubscriptionInTopic(topicArn, subscription -> {
            if (subscription.getEndpoint().equals(endpoint.toString())) {
                subscriptionArn[0] = subscription.getSubscriptionArn();
            }
        });

        return Optional.ofNullable(subscriptionArn[0]);
    }

    private String createSubscription(String topicArn, URL endpoint) {

        try {

            SubscribeResult result = amazonSNS.subscribe(topicArn, endpoint.getProtocol(), endpoint.toString());
            return result.getSubscriptionArn();

        } catch (Exception ex) {
            throw new FailedToRegisterSubscriberException(
                "Failed to create subscription for topic arn: " + topicArn +
                    ", on endpoint: " + endpoint,
                ex
            );
        }

    }

    private void forEachSubscriptionInTopic(String topicArn, Consumer<Subscription> subscriptionConsumer) {

        ListSubscriptionsByTopicResult initialResult = amazonSNS.listSubscriptionsByTopic(topicArn);
        initialResult.getSubscriptions().forEach(subscriptionConsumer);
        String nextToken = initialResult.getNextToken();

        while (nextToken != null) {

            ListSubscriptionsByTopicResult result = amazonSNS.listSubscriptionsByTopic(topicArn, nextToken);
            result.getSubscriptions().forEach(subscriptionConsumer);
            nextToken = result.getNextToken();

        }
    }

}
