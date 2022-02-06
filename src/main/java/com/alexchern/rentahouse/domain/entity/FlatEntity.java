package com.alexchern.rentahouse.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "flats")
public class FlatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String link;

    private String picture;

    private String address;

    @Column(name = "vote_value")
    private int voteValue;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "cost_per_month")
    private int costPerMonth;

    @OneToOne(cascade = CascadeType.ALL)
    private OwnerEntity owner;
}
