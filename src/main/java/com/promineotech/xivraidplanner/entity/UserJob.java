package com.promineotech.xivraidplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJob {
  private int userjob_pk;
  private User user;
  private Job job;
}
