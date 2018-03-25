package doo.gl.autosns.spring;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.List;

@Component
public class SNSInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Inject
    private List<SNSPublisher> publishers;

    @Inject
    private List<SNSSubscriber> subscribers;

    private void initialisePublishers() {
        publishers.forEach(SNSPublisher::initialise);
    }

    private void initialiseSubscribers() {
        subscribers.forEach(SNSSubscriber::initialise);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initialisePublishers();
        initialiseSubscribers();
    }
}
