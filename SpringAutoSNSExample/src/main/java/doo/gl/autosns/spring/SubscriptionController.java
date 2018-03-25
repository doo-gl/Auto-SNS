package doo.gl.autosns.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping(path = "/AutoSNS")
@RestController
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Inject
    private SubscriptionProcessor subscriptionProcessor;

    @RequestMapping(path = "/subscribe", method = POST)
    public ResponseEntity subscription(HttpEntity<String> entity) {
        HttpHeaders headers = entity.getHeaders();
        String body = entity.getBody();

        logger.info(
            "Headers: {}, Body: {}", headers, body
        );

        boolean processedSuccessfully = subscriptionProcessor.process(headers, body);

        logger.info("Processed successfully: {}", processedSuccessfully);

        return processedSuccessfully ?
            ResponseEntity.ok().build() :
            ResponseEntity.status(500).build();
    }

}
