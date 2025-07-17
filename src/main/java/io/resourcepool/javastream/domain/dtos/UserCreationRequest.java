package io.resourcepool.javastream.domain.dtos;

import io.resourcepool.javastream.domain.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record UserCreationRequest(
  @NotNull String firstName, @NotNull String lastName, @Min(18) @Max(120) Integer age) {

  public User toDomain() {
    return User.builder()
      .firstName(firstName)
      .lastName(lastName)
      .age(age)
      .createdAt(Instant.now())
      .build();
  }
}
