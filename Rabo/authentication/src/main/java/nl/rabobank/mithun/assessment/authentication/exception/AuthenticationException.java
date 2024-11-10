package nl.rabobank.mithun.assessment.authentication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationException extends  RuntimeException{

    String exceptionMessage;
}
