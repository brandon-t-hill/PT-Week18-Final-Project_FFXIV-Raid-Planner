package com.promineotech.xivraidplanner.exception;

public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String username;

  public UserNotFoundException(String username) {
    this(username, "The user with that username was not found.");
  }

  public UserNotFoundException(String username, String message) {
    super(message);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
