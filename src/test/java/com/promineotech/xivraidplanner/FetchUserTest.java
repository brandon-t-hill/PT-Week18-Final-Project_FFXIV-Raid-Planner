package com.promineotech.xivraidplanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.promineotech.xivraidplanner.entity.User;
import com.promineotech.xivraidplanner.support.FetchUserTestSupport;

class FetchUserTest extends FetchUserTestSupport {

  @Test
  void testThatAUserIsReturnedWhenAUsernameIsSupplied() {
    /*
    // Given: Valid Username
    String username = "Eremita";
    String uri = String.format("%s%s", getBaseUri(), username);
    // When: A connection is made to the URI
    ResponseEntity<User> response = getRestTemplate().getForEntity(uri, User.class);
    // Then: a success (OK - 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    // And: the actual list returned is the same as the expected list
    User expected = new User(4, "Eremita", "Eremita Celesturia");
    assertThat(response.getBody()).isEqualTo(expected);
    /**/
    fail("Not Implemented");
  }

  @Test
  void testThatAListOfUsersIsReturnedWhenAllIsCalled() {
    String uri = String.format("%susers", getBaseUri());

    ResponseEntity<List<User>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<User>>() {});
    
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
