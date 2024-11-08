package nl.rabobank.mithun.assessment.timeline.controller;

import nl.rabobank.mithun.assessment.timeline.model.TimelineEvent;
import nl.rabobank.mithun.assessment.timeline.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/timeline")
public class TimelineController {

    @Autowired
    TimelineService timelineService;

    @PostMapping(value="/postMessage")
    public void addMessages(@RequestBody TimelineEvent message){
        timelineService.postMessage(message);
    }
}
