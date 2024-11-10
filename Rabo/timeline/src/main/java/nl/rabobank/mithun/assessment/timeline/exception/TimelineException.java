package nl.rabobank.mithun.assessment.timeline.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimelineException extends  RuntimeException{

    String exceptionMessage;
}
