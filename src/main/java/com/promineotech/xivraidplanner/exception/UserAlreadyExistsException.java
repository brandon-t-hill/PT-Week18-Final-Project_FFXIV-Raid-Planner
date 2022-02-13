package com.promineotech.xivraidplanner.exception;

import com.promineotech.xivraidplanner.entity.User;

public class UserAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  private User user;
  
  public UserAlreadyExistsException(User existing) {
    this(existing, "This user already exists.");
  }
  
  public UserAlreadyExistsException(User existing, String message) {
    super(message);
    this.user = existing;
  }
  
  public User getUser() {
    return user;
  }
}
