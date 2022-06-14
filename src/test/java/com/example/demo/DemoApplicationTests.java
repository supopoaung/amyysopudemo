package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.PaySlipController;

@WebMvcTest(PaySlipController.class)
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
