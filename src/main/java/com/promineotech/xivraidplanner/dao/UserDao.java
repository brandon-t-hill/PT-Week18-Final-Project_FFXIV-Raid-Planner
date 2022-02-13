package com.promineotech.xivraidplanner.dao;

import java.util.List;
import java.util.Optional;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.entity.UserInputModel;
import com.promineotech.xivraidplanner.entity.UserJob;
import com.promineotech.xivraidplanner.entity.UserJobGear;

public interface UserDao {
  /**
   * Retrieves a list of all users
   * 
   * @return All the users found, empty list if none are present.
   */
  List<User> all();

  /**
   * Retrieves a user with the specified username
   * 
   * @param username The username of the user
   * @return The user if found, otherwise empty optional.
   */
  Optional<User> get(String username);

  /**
   * Inserts a new user into the database
   * 
   * @param input The new user
   * @return The newly created user
   */
  User insert(UserInputModel input);

  /**
   * Updates the character name of a user
   * 
   * @param username The username of the user
   * @param charname The character name to update to
   * @return The updated user if successful, otherwise null
   */
  User update(String username, String charname);

  /**
   * 
   * @param username
   * @return
   */
  boolean delete(String username);

  /**
   * 
   * @param username
   * @return
   */
  List<UserJob> allUserJobs(String username);

  /**
   * 
   * @param username
   * @param jobid
   * @return
   */
  UserJob getUserjob(String username, String jobid);
  
  /**
   * 
   * @param username
   * @param jobid
   * @return
   */
  List<UserJobGear> allUserJobGear(String username, String jobid);
}
