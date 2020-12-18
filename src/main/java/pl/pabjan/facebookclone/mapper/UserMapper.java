package pl.pabjan.facebookclone.mapper;
import org.springframework.stereotype.Component;
import pl.pabjan.facebookclone.controller.dto.UserResponse;
import pl.pabjan.facebookclone.model.User;
@Component
public class UserMapper {

    public UserResponse mapToDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setBirthday(user.getBirthday());
        userResponse.setCity(user.getCity());
        userResponse.setName(user.getName());
        userResponse.setLastName(user.getLastName());
        return userResponse;
    }
}
