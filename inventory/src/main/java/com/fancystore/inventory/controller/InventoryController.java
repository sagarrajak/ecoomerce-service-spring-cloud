package com.fancystore.inventory.controller;


import com.fancystore.inventory.exception.CustomIllegalArgumentException;
import com.fancystore.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
        return this.inventoryService.isInStock(skuCode);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Boolean> isItemsIsInStockStock(
        @RequestParam("sku-code") Optional<List<String>> skuCode
    ) throws CustomIllegalArgumentException {
        return skuCode
                .map(e -> this.inventoryService.isInStock(e))
                .orElseThrow(() -> new CustomIllegalArgumentException("sku-code not provided!"));
    }
}
