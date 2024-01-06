package com.fancystore.inventory.controller;


import com.fancystore.inventory.exception.CustomIllegalArgumentException;
import com.fancystore.inventory.service.InventoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
        return this.inventoryService.isInStock(skuCode);
    }


    //    @SneakyThrows
    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Boolean> isItemsIsInStockStock(
        @RequestParam("sku-code") Optional<List<String>> skuCode
    ) throws CustomIllegalArgumentException {
//        log.info("Wait time started!");
//        Thread.sleep(1000);
//        log.info("Wait time finished!");
        return skuCode
                .map(e -> this.inventoryService.isInStock(e))
                .orElseThrow(() -> new CustomIllegalArgumentException("sku-code not provided!"));
    }
}
