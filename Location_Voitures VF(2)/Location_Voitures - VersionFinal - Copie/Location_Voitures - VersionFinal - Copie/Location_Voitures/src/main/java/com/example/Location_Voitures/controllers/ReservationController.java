package com.example.Location_Voitures.controllers;

import com.example.Location_Voitures.entities.*;
import com.example.Location_Voitures.repositories.ClientRepository;
import com.example.Location_Voitures.repositories.LocationRepository;
import com.example.Location_Voitures.repositories.VoitureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final VoitureRepository voitureRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    public ReservationController(VoitureRepository voitureRepository,
                                 ClientRepository clientRepository,
                                 LocationRepository locationRepository) {
        this.voitureRepository = voitureRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    // Afficher le formulaire de réservation pour une voiture
    @GetMapping("/{id}")
    public String showReservationForm(@PathVariable Long id, Model model) {
        Optional<Voiture> voitureOpt = voitureRepository.findById(id);
        if (voitureOpt.isEmpty() || !voitureOpt.get().getDisponible()) {
            return "redirect:/gallery?error=voiture_indisponible";
        }
        
        model.addAttribute("voiture", voitureOpt.get());
        model.addAttribute("client", new Client());
        model.addAttribute("dateDebut", LocalDate.now());
        model.addAttribute("dateFin", LocalDate.now().plusDays(1));
        
        return "reservation/form";
    }

    // Traiter le formulaire de réservation
    @PostMapping("/{id}")
    public String processReservation(@PathVariable Long id,
                                    @RequestParam String nom,
                                    @RequestParam String cin,
                                    @RequestParam String telephone,
                                    @RequestParam LocalDate dateDebut,
                                    @RequestParam LocalDate dateFin,
                                    Model model) {
        
        Optional<Voiture> voitureOpt = voitureRepository.findById(id);
        if (voitureOpt.isEmpty() || !voitureOpt.get().getDisponible()) {
            model.addAttribute("error", "Voiture indisponible");
            return "redirect:/gallery?error=voiture_indisponible";
        }

        Voiture voiture = voitureOpt.get();
        
        // Vérifier les dates
        if (dateDebut.isAfter(dateFin) || dateDebut.isBefore(LocalDate.now())) {
            model.addAttribute("error", "Dates invalides");
            model.addAttribute("voiture", voiture);
            model.addAttribute("client", new Client());
            return "reservation/form";
        }

        // Vérifier si le client existe déjà
        Optional<Client> existingClient = clientRepository.findByCin(cin);
        Client client;
        
        if (existingClient.isPresent()) {
            client = existingClient.get();
        } else {
            // Créer un nouveau client
            client = new Client();
            client.setNom(nom);
            client.setCin(cin);
            client.setTelephone(telephone);
            clientRepository.save(client);
        }

        // Calculer le montant total
        long jours = ChronoUnit.DAYS.between(dateDebut, dateFin);
        BigDecimal montantTotal = voiture.getPrixJour().multiply(BigDecimal.valueOf(jours));

        // Créer la location
        Location location = new Location();
        LocationPk pk = new LocationPk();
        pk.setVoitureId(id);
        pk.setClientId(client.getId());
        pk.setDateDebut(dateDebut);

        location.setLocationPk(pk);
        location.setVoiture(voiture);
        location.setClient(client);
        location.setDateFin(dateFin);
        location.setMontantTotal(montantTotal);
        location.setStatut("CONFIRME");

        locationRepository.save(location);

        // Marquer la voiture comme non disponible
        voiture.setDisponible(false);
        voitureRepository.save(voiture);

        // Rediriger vers une page de confirmation
        model.addAttribute("location", location);
        return "reservation/confirmation";
    }
}

