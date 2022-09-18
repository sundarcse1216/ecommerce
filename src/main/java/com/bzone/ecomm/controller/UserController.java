package com.bzone.ecomm.controller;

import com.bzone.ecomm.dto.BZoneResponse;
import com.bzone.ecomm.dto.ForgotUser;
import com.bzone.ecomm.entiry.Users;
import com.bzone.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sundar
 * @since 16-09-2022
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> addUser(@Valid @RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(user));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> updateUser(@RequestBody Users user, @PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user, id));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getUserById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getAllUsers() {
        return ResponseEntity.ok(userService.gelAllUser());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping(value = "/forgotuser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getUserByEmail(@RequestBody @Valid ForgotUser user) {
        return ResponseEntity.ok(userService.getUserByEmail(user.getEmail()));
    }

    @GetMapping(value = "/forgotpassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getPasswordByEmail(@RequestBody @Valid ForgotUser user) {
        return ResponseEntity.ok(userService.getPasswordByEmail(user.getEmail()));
    }

}
