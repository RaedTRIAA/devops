package com.poly.gestioncatalogue5gr1.controleur;

import com.poly.gestioncatalogue5gr1.dao.CategorieRepository;
import com.poly.gestioncatalogue5gr1.entities.Produit;
import com.poly.gestioncatalogue5gr1.service.IServiceProduit;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProduitControleur {

    private IServiceProduit serviceProduit;
    private CategorieRepository categorieRepository;

    @GetMapping("/user/index")
    public String getAllProducts(Model m,
                                 @RequestParam(name = "mc",defaultValue ="" ) String mc,
                                 @RequestParam(name="page",defaultValue = "0")int page,
                                 @RequestParam(name="size",defaultValue = "6")int size)
    {
        //List<Produit> liste=serviceProduit.getAllProducts();
        Page<Produit> liste=serviceProduit.getProductsByMC(mc, PageRequest.of(page,size));
        m.addAttribute("mc",mc);
        m.addAttribute("produits",liste.getContent());
        m.addAttribute("currentpage",page);
        m.addAttribute("pages",new int[liste.getTotalPages()]);


        return "vue";
    }

    @GetMapping("/admin/delete/{id}")
    public String deletePRoduct(@PathVariable("id") Long idProduit)
    {
        serviceProduit.deleteProduct(idProduit);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formproduit")
    public String formAjout(Model m)
    {m.addAttribute("categories",categorieRepository.findAll());
        m.addAttribute("produit",new Produit());
        return "ajouterProduit";
    }

    @PostMapping("/admin/save")
    public String saveProduct(@ModelAttribute Produit p,@RequestParam("file") MultipartFile mf) throws IOException {
        serviceProduit.saveProduct(p,mf);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/update/{id}")
    public String updateProduit(@PathVariable Long id,Model m)
    {
        Produit p=serviceProduit.getProduct(id);
        m.addAttribute("produit",p);
        m.addAttribute("categories",categorieRepository.findAll());
        return "ajouterProduit";
    }

    @GetMapping("/")
    public String home()
    {
        return "redirect:/user/index";
    }

}
