package co.com.pragma1.r2dbc;

import co.com.pragma1.model.user.User;
import co.com.pragma1.model.user.gateways.UserRepository;
import co.com.pragma1.r2dbc.entity.UserEntity;
import co.com.pragma1.r2dbc.helper.ReactiveAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        Long,
        UserReactiveRepository

> implements UserRepository {

    private final TransactionalOperator transactionalOperator;

    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper,
                                         TransactionalOperator transactionalOperator) {
        super(repository, mapper, entity -> mapper.map(entity, User.class));

        this.transactionalOperator = transactionalOperator;
    }

    @Override
    public Mono<User> save(User user) {
        return Mono.just(user)
                .doOnNext(usuario -> log.info("Iniciando guardado de usuario : {}" , usuario))
                .flatMap(super::save)
                .as(transactionalOperator::transactional)
                .doOnNext(usuario -> log.info("Usuario guardado : {}" , usuario));
    }

    @Override
    public Mono<Boolean> existsByDocumento(String documento) {
        log.info("Verificando existencia de documento: {}", documento);
        return repository.existsByDocumento(documento)
                .doOnNext(exists -> log.info("¿El documento existe? {} = {}", documento, exists));
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        log.info("Verificando existencia de correo: {}", email);
        return repository.existsByEmail(email)
                .doOnNext(exists -> log.info("¿El correo existe? {} = {}", email, exists));
    }

}
