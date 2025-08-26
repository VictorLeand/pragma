package co.com.pragma1.api;

import co.com.pragma1.api.config.UserPath;
import co.com.pragma1.api.dto.UserDto;
import co.com.pragma1.api.globalException.GlobalExceptionFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

    private final UserPath userPath;

    @RouterOperations({
            @RouterOperation(
                    path = "/users",
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "listeSaveUser",
                    operation = @Operation(
                            operationId = "createUser",
                            summary = "Crear un nuevo usuario",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Usuario creado",
                                            content = @Content(schema = @Schema(implementation = UserDto.class))
                                    )
                            }
                    )
            )
    })
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler userHandler, GlobalExceptionFilter filter) {
        return route(POST(userPath.getUsers()), userHandler::listeSaveUser)
                .filter(filter);

    }
}
