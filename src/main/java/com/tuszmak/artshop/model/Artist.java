package com.tuszmak.artshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "artists")
@RequiredArgsConstructor
public class Artist {
    @Id
    private Long artistId;

    private String name;
    private String description;
    @OneToMany(mappedBy = "artist")
    private Set<Painting> paintings;
}
