package com.tuszmak.artshop.dto;

public record NewPainting(String name, String artistName, int createdYear, double price, String description,
                          double width,
                          double height) {
}
