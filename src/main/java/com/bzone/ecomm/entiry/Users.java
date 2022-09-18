package com.bzone.ecomm.entiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Date;

/**
 * @author sundar
 * @since 16-09-2022
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends Audit<String> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = true)
    private String lastName;
    @Column(name = "nick_name", nullable = false)
    private String nickName;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please, provide a valid Email")
    private String email;
    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;
    @Column(name = "dob", nullable = false)
    private Date dob;
    private boolean status = Boolean.TRUE;

}
