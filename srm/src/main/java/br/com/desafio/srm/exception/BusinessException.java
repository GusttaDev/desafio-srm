package br.com.desafio.srm.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3180109981099617472L;

    @JsonIgnore
    private HttpStatus httpStatusCode;

    private String code;
    private String message;
    private String description;
    private Object object;

    public BusinessException(HttpStatus httpStatus, String msg) {
        this.httpStatusCode = httpStatus;
        this.message = msg;
    }
}
