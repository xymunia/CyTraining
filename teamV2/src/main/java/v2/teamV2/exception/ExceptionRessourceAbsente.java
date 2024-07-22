package v2.teamV2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExceptionRessourceAbsente extends RuntimeException {

    public ExceptionRessourceAbsente(String message) {
        super(message);
    }

}
