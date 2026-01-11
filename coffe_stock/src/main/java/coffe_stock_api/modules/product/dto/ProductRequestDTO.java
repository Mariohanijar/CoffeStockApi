package coffe_stock_api.modules.product.dto;

public record ProductRequestDTO(String name,
    String category,
    float price,
    int quantity) {
    
}
