package com.tuszmak.artshop.utils;

import com.tuszmak.artshop.dto.ArtistDTO;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.model.Artist;
import com.tuszmak.artshop.model.Painting;

public class ToDTOUtil {
    public static PaintingDTO toPaintingDTO(Painting painting) {
        return new PaintingDTO(painting.getPublicId(), painting.getArtist(), painting.getName(), painting.getCreatedYear(),
                painting.getPrice(), painting.getDescription(), painting.getWidth(), painting.getHeight());
    }

    public static ArtistDTO toArtistDTO(Artist artist) {
        return new ArtistDTO(artist.getName(), artist.getDescription());
    }
}
