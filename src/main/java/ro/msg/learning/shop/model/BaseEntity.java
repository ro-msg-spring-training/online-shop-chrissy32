package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected ID ID;

    @Override
    public String toString() {
        return "BaseEntity --- ID: " + this.ID;
    }
}
