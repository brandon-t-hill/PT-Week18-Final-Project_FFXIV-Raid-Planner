package com.promineotech.xivraidplanner.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.promineotech.xivraidplanner.entity.Gear;
import com.promineotech.xivraidplanner.entity.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface LookupController {
  // @formatter:off
  @Operation(
      summary = "Returns a list of all gear",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of gear is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Gear.class))),
          @ApiResponse(
              responseCode = "404",
              description = "No gear was found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/gear", method = RequestMethod.GET)
  List<Gear> allGear();
  
  @Operation(
      summary = "Returns a list of all jobs",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of jobs is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Job.class))),
          @ApiResponse(
              responseCode = "404",
              description = "No jobs were found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/jobs", method = RequestMethod.GET)
  List<Job> allJobs();
  // @formatter:on
}
