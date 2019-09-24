package com.example.dynamodb;


import com.example.dynamodb.event.CustomSpringEventPublisher;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
public class EventRest {

    private final CustomSpringEventPublisher publisher;

    public EventRest(CustomSpringEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/test")
    public void  cotacao(@RequestParam String dt) {
        publisher.doStuffAndPublishAnEvent(dt);

    }

}
