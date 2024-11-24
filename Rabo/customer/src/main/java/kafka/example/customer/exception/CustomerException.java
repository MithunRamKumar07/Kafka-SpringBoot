package kafka.example.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerException extends  RuntimeException{

    String exceptionMessage;
}
