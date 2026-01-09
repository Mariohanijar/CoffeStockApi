package coffe_stock_api.modules.product.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.dto.ProductResponseDTO;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class DeleteProductUseCase {
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO execute(String name){
      var product =  productRepository.findByNameIgnoreCase(name).orElseThrow(()->new RuntimeException("product not found"));
    
      var productDeleted = ProductResponseDTO.fromEntity(product);
      productRepository.delete(product);

      return productDeleted;
    
    }

}
