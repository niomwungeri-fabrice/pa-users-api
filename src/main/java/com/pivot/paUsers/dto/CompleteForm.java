package com.pivot.paUsers.dto;

import com.pivot.paUsers.enums.Gender;
import lombok.Data;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CompleteForm {
    private String names;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    private Gender gender;
    private String phoneNumber;
}
