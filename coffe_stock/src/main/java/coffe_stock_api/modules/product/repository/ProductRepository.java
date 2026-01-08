package coffe_stock_api.modules.product.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import coffe_stock_api.modules.product.entitie.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{
    boolean existsByNameIgnoreCase(String name);

    Optional<ProductEntity> findByNameIgnoreCase(String name);

    List<ProductEntity> findByCategoryIgnoreCase(String category);

    List<ProductEntity> findByPriceLessThanEqual(float price);
}
