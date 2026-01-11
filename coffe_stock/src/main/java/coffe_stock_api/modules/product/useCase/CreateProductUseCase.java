package coffe_stock_api.modules.product.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.dto.ProductRequestDTO;
import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class CreateProductUseCase {
    @Autowired
    ProductRepository productRepository;

    
    public ProductEntity execute(ProductRequestDTO product) {
        if(productRepository.existsByNameIgnoreCase(product.name())) {
            throw new RuntimeException("Error: It's already exists a product with this name");
        }
        var productEntity = new ProductEntity();
        productEntity.setName(product.name());
        productEntity.setCategory(product.category());
        productEntity.setPrice(product.price());
        productEntity.setQuantity(product.quantity());

        return productRepository.save(productEntity);
    }
}
