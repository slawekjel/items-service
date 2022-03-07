package com.example.itemsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRequest {
    private String name;
    private String description;
    private String producer;
    private String category;
}
