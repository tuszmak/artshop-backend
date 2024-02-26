package com.tuszmak.artshop.dto;

import com.tuszmak.artshop.model.Artist;

public record PaintingDTO(long publicId, Artist artist, String name, int createdYear, double price,
                          String description, double width,
                          double height) {
}
