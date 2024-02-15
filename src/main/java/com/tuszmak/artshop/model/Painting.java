package com.tuszmak.artshop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "paintings")
public class Painting {

    private UUID id;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long publicId;
    @Getter
    private String name;
    @ManyToOne()
    @JoinColumn(name = "artist_id")
    @Getter
    private Artist artist;
    @Getter
    private int createdYear;
    @Getter
    private double price;
    @Getter
    private String description;
    @Getter
    private double width;
    @Getter
    private double height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting painting = (Painting) o;
        return publicId == painting.publicId && createdYear == painting.createdYear && Double.compare(price, painting.price) == 0 && Double.compare(width, painting.width) == 0 && Double.compare(height, painting.height) == 0 && Objects.equals(id, painting.id) && Objects.equals(name, painting.name) && Objects.equals(artist, painting.artist) && Objects.equals(description, painting.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicId, name, artist, createdYear, price, description, width, height);
    }
}
