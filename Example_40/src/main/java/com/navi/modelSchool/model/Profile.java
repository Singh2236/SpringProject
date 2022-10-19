package com.navi.modelSchool.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Profile {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be at 10 digits long")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid Email Address")
    private String email;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "Address must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "city must not be blank")
    @Size(min = 3, message = "city must be at least 3 characters long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 3, message = "State must be at least 3 characters long")
    private String state;

    @NotBlank(message = "Zip Code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip code must be at least 5 digits long")
    private String zipCode;
}
