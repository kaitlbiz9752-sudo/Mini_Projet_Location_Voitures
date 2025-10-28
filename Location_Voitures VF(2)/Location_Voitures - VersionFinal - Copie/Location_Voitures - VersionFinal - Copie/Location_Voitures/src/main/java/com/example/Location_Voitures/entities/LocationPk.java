package com.example.Location_Voitures.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class LocationPk implements Serializable {

    @Column(name = "voiture_id")
    private Long voitureId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    public LocationPk() {}

    public LocationPk(Long voitureId, Long clientId, LocalDate dateDebut) {
        this.voitureId = voitureId;
        this.clientId = clientId;
        this.dateDebut = dateDebut;
    }

    // --- Getters & Setters ---
    public Long getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(Long voitureId) {
        this.voitureId = voitureId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    // --- equals & hashCode (obligatoires pour EmbeddedId) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationPk that)) return false;
        return Objects.equals(voitureId, that.voitureId)
                && Objects.equals(clientId, that.clientId)
                && Objects.equals(dateDebut, that.dateDebut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voitureId, clientId, dateDebut);
    }
}
