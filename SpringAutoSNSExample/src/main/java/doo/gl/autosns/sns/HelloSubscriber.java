package doo.gl.autosns.sns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import doo.gl.autosns.spring.SNSSubscriber;

@Component
public class HelloSubscriber extends SNSSubscriber {

    private static final Logger logger = LoggerFactory.getLogger(HelloSubscriber.class);

    @Override
    public String getTopicName() {
        return "HELLO_WORLD";
    }

    @Override
    public void processNotification(String notification) {
        logger.info("RECEIVED NOTIFICATION: {}", notification);
    }

    @Override
    public void onSubscribeFailure(RuntimeException ex) {
        throw ex;
    }
}
