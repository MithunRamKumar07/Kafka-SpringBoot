package nl.rabobank.mithun.assessment.timeline.controller;

import nl.rabobank.mithun.assessment.timeline.model.TimelineEvent;
import nl.rabobank.mithun.assessment.timeline.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**<p> Controller class to post messages to the timeline service</p>
 */
@RestController
@RequestMapping(value = "/timeline")
public class TimelineController {

    @Autowired
    TimelineService timelineService;

    @PostMapping(value="/postMessage")
    public ResponseEntity<String> addMessages(@RequestBody TimelineEvent message){
        // Just publish the event asynchronously
        timelineService.postMessage(message);
        // send a response to acknowledge the event was published successfully
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("The event has been successfully published");
    }
}
