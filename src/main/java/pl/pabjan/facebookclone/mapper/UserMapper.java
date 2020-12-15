package pl.pabjan.facebookclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.pabjan.facebookclone.controller.dto.UserResponse;
import pl.pabjan.facebookclone.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="name", target = "name")
    @Mapping(source="lastName", target = "lastName")
    @Mapping(source="city", target = "city")
    @Mapping(source="birthday", target = "birthday")
    UserResponse mapToDto(User user);
}
