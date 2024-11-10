package nl.rabobank.mithun.assessment.timeline.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ErrorResponse {
    String errorDetails;
}
