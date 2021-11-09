package com.sessiones8910.Laptops;

import com.sessiones8910.Laptops.entities.Laptop;
import com.sessiones8910.Laptops.services.LaptopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopsApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LaptopsApplication.class, args);
		LaptopService service = context.getBean(LaptopService.class);

		Laptop laptop1 = new Laptop(null, "MacBook Air", "Apple", 250, 8, 13);

		service.create(laptop1);
	}

}
