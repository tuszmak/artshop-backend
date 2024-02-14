package com.tuszmak.artshop.model;


import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Table(name = "paintings")
public class Painting {
    @Id
    private UUID id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long publicId;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "artist_id")
    private Artist artist;
    private int createdYear;
    private double price;
    private String description;
    private double width;
    private double height;


}
