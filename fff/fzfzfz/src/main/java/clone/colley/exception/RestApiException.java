package clone.colley.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class RestApiException {
    private String Message;
    private HttpStatus httpStatus;
}
