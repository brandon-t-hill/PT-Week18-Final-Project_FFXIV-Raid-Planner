package com.promineotech.xivraidplanner.support;

import java.util.LinkedList;
import java.util.List;
import com.promineotech.xivraidplanner.entity.User;

public class FetchUserTestSupport extends BaseTest {
  protected List<User> buildExpectedOfAll() {
    List<User> list = new LinkedList<>();
    list.add(User.builder().user_pk(1).username("Beef").charname("Beef Skillet").build());
    list.add(User.builder().user_pk(2).username("Chrono").charname("Chrono Elric").build());
    list.add(User.builder().user_pk(3).username("Xabre").charname("Xabre").build());
    list.add(User.builder().user_pk(4).username("Eremita").charname("Eremita Celesturia").build());
    list.add(User.builder().user_pk(5).username("Makoto").charname("Makoto").build());
    list.add(User.builder().user_pk(6).username("Teo").charname("Teo Kroll").build());
    list.add(User.builder().user_pk(7).username("Skylar").charname("Skylar").build());
    list.add(User.builder().user_pk(8).username("Seamus").charname("Seamus").build());
    return list;
  }
}
