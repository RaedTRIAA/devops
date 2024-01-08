package com.poly.gestioncatalogue5gr1.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/errorPage")
    public String erroPage()
    {
        return "errorpage";
    }
}
