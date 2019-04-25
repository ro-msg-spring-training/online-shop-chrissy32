package ro.msg.learning.shop.Configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "ro.msg.learning.shop.JPARepos")
public class PersistenceConfig {
}
