package uz.pdp.ecommers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommers.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private LocalDateTime dateTime;
    private Status status;
    private UUID userId;
    private Integer price;
}
