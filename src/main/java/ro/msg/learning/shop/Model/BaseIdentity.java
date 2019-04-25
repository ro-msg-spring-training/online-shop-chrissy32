package ro.msg.learning.shop.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class BaseIdentity<T, U> implements Serializable {
    @Column
    private T firstID;
    @Column
    private U secondID;

    @Override
    public String toString() {
        return "BaseIdentity --- firstID: " + this.firstID + ", secondID: " + this.secondID;
    }
}
