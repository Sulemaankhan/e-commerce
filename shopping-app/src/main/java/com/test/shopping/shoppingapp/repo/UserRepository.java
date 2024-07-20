package com.test.shopping.shoppingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.shopping.shoppingapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String userName, String password);

}
