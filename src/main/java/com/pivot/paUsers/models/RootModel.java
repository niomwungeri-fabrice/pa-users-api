package com.pivot.paUsers.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RootModel {
    private String message;
    private Date date;
}
