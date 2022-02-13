package com.promineotech.xivraidplanner.service;

import java.util.List;
import java.util.Optional;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.entity.UserInputModel;
import com.promineotech.xivraidplanner.entity.UserJob;
import com.promineotech.xivraidplanner.entity.UserJobGear;

public interface UserService {
  /**
   * Retrieves all users
   * 
   * @return All users. If none are present, an empty list.
   */
  List<User> all();

  /**
   * Retrieves a user with a specified username
   * 
   * @param username
   * @return The user with the specified username, if not found an empty optional.
   */
  Optional<User> get(String username);
  
  /**
   * Creates a new user
   * @param input User data to create the new user with
   * @return The newly created user if successfull, otherwise returns null
   */
  User create(UserInputModel input);
  
  /**
   * Updates the character name of a user
   * @param username The username of the user to update
   * @param charname The name to update the character name to
   * @return The updated user if successful, otherwise null
   */
  User update(String username, String charname);
  
  /**
   * Delets the user with a specified username
   * @param username The username of the user to remove
   * @return The user that was deleted
   */
  User delete(String username);
  
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
  UserJob getUserJob(String username, String jobid);
  
  /**
   * 
   * @param username
   * @param jobid
   * @return
   */
  List<UserJobGear> allUserJobGear(String username, String jobid);
}
