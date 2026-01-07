package coffe_stock_api.modules.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import coffe_stock_api.modules.product.entitie.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{
    boolean existsByNameIgnoreCase(String name);
}
