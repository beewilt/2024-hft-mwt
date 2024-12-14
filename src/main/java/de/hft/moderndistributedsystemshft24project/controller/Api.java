package de.hft.moderndistributedsystemshft24project.controller;

import de.hft.moderndistributedsystemshft24project.repository.ApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.ApiApi;
import org.openapitools.api.ApiApiDelegate;
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
        ShoppingItem sh = apiRepository.save(shoppingItem);
        return new ResponseEntity<ShoppingItem>(sh, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ShoppingItem>> getAllItems() {
        return new ResponseEntity<>(apiRepository.findAll(), HttpStatus.OK);
    }




}
