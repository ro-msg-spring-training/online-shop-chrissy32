package ro.msg.learning.shop.controller;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@Profile(value = "test")
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
