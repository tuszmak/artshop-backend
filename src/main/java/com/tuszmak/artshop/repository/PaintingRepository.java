package com.tuszmak.artshop.repository;

import com.tuszmak.artshop.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaintingRepository extends JpaRepository<Painting, Long> {
    Optional<Painting> getPaintingsByPublicId(long publicId);
}
