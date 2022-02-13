package com.promineotech.xivraidplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gear {
  private int gear_pk;
  private String tier;
  private int item_level;
}
