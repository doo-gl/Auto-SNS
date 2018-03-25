package doo.gl.autosns.sns;

import org.springframework.stereotype.Component;

import doo.gl.autosns.spring.SNSPublisher;

@Component
public class HelloPublisher extends SNSPublisher {

    @Override
    public String getTopicName() {
        return "HELLO_WORLD";
    }

    @Override
    protected void onRegistrationFailure(RuntimeException ex) {
        throw ex;
    }
}
