package pl.pabjan.facebookclone.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class RegisterRequest {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private LocalDate birthday;
}
