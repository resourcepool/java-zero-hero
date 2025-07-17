package io.resourcepool.javastream.exceptions;

public class UserBlacklistedException extends IllegalStateException {

  public UserBlacklistedException(String lastName) {
    super("User with last name '" + lastName + "' is blacklisted.");
  }

}
