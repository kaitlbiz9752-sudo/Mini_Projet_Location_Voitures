package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.repositories.ClientRepository;
import com.example.Location_Voitures.repositories.LocationRepository;
import com.example.Location_Voitures.repositories.VoitureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Controller
public class StatisticsController {

    private final VoitureRepository voitureRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    public StatisticsController(VoitureRepository voitureRepository, 
                               ClientRepository clientRepository,
                               LocationRepository locationRepository) {
        this.voitureRepository = voitureRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/statistics")
    public String statistics(Model model) {
        // Total counts
        long totalVoitures = voitureRepository.count();
        long totalClients = clientRepository.count();
        long totalLocations = locationRepository.count();
        long voituresDisponibles = voitureRepository.findByDisponible(true).size();
        long voituresLouees = voitureRepository.findByDisponible(false).size();

        // Revenue calculations
        BigDecimal revenuTotal = locationRepository.findAll().stream()
            .map(loc -> loc.getMontantTotal() != null ? loc.getMontantTotal() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal revenuMensuel = locationRepository.findByDateFinBetween(
            LocalDate.now().minusMonths(1), LocalDate.now())
            .stream()
            .map(loc -> loc.getMontantTotal() != null ? loc.getMontantTotal() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Data for charts
        Map<String, Long> voituresParMarque = new HashMap<>();
        Map<String, Long> voituresParSegment = new HashMap<>();
        Map<String, Long> locationsParStatut = new HashMap<>();

        voitureRepository.findAll().forEach(v -> {
            voituresParMarque.put(v.getMarque(), 
                voituresParMarque.getOrDefault(v.getMarque(), 0L) + 1);
            voituresParSegment.put(v.getSegment(), 
                voituresParSegment.getOrDefault(v.getSegment(), 0L) + 1);
        });

        locationRepository.findAll().forEach(loc -> {
            locationsParStatut.put(loc.getStatut(), 
                locationsParStatut.getOrDefault(loc.getStatut(), 0L) + 1);
        });

        // Add to model
        model.addAttribute("totalVoitures", totalVoitures);
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalLocations", totalLocations);
        model.addAttribute("voituresDisponibles", voituresDisponibles);
        model.addAttribute("voituresLouees", voituresLouees);
        model.addAttribute("revenuTotal", revenuTotal);
        model.addAttribute("revenuMensuel", revenuMensuel);
        model.addAttribute("voituresParMarque", voituresParMarque);
        model.addAttribute("voituresParSegment", voituresParSegment);
        model.addAttribute("locationsParStatut", locationsParStatut);

        return "statistics";
    }
}
