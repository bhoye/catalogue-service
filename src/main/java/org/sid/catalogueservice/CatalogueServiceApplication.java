package org.sid.catalogueservice;

import org.sid.catalogueservice.dao.CategoryRepository;
import org.sid.catalogueservice.dao.ProductRepository;
import org.sid.catalogueservice.entities.Category;
import org.sid.catalogueservice.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class  CatalogueServiceApplication implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Product.class,Category.class);
        System.out.println("Ajouter deux catégories");
        categoryRepository.save(new Category(null,"Computers",null)) ;
        categoryRepository.save(new Category(null,"Printers",null)) ;
        System.out.println("------------------------------------");
        System.out.println("Ajout de produits");
        Category c1 = categoryRepository.findById(1L).get();
        productRepository.save(new Product(null,"HP 5432",65400,c1));
        productRepository.save(new Product(null,"Macbook 123",123400,c1));
        productRepository.save(new Product(null,"DEL 5643",34400,c1));
        Category c2 = categoryRepository.findById(2L).get();
        productRepository.save(new Product(null,"Printer HP 5432",65400,c2));
        productRepository.save(new Product(null,"Printer Epson",123400,c2));
        System.out.println("----------------------------------------");
        productRepository.findAll().forEach(p->{
            System.out.println(p.getName());
        });
    }
}
