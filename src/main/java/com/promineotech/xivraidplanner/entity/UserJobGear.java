package com.promineotech.xivraidplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJobGear {
  private int userjobgear_pk;
  private UserJob userjob;
  private Gear gear;
  private GearSlot slot;
}
