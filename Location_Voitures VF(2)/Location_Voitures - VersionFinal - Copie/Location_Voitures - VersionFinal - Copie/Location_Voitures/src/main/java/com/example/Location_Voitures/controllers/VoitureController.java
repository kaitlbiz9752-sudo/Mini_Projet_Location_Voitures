package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.entities.Voiture;
import com.example.Location_Voitures.repositories.VoitureRepository;
import com.example.Location_Voitures.repositories.LocationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Objects;

@Controller
public class VoitureController {

    private final VoitureRepository voitureRepository;
    private final LocationRepository locationRepository;

    public VoitureController(VoitureRepository voitureRepository,
                             LocationRepository locationRepository) {
        this.voitureRepository = voitureRepository;
        this.locationRepository = locationRepository;
    }

    /** Liste (page /voitures) */
    @GetMapping("/voitures")
    public String list(Model model) {
        List<Voiture> voitures = voitureRepository.findAll();
        model.addAttribute("voitures", voitures);
        return "voitures/list";
    }

    /** Afficher formulaire d’ajout (/voitures/add) */
    @GetMapping("/voitures/add")
    public String showAddForm(Model model) {
        model.addAttribute("voiture", new Voiture());
        return "voitures/form";
    }

    /** Enregistrer une nouvelle voiture (POST /voitures/save) */
    @PostMapping("/voitures/save")
    public String save(@ModelAttribute Voiture voiture) {
        voitureRepository.save(voiture);
        return "redirect:/voitures";
    }

    /** Afficher formulaire d’édition (/voitures/edit/{id}) */
    @GetMapping("/voitures/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Voiture v = voitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voiture introuvable: " + id));
        model.addAttribute("voiture", v);
        return "voitures/form";
    }

    /** Mettre à jour (POST /voitures/update/{id}) */
    @PostMapping("/voitures/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Voiture voiture) {
        voiture.setId(id); // important
        voitureRepository.save(voiture);
        return "redirect:/voitures";
    }

    /** Supprimer (/voitures/delete/{id}) */
    @GetMapping("/voitures/delete/{id}")
    public String delete(@PathVariable Long id, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        try {
            // Supprimer d'abord les locations associées pour éviter les erreurs de contrainte FK
            if (locationRepository.existsByVoiture_Id(id)) {
                locationRepository.deleteByVoiture_Id(id);
            }
            voitureRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Voiture supprimée avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la voiture: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Suppression impossible: " + e.getMessage());
        }
        return "redirect:/voitures";
    }

    /** Public gallery for visitors with filters */
    @GetMapping("/gallery")
    public String gallery(Model model,
                          @RequestParam(required = false) String segment,
                          @RequestParam(required = false) Boolean disponible,
                          @RequestParam(required = false) String statut,
                          @RequestParam(required = false) LocalDate dateDebut,
                          @RequestParam(required = false) LocalDate dateFin) {

        // Base: all cars or only available by default
        List<Voiture> voitures = voitureRepository.findAll();

        // Segment filter
        if (segment != null && !segment.isBlank()) {
            voitures = voitures.stream()
                    .filter(v -> segment.equalsIgnoreCase(v.getSegment()))
                    .collect(Collectors.toList());
        }

        // Disponibilite filter (true/false)
        if (disponible != null) {
            voitures = voitures.stream()
                    .filter(v -> Objects.equals(v.getDisponible(), disponible))
                    .collect(Collectors.toList());
        }

        // Période + statut filter via locations overlap
        if (dateDebut != null && dateFin != null && !dateDebut.isAfter(dateFin)) {
            // Find cars that have overlapping bookings (optionally by statut)
            List<Long> busyIds = locationRepository.findVoitureIdsWithOverlap(dateDebut, dateFin, statut);
            Set<Long> busySet = busyIds.stream().collect(Collectors.toSet());
            // If we want available in period -> exclude busy ones; if statut provided without disponible
            // default behavior: show cars not busy in that period
            voitures = voitures.stream()
                    .filter(v -> !busySet.contains(v.getId()))
                    .collect(Collectors.toList());
        } else if (statut != null && !statut.isBlank()) {
            // If only statut provided without dates, interpret as filter cars having any location with that status -> exclude them to show those free of that statut
            // Simpler: ignore statut alone, only meaningful with period. We'll just pass it to the view.
        }

        model.addAttribute("voitures", voitures);
        model.addAttribute("segment", segment);
        model.addAttribute("disponible", disponible);
        model.addAttribute("statut", statut);
        model.addAttribute("dateDebut", dateDebut);
        model.addAttribute("dateFin", dateFin);
        return "voitures/gallery";
    }
}
