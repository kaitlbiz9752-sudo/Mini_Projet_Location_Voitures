package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.entities.Client;
import com.example.Location_Voitures.repositories.ClientRepository;
import com.example.Location_Voitures.repositories.LocationRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    public ClientController(ClientRepository clientRepository,
                            LocationRepository locationRepository) {
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("client") Client client,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/form";
        }
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Client> opt = clientRepository.findById(id);
        if (opt.isEmpty()) return "redirect:/clients";
        model.addAttribute("client", opt.get());
        return "clients/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("client") Client client,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/form";
        }
        client.setId(id);
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Vérifier si le client existe
            Optional<Client> clientOpt = clientRepository.findById(id);
            if (clientOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Client introuvable");
                return "redirect:/clients";
            }
            
            // Supprimer d'abord les locations associées pour éviter les erreurs de contrainte FK
            if (locationRepository.existsByClient_Id(id)) {
                locationRepository.deleteByClient_Id(id);
            }

            // Supprimer le client
            clientRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Client supprimé avec succès");
            System.out.println("Client supprimé avec succès: " + id);
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Erreur lors de la suppression du client: " + e.getMessage());
            System.err.println("Erreur lors de la suppression: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/clients";
    }
}