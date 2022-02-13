package com.promineotech.xivraidplanner.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.GearSlot;
import com.promineotech.xivraidplanner.entity.Job;
import com.promineotech.xivraidplanner.entity.Role;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.entity.UserInputModel;
import com.promineotech.xivraidplanner.entity.UserJob;
import com.promineotech.xivraidplanner.entity.UserJobGear;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserDao implements UserDao {
  @Autowired
  private NamedParameterJdbcTemplate provider;

  @Override
  public List<User> all() {
    log.debug("Retrieve all users.");
    String sql = "SELECT * FROM users";
    List<User> users = provider.query(sql, (rs, rowNum) -> {
      int pk = rs.getInt("user_pk");
      String username = rs.getString("username");
      String charname = rs.getString("charname");
      User user = new User(pk, username, charname);
      return user;
    });
    return users;
  }

  @Override
  public Optional<User> get(String username) {
    log.debug("Retrieve user {}", username);
    String sql = "SELECT * FROM users WHERE username = :username";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", username);

    List<User> users = provider.query(sql, parameters, (rs, rowNum) -> {
      return (new User(rs.getInt("user_pk"), rs.getString("username"), rs.getString("charname")));
    });
    if (users.isEmpty()) {
      return Optional.empty();
    }
    return (Optional.of(users.get(0)));
  }

  @Override
  public User insert(UserInputModel input) {
    String sql = "INSERT INTO users(username, charname) VALUES(:username, :charname)";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", input.getUsername());
    parameters.put("charname", input.getCharname());

    int rows = provider.update(sql, parameters);
    if (rows > 0) {
      Optional<User> user = get(input.getUsername());
      if (user.isPresent()) {
        return user.get();
      }
    }
    return null;
  }

  @Override
  public User update(String username, String charname) {
    String sql = "UPDATE users SET charname = :charname WHERE username = :username";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", username);
    parameters.put("charname", charname);

    int rows = provider.update(sql, parameters);
    if (rows > 0) {
      Optional<User> updated = get(username);
      if(updated.isPresent()) {
        return updated.get();
      }
    }
    return null;
  }

  @Override
  public boolean delete(String username) {
    String sql = "DELETE FROM users WHERE username = :username";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", username);
    
    int rows = provider.update(sql, parameters);
    return (rows == 1);
  }

  @Override
  public List<UserJob> allUserJobs(String username) {
    // @formatter:off
    String sql = "SELECT users.user_pk, users.username, users.charname, "
                +"jobs.job_pk, jobs.name, jobs.role, userjobs.userjobs_pk "
                +"FROM((userjobs INNER JOIN users ON users.user_pk = userjobs.user_pk) "
                +"INNER JOIN jobs ON jobs.job_pk = userjobs.job_pk) "
                +"WHERE users.username = :username";
    // @formatter:on
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", username);
    
    List<UserJob> result = provider.query(sql, parameters, (rs, rowNum) ->{
      int upk = rs.getInt("user_pk");
      String uname = rs.getString("username");
      String uchar = rs.getString("charname");
      User user = new User(upk, uname, uchar);
      String jpk = rs.getString("job_pk");
      String jname = rs.getString("name");
      Role jrole = Role.valueOf(rs.getString("role"));
      Job job = new Job(jpk, jname, jrole);
      int ujpk = rs.getInt("userjobs_pk");
      return new UserJob(ujpk, user, job);
    });
    return result;
  }

  @Override
  public UserJob getUserjob(String username, String jobid) {
    // TODO GET/READ operation for single UserJob
    return null;
  }

  @Override
  public List<UserJobGear> allUserJobGear(String username, String jobid) {
    // @formatter:off
    String sql = "SELECT users.user_pk, users.username, users.charname, "
        +"jobs.job_pk, jobs.name, jobs.role, userjobs.userjobs_pk, "
        +"gear.gear_pk, gear.tier, gear.item_level, "
        +"userjobgear.jobgear_pk, userjobgear.slot "
        +"FROM((((userjobgear INNER JOIN userjobs ON userjobs.userjobs_pk = userjobgear.userjobs_pk) "
        +"INNER JOIN gear ON gear.gear_pk = userjobgear.gear_pk) "
        +"INNER JOIN users ON users.user_pk = userjobs.user_pk) "
        +"INNER JOIN jobs ON jobs.job_pk = userjobs.job_pk) "
        +"WHERE users.username = :username AND jobs.job_pk = :jobid";
    // @formatter:on
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", username);
    parameters.put("jobid", jobid);
    List<UserJobGear> result = provider.query(sql, parameters, (rs, rowNum) ->{
      //User
      int upk = rs.getInt("user_pk");
      String uname = rs.getString("username");
      String uchar = rs.getString("charname");
      User user = new User(upk, uname, uchar);
      //Job
      String jpk = rs.getString("job_pk");
      String jname = rs.getString("name");
      Role jrole = Role.valueOf(rs.getString("role"));
      Job job = new Job(jpk, jname, jrole);
      //UserJob
      int ujpk = rs.getInt("userjobs_pk");
      UserJob userJob = new UserJob(ujpk, user, job);
      //Gear
      int gpk = rs.getInt("gear_pk");
      String gtier = rs.getString("tier");
      int gitemlevel = rs.getInt("item_level");
      Gear gear = new Gear(gpk, gtier, gitemlevel);
      GearSlot gearSlot = GearSlot.valueOf(rs.getString("slot"));
      //UserJobGear
      int ujgpk = rs.getInt("jobgear_pk");
      return new UserJobGear(ujgpk, userJob, gear, gearSlot);
    });
    return result;
  }
}
