package com.pivot.paUsers.dto;

import com.pivot.paUsers.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountByGender {
    private Gender title;
    private long value;
}
