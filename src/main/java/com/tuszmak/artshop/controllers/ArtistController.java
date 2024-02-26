package com.tuszmak.artshop.controllers;

import com.tuszmak.artshop.dto.ArtistDTO;
import com.tuszmak.artshop.dto.NewArtist;
import com.tuszmak.artshop.service.IArtistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/artists")
public class ArtistController {

    private IArtistService artistService;

    @GetMapping
    private ArtistDTO getArtistByName(@RequestBody String artistName) {
        return artistService.getArtistByName(artistName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createArtist(@RequestBody NewArtist newArtist) {
        artistService.createNewArtist(newArtist);
    }
}
