package com.example.itemsservice.controller;

import com.example.itemsservice.model.Item;
import com.example.itemsservice.model.ItemRequest;
import com.example.itemsservice.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/items/")
public class ItemController {

    private final ItemService itemService;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        log.info("Received a request to retrieve item.");
        Optional<Item> retrievedItem = itemService.retrieveItem(id);
        return retrievedItem.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody ItemRequest itemRequest) {
        log.info("Received a request with item data to create new item.");
        try {
            var item = itemService.createItem(itemRequest);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
