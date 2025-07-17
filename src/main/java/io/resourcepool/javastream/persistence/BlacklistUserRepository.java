package io.resourcepool.javastream.persistence;

import io.resourcepool.javastream.domain.BlacklistedUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistUserRepository extends CrudRepository<BlacklistedUser, Long> {
  boolean existsByLastNameEqualsIgnoreCase(String lastName);
}
