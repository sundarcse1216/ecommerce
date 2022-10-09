package com.bzone.ecomm.controller;

import com.bzone.ecomm.dto.BZoneResponse;
import com.bzone.ecomm.dto.ForgotUser;
import com.bzone.ecomm.entiry.Users;
import com.bzone.ecomm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Register User", description = "Can create user using this API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Register Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "User Registration failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid input", content = @Content)
    })
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> addUser(@Valid @RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(user));
    }

    @Operation(summary = "Update User", description = "Can update user using by userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Updated Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "User Update failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid input", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> updateUser(@RequestBody Users user, @PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user, id));
    }

    @Operation(summary = "Get User", description = "Can Get single user by userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch User Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "Failed to fetch user", content = @Content),
            @ApiResponse(responseCode = "204", description = "User Not Found", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getUserById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Get All User", description = "Can Get all registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch User Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "Failed to fetch user", content = @Content),
            @ApiResponse(responseCode = "204", description = "User Not Found", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getAllUsers() {
        return ResponseEntity.ok(userService.gelAllUser());
    }

    @Operation(summary = "Delete User", description = "Can delete single user by userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "Failed to delete user", content = @Content),
            @ApiResponse(responseCode = "204", description = "User Not Found", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @Operation(summary = "Find username", description = "Can get username by emailId",
            requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ForgotUser.class)), required = true))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find Username Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "Failed to find user", content = @Content),
            @ApiResponse(responseCode = "204", description = "User Not Found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Bad Request", content = @Content)
    })
    @GetMapping(value = "/forgotuser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getUserByEmail(@Parameter(hidden = true) @RequestBody @Valid ForgotUser user) {
        return ResponseEntity.ok(userService.getUserByEmail(user.getEmail()));
    }

    @Operation(summary = "Reset Password", description = "Can reset password by emailId",
            requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ForgotUser.class)), required = true))

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset link send to mail Successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "500", description = "Failed to reset password", content = @Content),
            @ApiResponse(responseCode = "204", description = "User Not Found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Bad Request", content = @Content)
    })
    @GetMapping(value = "/forgotpassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getPasswordByEmail(@Parameter(hidden = true) @Valid @RequestBody ForgotUser user) {
        return ResponseEntity.ok(userService.getPasswordByEmail(user.getEmail()));
    }

}
