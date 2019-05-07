package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDTO implements Serializable {
    protected Integer ID;
}