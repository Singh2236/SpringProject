package com.navi.modelSchool.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */

@Data
@Entity
@Table(name = "contact_msg")
public class Contact  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "contact_id")
    private int contactId;  //since this data type is the primary key inside our table we are using int datatype.

    /**
     * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
     * @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
     * @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
     */

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long ")
    String name;


    @Column(name = "mobile_num")
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    String mobileNum;


    @NotBlank(message = "Email Should not be blank")
    @Email(message = "Please provide a valid email")
    String email;


    @NotBlank(message = "Subject must not be blank")
    @Size(message = "Subject must be 5 characters long")
    String subject;


    @NotBlank(message = "Message must not be blank")
    @Size(message = "Message must be at least 10 characters long")
    String message;


    private String status;      //for status of the message
}
