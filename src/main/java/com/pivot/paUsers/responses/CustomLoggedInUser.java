package com.pivot.paUsers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomLoggedInUser{
    private Object user;
    private Object data;
}
