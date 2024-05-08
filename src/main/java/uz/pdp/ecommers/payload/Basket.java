package uz.pdp.ecommers.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommers.entity.Product;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Basket {

    private Map<Product, Integer> basketProducts =  new LinkedHashMap<>();
}
