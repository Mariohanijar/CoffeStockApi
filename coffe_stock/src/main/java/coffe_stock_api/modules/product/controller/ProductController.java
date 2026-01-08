package coffe_stock_api.modules.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.useCase.ConsumeProductUseCase;
import coffe_stock_api.modules.product.useCase.CreateProductUseCase;
import coffe_stock_api.modules.product.useCase.ListAllProductsUseCase;
import coffe_stock_api.modules.product.useCase.ListByCategory;
import coffe_stock_api.modules.product.useCase.ListProductsByPriceUseCase;
import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @Autowired
    private ConsumeProductUseCase consumeProductUseCase;

    @Autowired
    private ListAllProductsUseCase listAllProductsUseCase;

    @Autowired
    private ListByCategory listByCategory;

    @Autowired
    private ListProductsByPriceUseCase listProductsByPriceUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> post(@Valid @RequestBody ProductEntity product) {
        try {
            var result = createProductUseCase.execute(product);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping("/consume") 
    public ResponseEntity<Object> consume(@RequestBody Map<String, Object> request) {
        try {
            String name = (String) request.get("name");
            int amount = (int) request.get("amount");

            var updatedProduct = consumeProductUseCase.execute(name, amount);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/list/{name}")
    public ResponseEntity<Object> getByCategory(@PathVariable("name") String name) {
    try {
        var products = listByCategory.execute(name);
        return ResponseEntity.ok(products);
    }catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }   

    @GetMapping("/list/price/{number}")
    public ResponseEntity<Object> getByPrice(@PathVariable("number") float number){
        try {
            var products = listProductsByPriceUseCase.execute(number);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
public ResponseEntity<Object> listAll() {
    try {
        var products = listAllProductsUseCase.execute();
        return ResponseEntity.ok().body(products);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

}
