package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.msg.learning.shop.Model.ProductCategory;
import ro.msg.learning.shop.UI.UI;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext context=
//				new AnnotationConfigApplicationContext(
//						"Configuration"
//				);
//
//		UI ui = context.getBean(UI.class);
//		Iterable<ProductCategory> pc = ui.productCategories();
//		pc.forEach(pc1-> System.out.println(pc1.toString()));
		SpringApplication.run(ShopApplication.class, args);
	}

}
