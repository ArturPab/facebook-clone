package pl.pabjan.facebookclone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    private String password;
}
