package com.fancystore.inventory.service;


import com.fancystore.inventory.invetory.InventoryRepository;
import com.fancystore.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    public boolean isInStock(String skuCode) {
        Optional<Inventory> bySkuCode = this.inventoryRepository.findBySkuCode(skuCode);
        return bySkuCode.isPresent();
    }
}
