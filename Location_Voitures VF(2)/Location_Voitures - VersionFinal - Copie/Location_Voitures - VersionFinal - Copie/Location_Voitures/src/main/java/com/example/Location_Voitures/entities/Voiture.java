package com.example.Location_Voitures.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "voitures")
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String immatriculation;

    @NotBlank
    private String marque;

    @NotBlank
    private String segment; // ex: "compact", "SUV", "luxe" etc.

    @NotNull
    private Boolean disponible = true;

    @Column(name = "prix_jour", nullable = false)
    @NotNull
    private BigDecimal prixJour;

    private String description;
    
    private String imageUrl; // URL or path to car image

    // getters / setters
    public Voiture() {}

    // constructeurs, getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getSegment() { return segment; }
    public void setSegment(String segment) { this.segment = segment; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
    public BigDecimal getPrixJour() { return prixJour; }
    public void setPrixJour(BigDecimal prixJour) { this.prixJour = prixJour; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
