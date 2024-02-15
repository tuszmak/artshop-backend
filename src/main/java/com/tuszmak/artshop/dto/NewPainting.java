package com.tuszmak.artshop.dto;

public record NewPainting(String artistName, String name, int createdYear, double price, String description,
                          double width,
                          double height) {
}
