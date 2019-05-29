package ro.msg.learning.shop.controller;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@Profile(value = "test")
// for some reason, this class in not recognized in the test package. However, it is only active on profile test, so it doesn't influence
// anything on the development profile.
public class TestController {
    private Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:test", "sa", "").load();

    @GetMapping("/tests/populate")
    public void populate() {
        flyway.migrate();
    }

    @GetMapping("/tests/clear")
    public void clear() {
        flyway.clean();
    }
}
