package com.promineotech.xivraidplanner.dao;

import java.util.List;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.Job;

public interface LookupDao {
  /**
   * Retrieves a list of all Gear
   * @return A list of all Gear
   */
  List<Gear> allGear();
  
  /**
   * Retrieves a list of all Jobs
   * @return A list of all Jobs
   */
  List<Job> allJobs();

}
