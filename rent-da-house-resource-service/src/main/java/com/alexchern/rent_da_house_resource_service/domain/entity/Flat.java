package com.alexchern.rent_da_house_resource_service.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private long version;

    @NotBlank
    private String title;

    private String link;

    private String picture;

    @NotBlank
    private String address;

    @Column(name = "vote_value")
    private int voteValue;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "cost_per_month")
    private int costPerMonth;

    @OneToOne(cascade = CascadeType.ALL)
    private Owner owner;
}
