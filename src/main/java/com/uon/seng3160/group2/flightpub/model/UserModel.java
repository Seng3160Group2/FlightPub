package com.uon.seng3160.group2.flightpub.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;

    @NotEmpty
    private String firstName = "John";

    private String middleName = "Mezo";

    @NotEmpty
    private String lastName = "Doe";

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email = "johndoe@email.com";

    @NotEmpty()
    private String phone = "1234 567 890";

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Password should not be empty")
    private String confirmPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob = LocalDate.of(1990, 1, 1);

    @NotEmpty(message = "Please select a gender")
    private String gender = "other";
}