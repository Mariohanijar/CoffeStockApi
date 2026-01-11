package coffe_stock_api.modules.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffe_stock_api.modules.product.dto.ProductConsumeDTO;
import coffe_stock_api.modules.product.dto.ProductRequestDTO;
import coffe_stock_api.modules.product.dto.ProductResponseDTO;
import coffe_stock_api.modules.product.dto.ProductUpdateDTO;
import coffe_stock_api.modules.product.entitie.ProductEntity;
import coffe_stock_api.modules.product.useCase.ConsumeProductUseCase;
import coffe_stock_api.modules.product.useCase.CreateProductUseCase;
import coffe_stock_api.modules.product.useCase.DeleteProductUseCase;
import coffe_stock_api.modules.product.useCase.ListAllProductsUseCase;
import coffe_stock_api.modules.product.useCase.ListByCategory;
import coffe_stock_api.modules.product.useCase.ListProductsByPriceUseCase;
import coffe_stock_api.modules.product.useCase.UpdateProductUseCase;
import io.micrometer.core.ipc.http.HttpSender.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private DeleteProductUseCase deleteProductUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> post(@Valid @RequestBody ProductRequestDTO product) {
    try {
        ProductEntity newProduct = createProductUseCase.execute(product);
        var result = ProductResponseDTO.fromEntity(newProduct);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @PatchMapping("/consume") 
    public ResponseEntity<Object> consume(@RequestBody ProductConsumeDTO request) {
        try {
            String name = (String) request.name();
            int amount = (int) request.quantityToConsume();

            var updatedProduct = consumeProductUseCase.execute(name, amount);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @Tag(name = "lists", description = "Types of searches to the products")
    @Operation(summary = "List all the products by category")
    @GetMapping("/list/{name}")
    public ResponseEntity<Object> getByCategory(@PathVariable("name") String name) {
    try {
        var products = listByCategory.execute(name);
        return ResponseEntity.ok(products);
    }catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }   

    @Tag(name = "lists")
    @Operation(summary = "List all the products by price")
    @GetMapping("/list/price/{number}")
    public ResponseEntity<Object> getByPrice(@PathVariable("number") float number){
        try {
            var products = listProductsByPriceUseCase.execute(number);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @Tag(name = "lists")
    @Operation(summary = "List all the products")
    @GetMapping("/list")
public ResponseEntity<Object> listAll() {
    try {
        var products = listAllProductsUseCase.execute();
        return ResponseEntity.ok().body(products);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    @DeleteMapping("/delete/{product}")
    public ResponseEntity<Object> delete(@PathVariable("product") String product){
        try {
           var productDeleted = deleteProductUseCase.execute(product);
            
           System.out.println("Produto deletado com sucesso");

            return ResponseEntity.ok(productDeleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<Object> update(@RequestBody ProductUpdateDTO productUpdateDTO){
        try {
            var productUpdated = updateProductUseCase.execute(productUpdateDTO);

            return ResponseEntity.ok().body(productUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
