package com.promineotech.xivraidplanner.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.entity.UserInputModel;
import com.promineotech.xivraidplanner.entity.UserJob;
import com.promineotech.xivraidplanner.entity.UserJobGear;
import com.promineotech.xivraidplanner.exception.UserAlreadyExistsException;
import com.promineotech.xivraidplanner.exception.UserNotFoundException;
import com.promineotech.xivraidplanner.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultUserController implements UserController {
  @Autowired
  private UserService service;

  @Override
  public List<User> all() {
    log.debug("Retrieve all users");
    return service.all();
  }

  @Override
  public User getUser(String username) {
    log.debug("Retrieve user {}", username);
    Optional<User> user = service.get(username);
    if (user.isPresent()) {
      return user.get();
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested user was not found");
  }

  @Override
  public User create(UserInputModel input) {
    if (input != null) {
      if (input.getUsername() != null && !input.getUsername().isEmpty()) {
        if (input.getCharname() != null && !input.getCharname().isEmpty()) {
          try {
            User result = service.create(input);
            if (result != null) {
              return result;
            }
          } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
          }
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
              "Failed to create user due to an unhandled or unexpected internal error");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Character name cannot be empty or null");
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Username cannot be empty or null");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User input cannot be null");
  }

  @Override
  public User update(String username, String charname) {
    if ((username != null && !username.isEmpty())) {
      if (charname != null && !charname.isEmpty()) {
        try {
          User updated = service.update(username, charname);
          if (updated != null) {
            return updated;
          }
        } catch (UserNotFoundException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Failed to update user due to an unhandled or unexpected internal error");
      }
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
        "The username or character name cannot be empty or null");
  }

  @Override
  public User delete(String username) {
    if (username != null && !username.isEmpty()) {
      try {
        User result = service.delete(username);
        if (result != null) {
          return result;
        }
      } catch (UserNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Failed to remove user due to an unhandled or unexpected internal error");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty or null");
  }

  @Override
  public List<UserJob> allUserJobs(String username) {
    if (username != null && !username.isEmpty()) {
      try {
        List<UserJob> result = service.allUserJobs(username);
        if (result != null) {
          return result;
        }
      } catch (UserNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Failed to retrieve user's jobs due to an unhandled or unexpected internal error");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty or null");
  }

  @Override
  public UserJob getUserJob(String username, String jobid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserJobGear> allUserJobGear(String username, String jobid) {
    if (username != null && !username.isEmpty()) {
      if (jobid != null && !jobid.isEmpty()) {
        try {
          List<UserJobGear> result = service.allUserJobGear(username, jobid);
          if (result != null) {
            return result;
          }
        } catch (UserNotFoundException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Failed to retrieve user's job gear due to an unhandled or unexpected internal error");
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Job id cannot be empty or null");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty or null");
  }

}
