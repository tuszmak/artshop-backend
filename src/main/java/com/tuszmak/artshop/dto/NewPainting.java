package com.tuszmak.artshop.dto;

import com.tuszmak.artshop.model.Artist;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public record NewPainting(String artistName, String name, int createdYear, double price, String description, double width,
                          double height) {
}
