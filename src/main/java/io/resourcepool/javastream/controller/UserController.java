package io.resourcepool.javastream.controller;

import io.resourcepool.javastream.controller.dtos.ErrorDto;
import io.resourcepool.javastream.domain.User;
import io.resourcepool.javastream.domain.dtos.UserCreationRequest;
import io.resourcepool.javastream.service.UserRegistryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRegistryService userRegistryService;

  public UserController(UserRegistryService userRegistryService) {
    this.userRegistryService = userRegistryService;
  }

  @GetMapping()
  @Operation(summary = "Retrieves all **users**",
    description = "This endpoint returns a list of all users in the system. It does not require any parameters.")
  public List<User> findAll() {
    return userRegistryService.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  @Operation(summary = "Creates a new **user**",
    description = "This endpoint allows you to create a new user in the system. The user will be saved with the provided details.")
  @ApiResponse(responseCode = "400", description = "User validation failed", content = {@Content(
    schema = @Schema(implementation = ErrorDto.class),
    examples = {
      @ExampleObject(name = "User must have a last name", value = """
      {
        "statusCode": 400,
        "message": "invalid value for fields:lastName",
        "occurredAt": "2023-10-01T12:00:00Z"
      }
      """)})}
  )
  @ApiResponse(responseCode = "401", description = "User is blacklisted", content = {@Content(
    schema = @Schema(implementation = ErrorDto.class),
    examples = {@ExampleObject(name = "User is blacklisted", value = """
      {
        "statusCode": 401,
        "message": "user lastName is blacklisted",
        "occurredAt": "2023-10-01T12:00:00Z"
      }
      """)})}
  )
  public User save(@Valid @RequestBody UserCreationRequest user) {
    return userRegistryService.save(user);
  }


  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/actions/blacklist")
  @Operation(summary = "Blacklist an existing **user**",
    description = "This endpoint allows you to blacklist an existing user by their last name. Blacklisted users cannot be created or modified.")
  public void blacklist(@RequestParam(name = "last_name") String lastName) {
    userRegistryService.blacklist(lastName);
  }
}
