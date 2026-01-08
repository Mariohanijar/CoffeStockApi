package coffe_stock_api.modules.product.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.repository.ProductRepository;

@Service
public class ConsumeProductUseCase {
    @Autowired ProductRepository productRepository;

    public ProductEntity execute(String productName, int amountToConsume){
        
        var product = productRepository.findByNameIgnoreCase(productName)
        .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));
        
        if (product.getQuantity() < amountToConsume){
            throw new RuntimeException("Stock insufficient! the actual amount is: " + product.getQuantity());
        }

        int newQuantity = product.getQuantity()-amountToConsume;
        product.setQuantity(newQuantity);

        return productRepository.save(product);
    }
}
