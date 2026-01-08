package coffe_stock_api.modules.product.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class ListAllProductsUseCase {
    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> execute(){
        return productRepository.findAll();
    }
}
