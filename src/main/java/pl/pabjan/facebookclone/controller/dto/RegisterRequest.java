package pl.pabjan.facebookclone.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(max = 80)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
}
