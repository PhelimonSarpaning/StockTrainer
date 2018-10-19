package com.JBank.src;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.JBank.src.controllers.UsersController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockTrainerApplicationTests {

	@Autowired
	private UsersController testController; // as no home controller
	
	@Test
	public void contextLoads() {
		assertThat(testController).isNotNull();
	}

}
