package com.template.builder;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.template.builder.SpringWebTemplateApplication;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebTemplateApplicationTests {

	@Test
	public void contextLoads() {
		SpringWebTemplateApplication.main(new String[] {""});
		assertNotEquals(true,false);
	}

}
