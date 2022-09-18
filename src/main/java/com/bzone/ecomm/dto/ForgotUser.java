package com.bzone.ecomm.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForgotUser {

    @Email(message = "Enter valid email")
    @NotBlank(message = "Email should not be null or empty")
    private String email;

}
