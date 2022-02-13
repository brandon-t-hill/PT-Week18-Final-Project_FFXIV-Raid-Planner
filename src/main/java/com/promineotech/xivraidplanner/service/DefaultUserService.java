package com.promineotech.xivraidplanner.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.xivraidplanner.dao.UserDao;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.entity.UserInputModel;
import com.promineotech.xivraidplanner.entity.UserJob;
import com.promineotech.xivraidplanner.entity.UserJobGear;
import com.promineotech.xivraidplanner.exception.UserAlreadyExistsException;
import com.promineotech.xivraidplanner.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserService implements UserService {
  @Autowired
  private UserDao userDao;

  @Override
  public List<User> all() {
    log.debug("Retrieve all users.");
    return userDao.all();
  }

  @Override
  public Optional<User> get(String username) {
    log.debug("Retrieve user {}", username);
    return userDao.get(username);
  }

  @Override
  public User create(UserInputModel input) {
    Optional<User> user = get(input.getUsername());
    if(user.isEmpty()) {
      User result = userDao.insert(input);
      return result;
    }
    throw new UserAlreadyExistsException(user.get());
  }

  @Override
  public User update(String username, String charname) {
    if (username == null || username.isEmpty()) return null;
    if (charname == null || charname.isEmpty()) return null;
    //Check that the user exists
    Optional<User> user = get(username);
    if (user.isPresent()) {
      return userDao.update(username, charname);
    }
    
    throw new UserNotFoundException(username);
  }

  @Override
  public User delete(String username) {
    if((username == null) || username.isEmpty()) return null;
    
    //Check for existing user
    Optional<User> existing = get(username);
    if(existing.isPresent()) {
      if(userDao.delete(username)) {
        return existing.get();
      }
      return null;
    }
    
    throw new UserNotFoundException(username);
  }

  @Override
  public List<UserJob> allUserJobs(String username) {
    if(username == null || username.isEmpty()) return null;
    //Check that the user exists
    Optional<User> user = get(username);
    if (user.isPresent()) {
      return userDao.allUserJobs(username);
    }
    throw new UserNotFoundException(username);
  }

  @Override
  public UserJob getUserJob(String username, String jobid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserJobGear> allUserJobGear(String username, String jobid) {
    if(username == null || username.isEmpty()) return null;
    if(jobid == null || jobid.isEmpty()) return null;
    Optional<User> user = get(username);
    if(user.isPresent()) {
      return userDao.allUserJobGear(username, jobid);
    }
    throw new UserNotFoundException(username);
  }

}
