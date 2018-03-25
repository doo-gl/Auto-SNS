package doo.gl.autosns.spring;

import javax.inject.Inject;

import java.util.Optional;

import doo.gl.autosns.AutoSNSClient;
import doo.gl.autosns.AutoSNSPublisher;

public abstract class SNSPublisher {

    @Inject
    private AutoSNSClient autoSNSClient;

    @Inject
    private SNSProperties properties;

    private AutoSNSPublisher autoSNSPublisher;

    protected abstract void onRegistrationFailure(RuntimeException ex);

    public abstract String getTopicName();

    public Optional<String> getTopicArn() {
        if (autoSNSPublisher == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(autoSNSPublisher.getTopicArn());
    }

    public void publish(String notification) {
        autoSNSPublisher.publish(notification);
    }

    void initialise() {
        try {
            String topicName = properties.getTopicPrefix() + getTopicName();
            autoSNSPublisher = autoSNSClient.registerPublisher(topicName);
        } catch (RuntimeException ex) {
            onRegistrationFailure(ex);
        }
    }

}
