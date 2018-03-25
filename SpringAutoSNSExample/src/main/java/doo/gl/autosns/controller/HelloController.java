package doo.gl.autosns.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import doo.gl.autosns.sns.HelloPublisher;

@RestController
public class HelloController {

    @Inject
    private HelloPublisher helloPublisher;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("HELLO WORLD");
    }

    @RequestMapping(path = "/publish", method = RequestMethod.POST)
    public ResponseEntity<String> publish() {
        helloPublisher.publish("HELLO WORLD!!");
        return ResponseEntity.ok("");
    }

}
