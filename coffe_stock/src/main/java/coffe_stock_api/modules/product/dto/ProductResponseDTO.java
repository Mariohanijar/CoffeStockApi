package coffe_stock_api.modules.product.dto;

import java.util.UUID;

import coffe_stock_api.modules.product.entitie.ProductEntity;

public record ProductResponseDTO(
    UUID id,
    String name,
    String category,
    float price,
    int quantity
) {

    public static ProductResponseDTO fromEntity(ProductEntity entity) {
        return new ProductResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getCategory(),
            entity.getPrice(),
            entity.getQuantity()
        );
    }
}
