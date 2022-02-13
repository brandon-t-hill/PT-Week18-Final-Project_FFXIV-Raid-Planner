package com.promineotech.xivraidplanner.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.entity.UserInputModel;
import com.promineotech.xivraidplanner.entity.UserJob;
import com.promineotech.xivraidplanner.entity.UserJobGear;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@OpenAPIDefinition(info = @Info(title = "XIV Raid Team"),
    servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})
public interface UserController {
  // @formatter:off
  @Operation(
      summary = "Returns a list of users",
      description = "Returns a list of all the users",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of users is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
          @ApiResponse(
              responseCode = "404",
              description = "No users were found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      }
      )
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  List<User> all();
  
  @Operation(
      summary = "Returns a user",
      description = "Returns a user from a specified username",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A user is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
          @ApiResponse(
              responseCode = "404",
              description = "The user with the given username was not found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
  User getUser(@PathVariable String username);
  
  @Operation(
      summary = "Creates a new user",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A user was created",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "400",
              description = "The user could not be created due to invalid or already existing input",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
          
      })
  @RequestMapping(value = "/users", method = RequestMethod.POST)
  @ResponseStatus(code = HttpStatus.CREATED)
  public User create(@RequestBody UserInputModel input);
  
  @Operation(
      summary = "Updates a user",
      description = "Takes a username and if found updates the character name of the user",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "User info was updated",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "400",
              description = "A user could not be found with that username",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
  public User update(@PathVariable String username, String charname);
  
  @Operation(
      summary = "Deletes a user by their username",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "User was deleted",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "400",
              description = "A user could not be found with that username",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
  public User delete(@PathVariable String username);
  
  @Operation(
      summary = "Retrieves a list of the jobs assigned to a user",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of User Jobs was returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = UserJob.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The username was invalid",
              content = @Content(mediaType = "applicaiton/json")),
          @ApiResponse(
              responseCode = "404",
              description = "The specified user could not be found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/users/{username}/jobs", method = RequestMethod.GET)
  public List<UserJob> allUserJobs(@PathVariable String username);
  
  @Operation(
      summary = "Retrieves a specific job of a specific user")
  @RequestMapping(value = "/users/{username}/jobs/{jobid}", method = RequestMethod.GET)
  public UserJob getUserJob(@PathVariable String username, @PathVariable String jobid);
  
  @Operation(
      summary = "Retrieves of all the gear equipped to a specified job of a specified user",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of User job gear was returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = UserJobGear.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The username or jobid was invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "The specified user could not be found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(mediaType = "application/json"))
      })
  @RequestMapping(value = "/users/{username}/jobs/{jobid}/gear", method = RequestMethod.GET)
  public List<UserJobGear> allUserJobGear(@PathVariable String username,@PathVariable String jobid);
  // @formatter:on
}
