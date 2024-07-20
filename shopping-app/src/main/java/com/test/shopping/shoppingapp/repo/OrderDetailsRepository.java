package com.test.shopping.shoppingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.shopping.shoppingapp.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
