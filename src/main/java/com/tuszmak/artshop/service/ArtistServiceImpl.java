package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.ArtistDTO;
import com.tuszmak.artshop.dto.NewArtist;
import com.tuszmak.artshop.model.Artist;
import com.tuszmak.artshop.repository.ArtistRepository;
import com.tuszmak.artshop.utils.ToDTOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtistServiceImpl implements IArtistService {
    private final ArtistRepository artistRepository;

    public ArtistDTO createNewArtist(NewArtist newArtist) {
        Artist artist = Artist.builder()
                .name(newArtist.name())
                .description(newArtist.description())
                .paintings(new HashSet<>())
                .build();
        Artist savedArtist = artistRepository.save(artist);
        return ToDTOUtil.toArtistDTO(savedArtist);
    }

    public ArtistDTO getArtistByName(String name) {
        Optional<Artist> artist = artistRepository.getArtistByName(name);
        if (artist.isPresent()) return ToDTOUtil.toArtistDTO(artist.get());
        throw new NoSuchElementException("No artist has name like this.");
    }

}
