package com.valerianosoft.mutanttest.service;

import com.valerianosoft.mutanttest.model.User;

public class UserService {

  public User create(final String name, final Integer age) {
    final var user = new User();
    user.setName(name);
    user.setAge(age);

    return user;
  }

}
