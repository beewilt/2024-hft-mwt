package de.hft.moderndistributedsystemshft24project;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@Log
@ComponentScan(basePackages = "de.openapitools.configuration")
public class ShoppingItemsController implements ApiApiDelegate {

    private final ShoppingItemsRepository shoppingItemsRepository;

    public ShoppingItemsController(ShoppingItemsRepository shoppingItemsRepository) {
        this.shoppingItemsRepository = shoppingItemsRepository;
    }



    @Override
    public ResponseEntity<ShoppingItem> addItem(ShoppingItem shoppingItem) {
        //log.info("Adding item: {}", shoppingItem);
        return shoppingItemsRepository.findByNameIgnoreCase(shoppingItem.getName())
                .map(item -> {
                    item.setAmount(item.getAmount() + shoppingItem.getAmount());
                 //   log.info("Updated item: {}", item);
                    return new ResponseEntity<>(shoppingItemsRepository.save(item), HttpStatus.OK);
                })
                .orElseGet(() -> {
                //    log.info("Creating new item: {}", shoppingItem);
                    return new ResponseEntity<>(shoppingItemsRepository.save(shoppingItem), HttpStatus.CREATED);
                });
    }

    @Override
    public ResponseEntity<Void> deleteItem(String name) {
      //  log.info("Deleting item with name: {}", name);
        return shoppingItemsRepository.findByNameIgnoreCase(name)
                .map(itemToDelete -> {
                    shoppingItemsRepository.delete(itemToDelete);
                  //  log.info("Deleted item: {}", itemToDelete);

                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElseGet(() -> {
               //     log.warn("Item not found: {}", name);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public ResponseEntity<List<ShoppingItem>> getAllItems() {
    //    log.info("Fetching all items");
        return new ResponseEntity<>(shoppingItemsRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShoppingItem> getItemByName(String name) {
      //  log.info("Fetching item with name: {}", name);
        return shoppingItemsRepository.findByNameIgnoreCase(name)
                .map(item -> {
           //         log.info("Found item: {}", item);
                    return new ResponseEntity<>(item, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ShoppingItem> updateItem(String name, ShoppingItem shoppingItem) {
     //   log.info("Updating item with name: {}", name);
        return shoppingItemsRepository.findByNameIgnoreCase(name)
                .map(itemToUpdate -> {
                    itemToUpdate.setName(shoppingItem.getName());
                    itemToUpdate.setAmount(shoppingItem.getAmount());
                //    log.info("Updated item: {}", itemToUpdate);
                    return new ResponseEntity<>(shoppingItemsRepository.save(itemToUpdate), HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
