package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.model.Artist;
import com.tuszmak.artshop.model.Painting;
import com.tuszmak.artshop.repository.ArtistRepository;
import com.tuszmak.artshop.repository.PaintingRepository;
import com.tuszmak.artshop.utils.ToDTOUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PaintingServiceImplTest {
    @Mock
    private PaintingRepository repository;
    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private PaintingServiceImpl paintingService;

    @Test
    void createPainting() {
        NewPainting newPainting = new NewPainting("foo", "painting", 2002, 125.5, "Foo", 64.0, 46.0);
        when(repository.save(any(Painting.class))).thenReturn(new Painting());
        Painting actual = paintingService.createPainting(newPainting);
        Painting expected = new Painting();
        assert expected.equals(actual);
        verify(repository, times(1)).save(any(Painting.class));
        verify(artistRepository, times(1)).getArtistByName(any(String.class));
    }

    @Test
    void should_return_a_painting_dto() {
        Painting painting = new Painting(
                UUID.randomUUID(), // Generate a random UUID
                1L, // Set the publicId
                "Starry Night",
                new Artist(),
                1889,
                1000000.0,
                "A beautiful night scene",
                120.0,
                90.0
        );
        when(repository.getPaintingsByPublicId(any(Long.class)))
                .thenReturn(Optional.of(painting));
        PaintingDTO expected = ToDTOUtil.toPaintingDTO(painting);
        PaintingDTO actual = paintingService.getPaintingById(1L);
        assert expected.equals(actual);
    }

    @Test
    void should_return_none() {

        when(repository.getPaintingsByPublicId(any(Long.class)))
                .thenReturn(Optional.empty());
        PaintingDTO actual = paintingService.getPaintingById(1L);
        assert null == actual;
    }
}