package com.poly.gestioncatalogue5gr1;


import com.poly.gestioncatalogue5gr1.dao.CategorieRepository;
import com.poly.gestioncatalogue5gr1.dao.ProduitRepository;
import com.poly.gestioncatalogue5gr1.entities.Categorie;
import com.poly.gestioncatalogue5gr1.entities.Produit;
import com.poly.gestioncatalogue5gr1.security.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@AllArgsConstructor
public class GestionCatalogue5Gr1Application implements CommandLineRunner{
   // @Autowired
   // private ProduitRepository produitRepository;
   // @Autowired
    //private CategorieRepository categorieRepository;
    public static void main(String[] args) {
        SpringApplication.run(GestionCatalogue5Gr1Application.class, args);    }
    @Override
    public void run(String... args) throws Exception {
       // categorieRepository.save(new Categorie(null,"informatique",null));
        //categorieRepository.save(Categorie.builder().nom("electronique").build());
   //    produitRepository.save(new Produit(null,"imprimante",500,10,categorieRepository.findById(1L).get()));
    //    produitRepository.save(new Produit(null,"machine a laver",500,10,categorieRepository.findById(2L).get()));
     //   produitRepository.save(new Produit(null,"clavier",50,100,categorieRepository.findById(1L).get()));
     //   produitRepository.save(new Produit(null,"souris",50,100,categorieRepository.findById(1L).get()));
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(IAccountService accountService)
    {
        return  args -> {
                 accountService.addRole("USER");
                 accountService.addRole("ADMIN");
                 accountService.addUser("user","123","user@gmail.com");
                 accountService.addUser("admin","123","admin@gmail.com");
                 accountService.addroleToUser("user","USER");
                 accountService.addroleToUser("admin","ADMIN");
                 accountService.addroleToUser("admin","USER");



        };
    }
}
