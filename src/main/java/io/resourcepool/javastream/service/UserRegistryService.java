package io.resourcepool.javastream.service;

import io.resourcepool.javastream.domain.BlacklistedUser;
import io.resourcepool.javastream.domain.User;
import io.resourcepool.javastream.domain.dtos.UserCreationRequest;
import io.resourcepool.javastream.exceptions.UserBlacklistedException;
import io.resourcepool.javastream.persistence.BlacklistUserRepository;
import io.resourcepool.javastream.persistence.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class UserRegistryService {
  private final UserRepository userRepository;
  private final BlacklistUserRepository blacklistUserRepository;

  public UserRegistryService(UserRepository userRepository, BlacklistUserRepository blacklistUserRepository) {
    this.userRepository = userRepository;
    this.blacklistUserRepository = blacklistUserRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Transactional
  public User save(UserCreationRequest user) {
    if (blacklistUserRepository.existsByLastNameEqualsIgnoreCase(user.lastName())) {
      throw new UserBlacklistedException(user.lastName());
    }
    return userRepository.save(user.toDomain());
  }

  public void blacklist(String lastName) {
    blacklistUserRepository.save(BlacklistedUser.builder()
      .lastName(lastName)
      .createdAt(Instant.now())
      .build());
  }
}
