package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pabjan.facebookclone.controller.dto.RegisterRequest;
import pl.pabjan.facebookclone.controller.dto.UserResponse;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.mapper.UserMapper;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        return userMapper.mapToDto(userRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found user")));
    }

    public List<UserResponse> findByName(String name) {
        List<User> users = userRepository.findByName(name);
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<UserResponse> findByLastName(String lastName) {
        List<User> users = userRepository.findByLastName(lastName);
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<UserResponse> findByCity(String city) {
        List<User> users = userRepository.findByCity(city);
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Transactional
    public void edit(RegisterRequest request) {
        User user = authService.getCurrentUser();
        user.setBirthday(request.getBirthday());
        user.setCity(request.getCity());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }
}
