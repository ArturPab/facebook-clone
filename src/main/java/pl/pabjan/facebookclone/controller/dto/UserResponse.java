package pl.pabjan.facebookclone.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String lastName;
    private String city;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
}
