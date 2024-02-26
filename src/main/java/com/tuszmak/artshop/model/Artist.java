package com.tuszmak.artshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "artists")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;

    @Getter
    private String name;
    @Getter
    private String description;
    @OneToMany(mappedBy = "artist")
    private Set<Painting> paintings;
}
