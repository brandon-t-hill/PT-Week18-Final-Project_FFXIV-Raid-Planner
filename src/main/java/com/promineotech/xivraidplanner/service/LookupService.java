package com.promineotech.xivraidplanner.service;

import java.util.List;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.Job;

public interface LookupService {
  /**
   * Retrieve a list of all available Gear
   * @return A list of all available Gear
   */
  List<Gear> allGear();
  
  /**
   * Retrieve a list of all available Jobs
   * @return A list of all available Jobs
   */
  List<Job> allJobs();
}
