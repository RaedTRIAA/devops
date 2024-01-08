package com.poly.gestioncatalogue5gr1.restcontroleur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.gestioncatalogue5gr1.entities.Produit;
import com.poly.gestioncatalogue5gr1.service.IServiceProduit;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
//@RequestMapping("/api")
public class ProduitRestController {
   IServiceProduit serviceProduit;
    @GetMapping("/all")
    public List<Produit> getAllProducts(@RequestParam(name = "mc",defaultValue ="" ) String mc,
                                        @RequestParam(name="page",defaultValue = "0")int page,
                                        @RequestParam(name="size",defaultValue = "5")int size)
    {
        //List<Produit> liste=serviceProduit.getAllProducts();
        Page<Produit> liste=serviceProduit.getProductsByMC(mc, PageRequest.of(page,size));
        //m.addAttribute("mc",mc);
        //m.addAttribute("produits",liste.getContent());
        //m.addAttribute("pages",new int[liste.getTotalPages()]);
        //m.addAttribute("current",page);
       // System.out.println("okkkkk");

        return liste.getContent();
    }

    @DeleteMapping ("/remove/{id}")
    public void deletePRoduct(@PathVariable("id") Long idProduit)
    {
        serviceProduit.deleteProduct(idProduit);

    }


     @PostMapping("/save")
     public void addProduct(@RequestParam("produit")String produit,@RequestParam("file")MultipartFile mf) throws IOException {
         Produit p=new ObjectMapper().readValue(produit,Produit.class);
         serviceProduit.saveProduct(p,mf);
     }





    @GetMapping(path = "/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return serviceProduit.getImage(id);
    }




}

