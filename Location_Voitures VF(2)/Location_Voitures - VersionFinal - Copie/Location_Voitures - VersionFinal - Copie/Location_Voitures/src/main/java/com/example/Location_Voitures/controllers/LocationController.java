package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.entities.*;
import com.example.Location_Voitures.repositories.ClientRepository;
import com.example.Location_Voitures.repositories.LocationRepository;
import com.example.Location_Voitures.repositories.VoitureRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository locationRepository;
    private final VoitureRepository voitureRepository;
    private final ClientRepository clientRepository;

    public LocationController(LocationRepository locationRepository,
                              VoitureRepository voitureRepository,
                              ClientRepository clientRepository) {
        this.locationRepository = locationRepository;
        this.voitureRepository = voitureRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("locations", locationRepository.findAllWithRelations());
        return "locations/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("voitures", voitureRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        // champs simples pour le formulaire
        model.addAttribute("dateDebut", LocalDate.now());
        model.addAttribute("dateFin", LocalDate.now().plusDays(1));
        model.addAttribute("montantTotal", BigDecimal.ZERO);
        model.addAttribute("statuts", new String[]{"CONFIRME","EN_COURS","TERMINE","ANNULE"});
        model.addAttribute("mode", "new");
        return "locations/form";
    }

    @PostMapping
    public String create(@RequestParam Long voitureId,
                         @RequestParam Long clientId,
                         @RequestParam LocalDate dateDebut,
                         @RequestParam LocalDate dateFin,
                         @RequestParam BigDecimal montantTotal,
                         @RequestParam String statut,
                         Model model) {

        Optional<Voiture> vOpt = voitureRepository.findById(voitureId);
        Optional<Client> cOpt = clientRepository.findById(clientId);
        if (vOpt.isEmpty() || cOpt.isEmpty()) {
            model.addAttribute("error", "Client ou Voiture introuvable");
            return "redirect:/locations/new";
        }

        Location location = new Location();
        LocationPk pk = new LocationPk();
        pk.setVoitureId(voitureId);
        pk.setClientId(clientId);
        pk.setDateDebut(dateDebut);

        location.setLocationPk(pk);
        location.setVoiture(vOpt.get());
        location.setClient(cOpt.get());
        location.setDateFin(dateFin);
        location.setMontantTotal(montantTotal);
        location.setStatut(statut);

        locationRepository.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long voitureId,
                           @RequestParam Long clientId,
                           @RequestParam LocalDate dateDebut,
                           Model model) {
        LocationPk pk = new LocationPk();
        pk.setVoitureId(voitureId);
        pk.setClientId(clientId);
        pk.setDateDebut(dateDebut);

        Optional<Location> locOpt = locationRepository.findById(pk);
        if (locOpt.isEmpty()) {
            return "redirect:/locations";
        }

        model.addAttribute("location", locOpt.get());
        model.addAttribute("voitures", voitureRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("statuts", new String[]{"CONFIRME","EN_COURS","TERMINE","ANNULE"});
        model.addAttribute("mode", "edit");
        return "locations/form";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long voitureId,
                         @RequestParam Long clientId,
                         @RequestParam LocalDate dateDebut,
                         @RequestParam LocalDate dateFin,
                         @RequestParam BigDecimal montantTotal,
                         @RequestParam String statut) {
        LocationPk pk = new LocationPk();
        pk.setVoitureId(voitureId);
        pk.setClientId(clientId);
        pk.setDateDebut(dateDebut);

        Optional<Location> locOpt = locationRepository.findById(pk);
        if (locOpt.isPresent()) {
            Location location = locOpt.get();
            location.setDateFin(dateFin);
            location.setMontantTotal(montantTotal);
            location.setStatut(statut);
            locationRepository.save(location);
        }
        return "redirect:/locations";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long voitureId,
                         @RequestParam Long clientId,
                         @RequestParam LocalDate dateDebut) {
        LocationPk pk = new LocationPk();
        pk.setVoitureId(voitureId);
        pk.setClientId(clientId);
        pk.setDateDebut(dateDebut);
        locationRepository.deleteById(pk);
        return "redirect:/locations";
    }
}