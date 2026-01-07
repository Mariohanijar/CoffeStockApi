package coffe_stock_api.modules.product.entitie;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Data;

@Data
@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    private String category;
    
    @Min(value = 0, message = "O valor não pode ser menor que 0")
    @Max(value = 130, message = "O valor não pode ser maior que 130")
    private float price;
    @Min(value = 0)
    @Column(name="quantity")
    private int quantity;
    
    @CreationTimestamp
    private LocalDateTime created_at;

    
}
