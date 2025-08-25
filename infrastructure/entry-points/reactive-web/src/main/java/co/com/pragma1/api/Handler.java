package co.com.pragma1.api;

import co.com.pragma1.api.mapper.UserMapper;
import co.com.pragma1.api.validator.RequestValidator;
import co.com.pragma1.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {


    private final UserUseCase userUseCase;
    private final TransactionalOperator transactionalOperator;
    private final RequestValidator requestValidator;
    private final UserMapper mapper;

    public Mono<ServerResponse> listeSaveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserDto.class)
                .map(requestValidator::validate)
                .map(mapper::toUser)
                .flatMap(userUseCase::saveUser)
                .as(transactionalOperator::transactional)
                .flatMap(savedUser -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedUser));
    }
}
