package io.resourcepool.javastream.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column
  private String firstName;
  @Column
  private String lastName;
  @Column
  private Integer age;
  @Column
  private Instant createdAt;
}
