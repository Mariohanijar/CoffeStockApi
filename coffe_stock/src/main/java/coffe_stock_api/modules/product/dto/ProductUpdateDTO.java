package coffe_stock_api.modules.product.dto;

import java.util.UUID;

public record ProductUpdateDTO(UUID id, String name, String category, float price, int quantity) {
    

}
