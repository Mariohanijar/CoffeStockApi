package coffe_stock_api.modules.product.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class CreateProductUseCase {
    @Autowired
    ProductRepository productRepository;

    public ProductEntity execute (ProductEntity product){
        if(productRepository.existsByNameIgnoreCase(product.getName())){
            throw new RuntimeException("Error: It's already exists a product with this name");
        }
        return productRepository.save(product);
    }
}
