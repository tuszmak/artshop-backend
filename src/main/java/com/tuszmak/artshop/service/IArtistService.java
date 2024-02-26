package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.ArtistDTO;
import com.tuszmak.artshop.dto.NewArtist;

public interface IArtistService {
    ArtistDTO createNewArtist(NewArtist newArtist);

    ArtistDTO getArtistByName(String name);
}
