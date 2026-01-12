package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Part E: Add sample inventory only if both parts and products lists are empty
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            
            // Create 5 sample parts for guitar shop
            OutsourcedPart strings = new OutsourcedPart();
            strings.setCompanyName("D'Addario");
            strings.setName("Guitar Strings Set");
            strings.setInv(50);
            strings.setPrice(12.99);
            strings.setMinInv(10);
            strings.setMaxInv(100);
            partRepository.save(strings);

            InhousePart pickup = new InhousePart();
            pickup.setPartId(1001);
            pickup.setName("Humbucker Pickup");
            pickup.setInv(30);
            pickup.setPrice(89.99);
            pickup.setMinInv(5);
            pickup.setMaxInv(50);
            partRepository.save(pickup);

            OutsourcedPart tuners = new OutsourcedPart();
            tuners.setCompanyName("Grover");
            tuners.setName("Locking Tuners");
            tuners.setInv(25);
            tuners.setPrice(45.50);
            tuners.setMinInv(5);
            tuners.setMaxInv(40);
            partRepository.save(tuners);

            InhousePart bridge = new InhousePart();
            bridge.setPartId(1002);
            bridge.setName("Tremolo Bridge");
            bridge.setInv(20);
            bridge.setPrice(75.00);
            bridge.setMinInv(5);
            bridge.setMaxInv(30);
            partRepository.save(bridge);

            OutsourcedPart amp = new OutsourcedPart();
            amp.setCompanyName("Fender");
            amp.setName("Practice Amplifier");
            amp.setInv(15);
            amp.setPrice(129.99);
            amp.setMinInv(3);
            amp.setMaxInv(25);
            partRepository.save(amp);

            // Create 5 sample products for guitar shop
            Product strat = new Product("Stratocaster Style Electric Guitar", 899.99, 5);
            productRepository.save(strat);
            strings.getProducts().add(strat);
            pickup.getProducts().add(strat);
            tuners.getProducts().add(strat);
            partRepository.save(strings);
            partRepository.save(pickup);
            partRepository.save(tuners);

            Product lespaul = new Product("Les Paul Style Electric Guitar", 1299.99, 3);
            productRepository.save(lespaul);
            strings.getProducts().add(lespaul);
            pickup.getProducts().add(lespaul);
            partRepository.save(strings);
            partRepository.save(pickup);

            Product acoustic = new Product("Acoustic Dreadnought Guitar", 599.99, 8);
            productRepository.save(acoustic);
            strings.getProducts().add(acoustic);
            tuners.getProducts().add(acoustic);
            partRepository.save(strings);
            partRepository.save(tuners);

            Product semihollow = new Product("Semi-Hollow Body Guitar", 1499.99, 2);
            productRepository.save(semihollow);
            strings.getProducts().add(semihollow);
            pickup.getProducts().add(semihollow);
            bridge.getProducts().add(semihollow);
            partRepository.save(strings);
            partRepository.save(pickup);
            partRepository.save(bridge);

            Product metal = new Product("7-String Metal Guitar", 1099.99, 4);
            productRepository.save(metal);
            strings.getProducts().add(metal);
            pickup.getProducts().add(metal);
            tuners.getProducts().add(metal);
            bridge.getProducts().add(metal);
            partRepository.save(strings);
            partRepository.save(pickup);
            partRepository.save(tuners);
            partRepository.save(bridge);

            System.out.println("Sample guitar shop inventory has been loaded.");
        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: "+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: "+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}