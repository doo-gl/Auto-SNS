package doo.gl.autosns.spring;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.io.IOException;

@Component
public class SubscriptionProcessor {

    private static final String MESSAGE_TYPE_HEADER = "x-amz-sns-message-type";
    private static final String CONFIRMATION_TYPE = "SubscriptionConfirmation";
    private static final String NOTIFICATION_TYPE = "Notification";

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionProcessor.class);

    @Inject
    private SubscriptionConfirmer subscriptionConfirmer;

    @Inject
    private SubscriptionNotifier subscriptionNotifier;

    @Inject
    private ObjectMapper objectMapper;

    public boolean process(HttpHeaders httpHeaders, String body) {

        if (!httpHeaders.containsKey(MESSAGE_TYPE_HEADER)) {
            logger.warn(
                "Subscription request has come through without the aws message type header: {}, " +
                    "headers: {}, body {}", MESSAGE_TYPE_HEADER, httpHeaders, body
            );
            return false;
        }

        String messageType = httpHeaders.get(MESSAGE_TYPE_HEADER).get(0);

        try {
            switch (messageType) {

                case CONFIRMATION_TYPE:

                    SubscriptionConfirmation confirmation = objectMapper.readValue(body, SubscriptionConfirmation.class);
                    return subscriptionConfirmer.confirm(confirmation);

                case NOTIFICATION_TYPE:

                    SubscriptionNotification notification = objectMapper.readValue(body, SubscriptionNotification.class);
                    return subscriptionNotifier.notify(notification);

                default:

                    logger.warn("Subscription request has an unrecognised message type: {}", messageType);
                    return false;

            }
        } catch (IOException ex) {
            logger.error("Failed to parse notification, {}", body, ex);
            return false;
        }


    }

}
