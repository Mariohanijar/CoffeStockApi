package coffe_stock_api.modules.product.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.dto.ProductResponseDTO;
import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class ListProductsByPriceUseCase {
    @Autowired ProductRepository productRepository;

    public List<ProductResponseDTO> execute(float price){
        List<ProductEntity> productsPrice = productRepository.findByPriceLessThanEqual(price);

        if(productsPrice.isEmpty()){
            throw new RuntimeException("Not found products with this budget");
        }

        return productsPrice.stream()
        .map(ProductResponseDTO::fromEntity)
        .toList();
    }
}
