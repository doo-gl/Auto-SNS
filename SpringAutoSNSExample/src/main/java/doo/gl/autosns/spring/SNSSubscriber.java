package doo.gl.autosns.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import doo.gl.autosns.AutoSNSClient;
import doo.gl.autosns.AutoSNSSubscriber;

public abstract class SNSSubscriber {

    private static final Logger logger = LoggerFactory.getLogger(SNSSubscriber.class);

    @Inject
    private AutoSNSClient autoSNSClient;

    @Inject
    private SNSProperties properties;

    private AutoSNSSubscriber autoSNSSubscriber;

    public abstract void onSubscribeFailure(RuntimeException ex);

    public abstract String getTopicName();

    public abstract void processNotification(String notification);

    void confirmSubscription(SubscriptionConfirmation confirmation) {
        logger.info("Confirming subscription: {}", confirmation);
        if (autoSNSSubscriber == null) {
            logger.warn("Received confirmation {} for subscription but subscriber has not been initialised", confirmation);
            return;
        }
        autoSNSSubscriber.confirmSubscription(confirmation.getToken());
    }

    void initialise() {

        try {
            String topicName = properties.getTopicPrefix() + getTopicName();
            autoSNSSubscriber = autoSNSClient.registerSubscriber(topicName, getEndpoint());
        } catch (RuntimeException ex) {
            onSubscribeFailure(ex);
        }

    }

    public Optional<String> getTopicArn() {
        if (autoSNSSubscriber == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(autoSNSSubscriber.getTopicArn());
    }

    private URL getEndpoint() {
        try {
            return new URL(properties.getSubscriptionEndpoint());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
