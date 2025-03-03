package com.test.shopping.shoppingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.shopping.shoppingapp.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	List<Orders> findByUserId(long userId);

}
