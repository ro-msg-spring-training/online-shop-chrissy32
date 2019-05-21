//package ro.msg.learning.shop;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ShopApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//}

package ro.msg.learning.shop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.annotation.Profile;
import ro.msg.learning.shop.integrationTests.OrderCreationIntegrationTest;
import ro.msg.learning.shop.unitTests.MostAbundantUnitTest;
import ro.msg.learning.shop.unitTests.SingleLocationUnitTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		MostAbundantUnitTest.class,
		SingleLocationUnitTest.class,
		OrderCreationIntegrationTest.class
})
public class ShopApplicationTests {
}
