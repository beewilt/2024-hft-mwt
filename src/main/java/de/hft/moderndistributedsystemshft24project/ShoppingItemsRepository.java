package de.hft.moderndistributedsystemshft24project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingItemsRepository extends JpaRepository<ShoppingItem, Long> {
    Optional<ShoppingItem> findByNameIgnoreCase(String name);
}
