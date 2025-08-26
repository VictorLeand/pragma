package co.com.pragma1.api.mapper;

import co.com.pragma1.api.dto.UserDto;
import co.com.pragma1.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto dto);
}
