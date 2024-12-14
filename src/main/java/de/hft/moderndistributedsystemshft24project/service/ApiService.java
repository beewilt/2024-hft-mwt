package de.hft.moderndistributedsystemshft24project.service;

import de.hft.moderndistributedsystemshft24project.repository.ApiRepository;
import lombok.AllArgsConstructor;
import org.openapitools.api.ApiApi;
import org.openapitools.api.ApiApiDelegate;
import org.openapitools.model.ShoppingItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

//@AllArgsConstructor
@Service
public class ApiService implements ApiApi {

    private final ApiRepository apiRepository;

    public ApiService(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }



    @Override
    public ResponseEntity<List<ShoppingItem>> getAllItems() {
        return new ResponseEntity<>(apiRepository.findAll(), HttpStatus.OK);
    }


}
