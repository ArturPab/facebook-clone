package pl.pabjan.facebookclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.facebookclone.controller.dto.RegisterRequest;
import pl.pabjan.facebookclone.controller.dto.UserResponse;
import pl.pabjan.facebookclone.service.UserService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<UserResponse>> findByName(@PathVariable String name) {
        return status(HttpStatus.OK).body(userService.findByName(name));
    }

    @GetMapping("/by-last-name/{lastName}")
    public ResponseEntity<List<UserResponse>> findByLastName(@PathVariable String lastName) {
        return status(HttpStatus.OK).body(userService.findByLastName(lastName));
    }
    @GetMapping("/by-city/{city}")
    public ResponseEntity<List<UserResponse>> findByCity(@PathVariable String city) {
        return status(HttpStatus.OK).body(userService.findByCity(city));
    }

    public ResponseEntity<Void> edit(@RequestBody RegisterRequest request) {
        userService.edit(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
