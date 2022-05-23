package ru.otus.lhfvgbh.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lhfvgbh.prototype.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.isAvailable=false WHERE p.id = :id")
    void disable(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.isAvailable=true WHERE p.id = :id")
    void enable(@Param("id") Long id);

}
