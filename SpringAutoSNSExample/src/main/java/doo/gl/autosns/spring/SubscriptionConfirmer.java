package doo.gl.autosns.spring;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

@Component
public class SubscriptionConfirmer {

    @Inject
    private List<SNSSubscriber> subscribers;

    public boolean confirm(SubscriptionConfirmation confirmation) {
        boolean processedSuccessfully = false;

        for (SNSSubscriber subscriber : subscribers) {

            Optional<String> topicArn = subscriber.getTopicArn();

            if (topicArn.isPresent() && topicArn.get().equals(confirmation.getTopicArn())) {
                subscriber.confirmSubscription(confirmation);
                processedSuccessfully = true;
            }

        }

        return processedSuccessfully;
    }

}
