package coffe_stock_api.modules.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.useCase.CreateProductUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> post(@Valid @RequestBody ProductEntity product) {
        try {
            var result = createProductUseCase.execute(product);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
