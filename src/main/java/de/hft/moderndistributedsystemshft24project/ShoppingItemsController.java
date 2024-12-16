package de.hft.moderndistributedsystemshft24project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@ComponentScan(basePackages = "de.openapitools.configuration")
public class ShoppingItemsController implements ApiApiDelegate {

    private final ShoppingItemsRepository shoppingItemsRepository;

    public ShoppingItemsController(ShoppingItemsRepository shoppingItemsRepository) {
        this.shoppingItemsRepository = shoppingItemsRepository;
    }



    @Override
    public ResponseEntity<ShoppingItem> addItem(ShoppingItem shoppingItem) {
        return shoppingItemsRepository.findByNameIgnoreCase(shoppingItem.getName())
                .map(item -> {
                    item.setAmount(item.getAmount() + shoppingItem.getAmount());
                    return new ResponseEntity<>(shoppingItemsRepository.save(item), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(shoppingItemsRepository.save(shoppingItem), HttpStatus.CREATED));
    }

    @Override
    public ResponseEntity<Void> deleteItem(String name) {
        return shoppingItemsRepository.findByNameIgnoreCase(name)
                .map(itemToDelete -> {
                    shoppingItemsRepository.delete(itemToDelete);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<ShoppingItem>> getAllItems() {
        return new ResponseEntity<>(shoppingItemsRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShoppingItem> getItemByName(String name) {
        return shoppingItemsRepository.findByNameIgnoreCase(name)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ShoppingItem> updateItem(String name, ShoppingItem shoppingItem) {
        return shoppingItemsRepository.findByNameIgnoreCase(name)
                .map(itemToUpdate -> {
                    itemToUpdate.setName(shoppingItem.getName());
                    itemToUpdate.setAmount(shoppingItem.getAmount());
                    return new ResponseEntity<>(shoppingItemsRepository.save(itemToUpdate), HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
