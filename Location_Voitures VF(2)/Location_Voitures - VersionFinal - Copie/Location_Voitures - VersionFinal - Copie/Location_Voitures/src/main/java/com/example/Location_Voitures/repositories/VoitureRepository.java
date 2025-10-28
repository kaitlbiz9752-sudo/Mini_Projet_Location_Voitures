package com.example.Location_Voitures.repositories;

import com.example.Location_Voitures.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {


    List<Voiture> findBySegment(String segment);


    List<Voiture> findByDisponible(Boolean disponible);


    List<Voiture> findByMarque(String marque);
}
