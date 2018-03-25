package doo.gl.autosns.spring;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

@Component
public class SubscriptionNotifier {

    @Inject
    private List<SNSSubscriber> subscribers;

    public boolean notify(SubscriptionNotification notification) {

        boolean processedSuccessfully = false;

        for (SNSSubscriber subscriber : subscribers) {

            Optional<String> topicArn = subscriber.getTopicArn();

            if (topicArn.isPresent() && topicArn.get().equals(notification.getTopicArn())) {
                subscriber.processNotification(notification.getMessage());
                processedSuccessfully = true;
            }

        }

        return processedSuccessfully;

    }

}
