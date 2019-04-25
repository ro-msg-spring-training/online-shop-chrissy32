package ro.msg.learning.shop.JPARepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.msg.learning.shop.Model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface IShopRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
