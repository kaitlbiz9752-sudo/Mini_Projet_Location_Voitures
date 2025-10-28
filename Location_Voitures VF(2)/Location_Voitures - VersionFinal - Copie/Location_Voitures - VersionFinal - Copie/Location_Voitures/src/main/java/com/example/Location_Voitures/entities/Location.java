package com.example.Location_Voitures.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "locations")
public class Location {

    @EmbeddedId
    private LocationPk locationPk;

    @NotNull
    private LocalDate dateFin;

    @Column(name = "montant_total", nullable = false)
    private BigDecimal montantTotal;

    @NotNull
    private String statut; // CONFIRME, EN_COURS, TERMINE, ANNULE

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("voitureId") // correspond au champ dans LocationPk
    @JoinColumn(name = "voiture_id", nullable = false)
    private Voiture voiture;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientId") // correspond au champ dans LocationPk
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Location() {}

    // getters / setters
    public LocationPk getLocationPk() { return locationPk; }
    public void setLocationPk(LocationPk locationPk) { this.locationPk = locationPk; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Voiture getVoiture() { return voiture; }
    public void setVoiture(Voiture voiture) { this.voiture = voiture; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
