package pl.pabjan.facebookclone.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 7, max=50, message = "Password must be between 7 and 50 characters")
    private String password;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 7 and 50 characters")
    private String name;

    @NotBlank(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name must be between 7 and 50 characters")
    private String lastName;

    @NotBlank(message = "City cannot be null")
    private String city;

    @NotBlank(message = "Birthday cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
}
