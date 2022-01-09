package com.pivot.paUsers.controllers;

import com.pivot.paUsers.models.RootModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RootController {
    @GetMapping("/")
    public ResponseEntity<RootModel> route() {
        try {
            return new ResponseEntity<>( new RootModel("Welcome to Pivot Access User Management System V1.", new Date()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
