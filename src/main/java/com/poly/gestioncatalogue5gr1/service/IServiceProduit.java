package com.poly.gestioncatalogue5gr1.service;

import com.poly.gestioncatalogue5gr1.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceProduit {

    public void saveProduct(Produit p, MultipartFile mf) throws IOException;
    public List<Produit> getAllProducts();
    public Page<Produit> getProductsByMC(String mc, Pageable p);
    public List<Produit> getProductsBCat(Long idCat);
    public void deleteProduct(Long id);
    public Produit getProduct(Long id);
    public byte[] getImage(Long id) throws IOException;


}
