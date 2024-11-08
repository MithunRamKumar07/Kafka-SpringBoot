package nl.rabobank.mithun.assessment.timeline.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.timeline.model.Message;
import nl.rabobank.mithun.assessment.timeline.model.Timeline;
import nl.rabobank.mithun.assessment.timeline.repository.MessageRepository;
import nl.rabobank.mithun.assessment.timeline.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimelineService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TimelineRepository timelineRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
        log.info("Message purged into the DB");
    }

    public void saveTimelineData(Timeline timeline) {
        timelineRepository.save(timeline);
        log.info("Timeline Data purged into the DB");
    }
}
