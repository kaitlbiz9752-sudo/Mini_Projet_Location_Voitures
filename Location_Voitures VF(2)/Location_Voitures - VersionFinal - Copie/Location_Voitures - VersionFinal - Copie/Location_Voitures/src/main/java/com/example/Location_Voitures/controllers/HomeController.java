package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.repositories.VoitureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final VoitureRepository voitureRepository;

    public HomeController(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("voitures", voitureRepository.findAll());
        return "index"; // templates/index.html
    }
}
