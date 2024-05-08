package uz.pdp.ecommers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BasketProduct {
    private Integer id;
    private Integer productId;
    private int amount;
    private Integer basketId;

}
