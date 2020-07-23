package ro.msg.learning.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		/*System.out.println("hello world, I have just started up");
		ProductCategory pc1 = ProductCategory.builder().name("aa").build();
		Product p1 = Product.builder().name("a")
				.build();
		p1.setProductCategory(pc1);
		rep1.save(p1);

		Product p2 = Product.builder().name("b")
				.build();
		p2.setProductCategory(pc1);
		rep1.save(p2);*/

	}
}
