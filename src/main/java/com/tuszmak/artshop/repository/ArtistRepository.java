package com.tuszmak.artshop.repository;

import com.tuszmak.artshop.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> getArtistByName(String name);
}
