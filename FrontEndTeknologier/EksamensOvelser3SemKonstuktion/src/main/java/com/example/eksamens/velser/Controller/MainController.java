package com.example.eksamens.velser.Controller;

import com.example.eksamens.velser.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/javascript")
    public String Javascript() {
        return "javascript";
    }

    @GetMapping("/javascript/product")
    public String javascriptProduct(){
        return "javascriptproduct";
    }

    @GetMapping("/Ajax")
    public String Ajax() {
        return "ajax";
    }

    @GetMapping("/Jquery")
    public String JQUERY() {
        return "jquery";
    }

    @GetMapping("/Html5Formv")
    public String Html5FormValidation() {
        return "html5formv";
    }

    @GetMapping("/Thymeleaf")
    public String Thymeleaf(Model model) {
        User user = new User("Patrick Jønsson", "Student at KEA", "Intellij is a great tool that helps me to developer better code and autocompletes the code i cant remember how to write");
        model.addAttribute(user);

        User user2 = new User("Tilde Johansen", "Freelancer",  "intellij is a great tool but dosn't allow for custom user features");
        model.addAttribute("tilde" , user2);

        return "thymeleaf";
    }

    @GetMapping("/Http")
    public String Http() {
        return "http";
    }

}
