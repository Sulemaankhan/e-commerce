//package com.test.shopping.shoppingapp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.test.shopping.shoppingapp.feignclient.BankClient;
//
//@RestController
//@RequestMapping("loadBalance")
//public class LoadBalanceController {
//
//	@Autowired
//	private BankClient bankClient;
//
//	@GetMapping("port")
//	public ResponseEntity<String> getPort() {
//		return new ResponseEntity<String>(bankClient.getInfo(), HttpStatus.OK);
//	}
//
//}
