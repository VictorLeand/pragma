package co.com.pragma1.api;

import co.com.pragma1.model.user.exception.BusinessException;
import co.com.pragma1.model.user.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ExceptionResponse> handleBusinessException(BusinessException ex, ServerWebExchange exchange) {
        return Mono.just(buildResponse(ex.getMessage(), exchange, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ExceptionResponse> handleGenericException(Exception ex, ServerWebExchange exchange) {
        return Mono.just(buildResponse("Error interno del servidor", exchange, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private ExceptionResponse buildResponse(String message, ServerWebExchange exchange, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return new ExceptionResponse(
                LocalDateTime.now(),
                message,
                exchange.getRequest().getPath().value()
        );
    }
}
