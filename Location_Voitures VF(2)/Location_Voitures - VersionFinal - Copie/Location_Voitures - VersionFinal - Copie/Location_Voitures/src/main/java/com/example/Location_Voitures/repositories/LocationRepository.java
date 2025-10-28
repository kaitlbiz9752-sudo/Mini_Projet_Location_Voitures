package com.example.Location_Voitures.repositories;

import com.example.Location_Voitures.entities.Location;
import com.example.Location_Voitures.entities.LocationPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, LocationPk> {
    @Query("select l from Location l join fetch l.client join fetch l.voiture")
    List<Location> findAllWithRelations();
    List<Location> findByDateFinBetween(LocalDate debut, LocalDate fin);
    List<Location> findByStatut(String statut);
    
    // Récupérer les ids de voitures ayant une location qui chevauche une période donnée
    @Query("select distinct l.voiture.id from Location l where l.locationPk.dateDebut <= :fin and l.dateFin >= :debut and (:statut is null or l.statut = :statut)")
    List<Long> findVoitureIdsWithOverlap(@Param("debut") LocalDate debut, @Param("fin") LocalDate fin, @Param("statut") String statut);
    
    // Suppressions en cascade manuelles pour éviter les erreurs de contrainte
    void deleteByClient_Id(Long clientId);
    void deleteByVoiture_Id(Long voitureId);
    
    // Vérifications d'existence
    boolean existsByClient_Id(Long clientId);
    boolean existsByVoiture_Id(Long voitureId);
}
