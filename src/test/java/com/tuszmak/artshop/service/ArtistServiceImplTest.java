package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.ArtistDTO;
import com.tuszmak.artshop.dto.NewArtist;
import com.tuszmak.artshop.model.Artist;
import com.tuszmak.artshop.repository.ArtistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceImplTest {
    @Mock
    private ArtistRepository artistRepository;
    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    void should_create_new_artist() {
        when(artistRepository.save(any(Artist.class)))
                .thenReturn(new Artist(1L, "foo", "bar", new HashSet<>()));
        NewArtist newArtist = new NewArtist("foo", "bar");
        ArtistDTO expected = new ArtistDTO("foo", "bar");
        ArtistDTO actual = artistService.createNewArtist(newArtist);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void should_get_artist_by_name() {
        when(artistRepository.getArtistByName("foo"))
                .thenReturn(Optional.of(new Artist(1L, "foo", "bar", new HashSet<>())));
        ArtistDTO expected = new ArtistDTO("foo", "bar");
        ArtistDTO actual = artistService.getArtistByName("foo");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void should_throw_error_when_getting_artist_by_name() {
        when(artistRepository.getArtistByName("foo"))
                .thenReturn(Optional.empty());
        NoSuchElementException exception = Assertions.assertThrows(
                NoSuchElementException.class, () -> artistService.getArtistByName("foo"));
        assertEquals("No artist has name like this.", exception.getMessage());
    }
}