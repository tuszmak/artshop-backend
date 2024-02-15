package com.tuszmak.artshop.repository;

import com.tuszmak.artshop.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Painting, Long> {

}
