package com.alexchern.rent_da_house_resource_service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLAT_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "FLAT_SEQUENCE_GENERATOR", sequenceName = "S_FLAT_SEQUENCE")
    private Long id;

    @Version
    private long version;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String link;

    private String image;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @PositiveOrZero
    @Column(name = "vote_value")
    private int voteValue;

    @Column(name = "short_description")
    private String shortDescription;

    @Positive
    @Column(name = "cost_per_month", nullable = false)
    private int costPerMonth;

    @OneToOne(cascade = CascadeType.MERGE)
    private Owner owner;
}
