package coffe_stock_api.modules.product.useCase;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class ListByCategory {
    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> execute(String productCategory){
        
        String categoryToSearch = productCategory.replace("-", " ").trim();
        
        List<ProductEntity> category = productRepository.findByCategoryIgnoreCase(categoryToSearch);

        if(category.isEmpty()){
            throw new RuntimeException("category "+ productCategory + " was not found in the database");
        }
        return category;
    }
}
