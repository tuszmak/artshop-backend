package com.tuszmak.artshop.repository;

import com.tuszmak.artshop.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist getArtistByName(String name);
}
