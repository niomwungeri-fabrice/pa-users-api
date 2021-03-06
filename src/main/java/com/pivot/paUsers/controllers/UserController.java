package com.pivot.paUsers.controllers;

import com.pivot.paUsers.dto.AuthenticationRequest;
import com.pivot.paUsers.dto.CompleteForm;
import com.pivot.paUsers.models.UserAccount;
import com.pivot.paUsers.responses.CustomLoggedInUser;
import com.pivot.paUsers.responses.GenericResponse;
import com.pivot.paUsers.services.CustomUserDetailsService;
import com.pivot.paUsers.services.UserService;
import com.pivot.paUsers.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JWTUtil jwtUtil;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        UserAccount loggedInUser = userService.findByEmail(loginRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new CustomLoggedInUser(loggedInUser, jwt));
    }

    @GetMapping("/count/gender")
    public ResponseEntity<?> numberByGender(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String email = jwtUtil.extractUsername(token.split(" ")[1]);
        if (!userService.findByEmail(email).isAdmin()) {
            return new ResponseEntity(new GenericResponse("error", "Un Authorized Request"),
                    HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new GenericResponse("success", userService.countByGender()));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String email = jwtUtil.extractUsername(token.split(" ")[1]);
        if (!userService.findByEmail(email).isAdmin()) {
            return new ResponseEntity(new GenericResponse("error", "Un Authorized Request"),
                    HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new GenericResponse("success", userService.getAllUsers()));
    }

    @PostMapping("/complete/{userId}/form")
    public ResponseEntity<?> completeForm(@Valid @RequestBody CompleteForm userAccount, @PathVariable("userId") String userId) {
        if (userService.findByUserId(userId) == null) {
            return new ResponseEntity(new GenericResponse("error", "User does not exist!"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new GenericResponse("User info updated!", userService.completeForm(userAccount, userId)));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserAccount userAccount) {

        UserAccount  account = userService.findByEmail(userAccount.getEmail());

        if (account != null) {
            return new ResponseEntity(new GenericResponse("Email Address already in use!", account),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        System.out.println(userAccount);
        UserAccount createUser = userService.signUp(userAccount);
        return ResponseEntity.ok(new GenericResponse("data", createUser));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("error", errorMessage);
        });
        return errors;
    }

}
