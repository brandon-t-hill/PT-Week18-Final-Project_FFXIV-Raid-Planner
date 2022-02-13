package com.promineotech.xivraidplanner.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.Job;
import com.promineotech.xivraidplanner.service.LookupService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultLookupController implements LookupController {
  @Autowired
  private LookupService service;
  
  @Override
  public List<Gear> allGear() {
    log.debug("Retrieve all Gear");
    return service.allGear();
  }

  @Override
  public List<Job> allJobs() {
    log.debug("Retrieve all Jobs");
    return service.allJobs();
  }

}
