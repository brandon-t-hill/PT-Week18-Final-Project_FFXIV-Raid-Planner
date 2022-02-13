package com.promineotech.xivraidplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
  private String job_pk;
  private String name;
  private Role role;
}
