package com.alexchern.rentahouse.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "flat_viewings")
public class FlatViewingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "viewing_day")
    private Instant viewingDay;

    @OneToOne(cascade = CascadeType.ALL)
    private FlatEntity flat;
}
