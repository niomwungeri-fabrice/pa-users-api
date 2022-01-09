package com.pivot.paUsers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pivot.paUsers.enums.Gender;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@ToString
@Data
@Entity
@Table(name = "users")
public class UserAccount extends GenericModelInfo {
    @Id
    @Column(name = "user_id")
    private UUID userId = UUID.randomUUID();

    private String names;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Invalid email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
