package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.model.Artist;
import com.tuszmak.artshop.model.Painting;
import com.tuszmak.artshop.repository.ArtistRepository;
import com.tuszmak.artshop.repository.PaintingRepository;
import com.tuszmak.artshop.utils.ToDTOUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PaintingServiceImplTest {
    @Mock
    private PaintingRepository paintingRepository;
    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private PaintingServiceImpl paintingService;

    @Test
    void createPainting() {
        NewPainting newPainting = new NewPainting("foo", "painting", 2002, 125.5, "Foo", 64.0, 46.0);
        when(paintingRepository.save(any(Painting.class))).thenReturn(new Painting());
        Painting actual = paintingService.createPainting(newPainting);
        Painting expected = new Painting();
        Assertions.assertEquals(expected, actual);
        verify(paintingRepository, times(1)).save(any(Painting.class));
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
        when(paintingRepository.getPaintingsByPublicId(any(Long.class)))
                .thenReturn(Optional.of(painting));
        PaintingDTO expected = ToDTOUtil.toPaintingDTO(painting);
        PaintingDTO actual = paintingService.getPaintingById(1L);
        assert expected.equals(actual);
    }

    @Test
    void should_return_none() {

        when(paintingRepository.getPaintingsByPublicId(any(Long.class)))
                .thenReturn(Optional.empty());
        PaintingDTO actual = paintingService.getPaintingById(1L);
        assert null == actual;
    }

    @Nested
    class modifyPaintingTests {
        private Painting painting;
        private NewPainting newPainting;
        private Painting returnedPainting;

        @BeforeEach
        void setUp() {
            painting = new Painting(
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

        }

        @Test
        void should_return_new_painting() {
            newPainting = new NewPainting(
                    "Mona Lisa",
                    "Leonardo da Vinci",
                    1503,
                    2560.00,
                    "A renowned portrait of Lisa del Giocondo",
                    77.0,
                    53.0
            );
            returnedPainting = new Painting(
                    UUID.randomUUID(),
                    1L,
                    "Mona Lisa",
                    new Artist(),
                    1503,
                    2560.00,
                    "A renowned portrait of Lisa del Giocondo",
                    77.0,
                    53.0
            );
            when(paintingRepository.save(any(Painting.class))).thenReturn(returnedPainting);
            when(paintingRepository.findById(1L)).thenReturn(Optional.ofNullable(painting));
            when(artistRepository.getArtistByName(anyString())).thenReturn(Optional.of(new Artist()));
            PaintingDTO actual = paintingService.modifyPaintingById(1L, newPainting);
            PaintingDTO expected = ToDTOUtil.toPaintingDTO(returnedPainting);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void should_modify_only_the_name() {
            newPainting = new NewPainting(
                    "Mona Lisa",
                    "",
                    0,
                    0,
                    "",
                    0,
                    0
            );
            returnedPainting = new Painting(
                    UUID.randomUUID(), // Generate a random UUID
                    1L, // Set the publicId
                    "Mona Lisa",
                    new Artist(),
                    1889,
                    1000000.0,
                    "A beautiful night scene",
                    120.0,
                    90.0
            );
            when(paintingRepository.save(any(Painting.class))).thenReturn(returnedPainting);
            when(paintingRepository.findById(1L)).thenReturn(Optional.ofNullable(painting));
            when(artistRepository.getArtistByName(anyString())).thenReturn(Optional.of(new Artist()));
            PaintingDTO actual = paintingService.modifyPaintingById(1L, newPainting);
            PaintingDTO expected = ToDTOUtil.toPaintingDTO(returnedPainting);
            Assertions.assertEquals(expected, actual);
            Assertions.assertEquals(actual.name(), "Mona Lisa");
            Assertions.assertEquals(actual.createdYear(), 1889);
        }

        @Test
        void should_return_error_for_id() {
            newPainting = new NewPainting(
                    "Foo",
                    "Mona Lisa",
                    0,
                    0,
                    "",
                    0,
                    0
            );
            when(paintingRepository.findById(anyLong())).thenReturn(Optional.empty());
            NoSuchElementException exception = Assertions.assertThrows(
                    NoSuchElementException.class, () -> paintingService.modifyPaintingById(1L, newPainting));
            Assertions.assertEquals("No painting found", exception.getMessage());
        }

        @Test
        void should_return_error_for_name() {
            newPainting = new NewPainting(
                    "Foo",
                    "Mona Lisa",
                    0,
                    0,
                    "",
                    0,
                    0
            );
            when(paintingRepository.findById(anyLong())).thenReturn(Optional.ofNullable(painting));
            when(artistRepository.getArtistByName(anyString())).thenReturn(Optional.empty());
            NoSuchElementException exception = Assertions.assertThrows(
                    NoSuchElementException.class, () -> paintingService.modifyPaintingById(1L, newPainting));
            Assertions.assertEquals("No artist has name like this.", exception.getMessage());
        }
    }
}