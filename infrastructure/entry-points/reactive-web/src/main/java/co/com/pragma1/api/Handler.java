package co.com.pragma1.api;

import co.com.pragma1.model.user.User;
import co.com.pragma1.model.user.exception.BusinessException;
import co.com.pragma1.model.user.exception.ExceptionResponse;
import co.com.pragma1.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {


    private final UserUseCase userUseCase;

    public Mono<ServerResponse> listeSaveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(User.class)
                .flatMap(userUseCase::saveUser)
                .flatMap(savedUser -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedUser))
                .onErrorResume(BusinessException.class, ex ->
                        ServerResponse.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new ExceptionResponse(
                                        LocalDateTime.now(),
                                        ex.getMessage(),
                                        serverRequest.path()
                                ))
                )
                .onErrorResume(Exception.class, ex ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new ExceptionResponse(
                                        LocalDateTime.now(),
                                        "Error interno del servidor",
                                        serverRequest.path()
                                ))
                );
    }
}

