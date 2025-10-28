package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.repositories.ClientRepository;
import com.example.Location_Voitures.repositories.LocationRepository;
import com.example.Location_Voitures.repositories.VoitureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final VoitureRepository voitureRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    public AdminController(VoitureRepository voitureRepository,
                          ClientRepository clientRepository,
                          LocationRepository locationRepository) {
        this.voitureRepository = voitureRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        // Total counts
        long totalVoitures = voitureRepository.count();
        long totalClients = clientRepository.count();
        long totalLocations = locationRepository.count();
        long voituresDisponibles = voitureRepository.findByDisponible(true).size();
        long voituresLouees = voitureRepository.findByDisponible(false).size();

        // Revenue calculation
        BigDecimal revenuTotal = locationRepository.findAll().stream()
            .map(loc -> loc.getMontantTotal() != null ? loc.getMontantTotal() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("totalVoitures", totalVoitures);
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalLocations", totalLocations);
        model.addAttribute("voituresDisponibles", voituresDisponibles);
        model.addAttribute("voituresLouees", voituresLouees);
        model.addAttribute("revenuTotal", revenuTotal);

        return "admin/dashboard";
    }

    @PostMapping("/generate-locations")
    public String generateLocations() {
        var voitures = voitureRepository.findAll();
        var clients = clientRepository.findAll();

        if (voitures.isEmpty() || clients.isEmpty()) {
            return "redirect:/admin";
        }

        int count = Math.min(8, Math.min(voitures.size(), clients.size()));
        for (int i = 0; i < count; i++) {
            var voiture = voitures.get(i % voitures.size());
            var client = clients.get(i % clients.size());

            var dateDebut = LocalDate.now().minusDays(14 - (i * 2L));
            var days = 2 + (i % 5);
            var dateFin = dateDebut.plusDays(days);

            var location = new com.example.Location_Voitures.entities.Location();
            var pk = new com.example.Location_Voitures.entities.LocationPk();
            pk.setVoitureId(voiture.getId());
            pk.setClientId(client.getId());
            pk.setDateDebut(dateDebut);

            location.setLocationPk(pk);
            location.setVoiture(voiture);
            location.setClient(client);
            location.setDateFin(dateFin);
            var montant = voiture.getPrixJour().multiply(BigDecimal.valueOf(days));
            location.setMontantTotal(montant);
            location.setStatut("CONFIRME");

            // Enregistrer (ignore erreurs de doublon via clé composée si mêmes données existent déjà)
            try {
                locationRepository.save(location);
            } catch (Exception ignored) {
            }
        }

        return "redirect:/admin";
    }
}

