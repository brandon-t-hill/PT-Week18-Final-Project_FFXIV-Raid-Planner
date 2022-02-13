package com.promineotech.xivraidplanner.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.Job;
import com.promineotech.xivraidplanner.entity.Role;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultLookupDao implements LookupDao {
  @Autowired
  private NamedParameterJdbcTemplate provider;
  @Override
  public List<Gear> allGear() {
    log.debug("Retrieve all Gear");
    String sql = "SELECT * FROM gear";
    List<Gear> gear = provider.query(sql, (rs, rowNum) -> {
      int pk = rs.getInt("gear_pk");
      String tier = rs.getString("tier");
      int itemLevel = rs.getInt("item_level");
      return new Gear(pk, tier, itemLevel);
    });
    return gear;
  }

  @Override
  public List<Job> allJobs() {
    log.debug("Retrieve all Jobs");
    String sql = "SELECT * FROM jobs";
    List<Job> jobs = provider.query(sql, (rs, rowNum) -> {
      String pk = rs.getString("job_pk");
      String name = rs.getString("name");
      Role role = Role.valueOf(rs.getString("role"));
      return new Job(pk, name, role);
    });
    return jobs;
  }

}
