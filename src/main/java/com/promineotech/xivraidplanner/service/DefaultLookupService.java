package com.promineotech.xivraidplanner.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.xivraidplanner.dao.LookupDao;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.Job;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultLookupService implements LookupService {
  @Autowired
  private LookupDao lookupDao;

  @Override
  public List<Gear> allGear() {
    log.debug("Retrieve all Gear");
    return lookupDao.allGear();
  }

  @Override
  public List<Job> allJobs() {
    log.debug("Retrieve all Jobs");
    return lookupDao.allJobs();
  }

}
