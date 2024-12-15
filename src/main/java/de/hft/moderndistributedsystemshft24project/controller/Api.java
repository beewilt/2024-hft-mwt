package de.hft.moderndistributedsystemshft24project.controller;

import de.hft.moderndistributedsystemshft24project.repository.ApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.ApiApi;
import org.openapitools.model.ShoppingItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Api implements ApiApi {

    private final ApiRepository apiRepository;

    public Api(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public ResponseEntity<ShoppingItem> addItem(ShoppingItem shoppingItem) {
        return apiRepository.findByNameIgnoreCase(shoppingItem.getName())
                .map(item -> {
                    item.setAmount(item.getAmount() + shoppingItem.getAmount());
                    return new ResponseEntity<>(apiRepository.save(item), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(apiRepository.save(shoppingItem), HttpStatus.CREATED));
    }

    @Override
    public ResponseEntity<Void> deleteItem(String name) {
        return apiRepository.findByNameIgnoreCase(name)
                .map(itemToDelete -> {
                    apiRepository.delete(itemToDelete);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<ShoppingItem>> getAllItems() {
        return new ResponseEntity<>(apiRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShoppingItem> getItemByName(String name) {
        return apiRepository.findByNameIgnoreCase(name)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ShoppingItem> updateItem(String name, ShoppingItem shoppingItem) {
        return apiRepository.findByNameIgnoreCase(name)
                .map(itemToUpdate -> {
                    itemToUpdate.setName(shoppingItem.getName());
                    itemToUpdate.setAmount(shoppingItem.getAmount());
                    return new ResponseEntity<>(apiRepository.save(itemToUpdate), HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
