package com.fancystore.inventory.bootstrap;


import com.fancystore.inventory.invetory.InventoryRepository;
import com.fancystore.inventory.model.Inventory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class Bootstrapdata implements CommandLineRunner {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Inventory>> typeReference = new TypeReference<List<Inventory>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/inventory.json");
        try {
            List<Inventory> inventories = mapper.readValue(inputStream, typeReference);
            System.out.println("Inventories : "+inventories);
            inventoryRepository.saveAll(inventories);
        }
        catch (IOException err) {
            System.out.println("Unable to read file!");
        }
        catch (Exception e) {
            System.out.println("Exception e");
            e.printStackTrace();
        }
    }
}
