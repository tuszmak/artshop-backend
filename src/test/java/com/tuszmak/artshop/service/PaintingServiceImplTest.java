package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.model.Painting;
import com.tuszmak.artshop.repository.ArtistRepository;
import com.tuszmak.artshop.repository.PaintingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    }
}