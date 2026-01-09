package coffe_stock_api.modules.product.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.dto.ProductResponseDTO;
import coffe_stock_api.modules.product.dto.ProductUpdateDTO;
import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class UpdateProductUseCase {
    @Autowired
    ProductRepository productRepository;

    
    public ProductResponseDTO execute(ProductUpdateDTO productUpdateDTO){
      var product = productRepository.findById(productUpdateDTO.id()).orElseThrow(()-> new RuntimeException("Id do produto não encontrado"));
      
      
      product.setName(productUpdateDTO.name());  
      product.setCategory(productUpdateDTO.category());
      product.setPrice(productUpdateDTO.price());
      product.setQuantity(productUpdateDTO.quantity());
      product.setId(productUpdateDTO.id());
        
      productRepository.save(product);

        return ProductResponseDTO.fromEntity(product);
    }

}
