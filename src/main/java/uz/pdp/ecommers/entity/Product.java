package uz.pdp.ecommers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommers.repo.CategoryRepo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer categoryId;
    private byte[] photo;


    public String getCategoryById(Integer categoryId) {
        Category category = CategoryRepo.findById(categoryId);
        return category.getName();
    }
}
