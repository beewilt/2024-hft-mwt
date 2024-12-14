package de.hft.moderndistributedsystemshft24project.repository;

import org.openapitools.model.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiRepository extends JpaRepository<ShoppingItem, Long> {
    Optional<ShoppingItem> findByNameIgnoreCase(String name);
}
