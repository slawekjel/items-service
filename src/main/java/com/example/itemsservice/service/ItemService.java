package com.example.itemsservice.service;

import com.example.itemsservice.model.Item;
import com.example.itemsservice.model.ItemRequest;
import com.example.itemsservice.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Optional<Item> retrieveItem(Long id) {
        log.info("Retrieving item with id: {}", id);
        return itemRepository.findById(id);
    }

    public Item createItem(ItemRequest itemRequest) {
        log.info("Creating new item");
        var item = new Item(
                itemRequest.getName(), itemRequest.getDescription(), itemRequest.getProducer(), itemRequest.getCategory());
        return itemRepository.save(item);
    }

}
