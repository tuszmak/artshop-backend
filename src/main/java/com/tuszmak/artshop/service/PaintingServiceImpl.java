package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.model.Artist;
import com.tuszmak.artshop.model.Painting;
import com.tuszmak.artshop.repository.ArtistRepository;
import com.tuszmak.artshop.repository.PaintingRepository;
import com.tuszmak.artshop.utils.ToDTOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaintingServiceImpl implements IPaintingService {
    private final PaintingRepository paintingRepository;
    private final ArtistRepository artistRepository;

    public Painting createPainting(NewPainting newPainting) {
        Artist artist = artistRepository.getArtistByName(newPainting.artistName()).orElse(new Artist());
        Painting painting = Painting.builder()
                .name(newPainting.name())
                .artist(artist)
                .createdYear(newPainting.createdYear())
                .price(newPainting.price())
                .description(newPainting.description())
                .width(newPainting.width())
                .height(newPainting.height())
                .id(UUID.randomUUID())
                .build();

        return paintingRepository.save(painting);
    }

    @Override
    public PaintingDTO getPaintingById(long paintingId) {
        Optional<Painting> painting = paintingRepository.getPaintingsByPublicId(paintingId);
        return painting.map(ToDTOUtil::toPaintingDTO).orElse(null);
    }

    @Override
    public PaintingDTO modifyPaintingById(long paintingId, NewPainting newPainting) throws NoSuchElementException {
        Painting paintingInDB = paintingRepository.findById(paintingId).orElseThrow(() -> new NoSuchElementException("No painting found"));
        Artist newArtist = artistRepository.getArtistByName(newPainting.artistName()).orElseThrow(() -> new NoSuchElementException("No artist has name like this."));
        Painting painting = Painting.builder()
                .name(newPainting.name().isBlank() ? paintingInDB.getName() : newPainting.name())
                .artist(newPainting.artistName().isBlank() ? paintingInDB.getArtist() : newArtist)
                .createdYear(newPainting.createdYear() <= 0 ? paintingInDB.getCreatedYear() : newPainting.createdYear())
                .price(newPainting.price() <= 0 ? paintingInDB.getPrice() : newPainting.price())
                .description(newPainting.description().isBlank() ? paintingInDB.getDescription() : newPainting.description())
                .width(newPainting.width() <= 0 ? paintingInDB.getWidth() : newPainting.width())
                .height(newPainting.height() <= 0 ? paintingInDB.getHeight() : newPainting.height())
                .id(UUID.randomUUID())
                .build();
        Painting savedPainting = paintingRepository.save(painting);
        return ToDTOUtil.toPaintingDTO(savedPainting);

    }


}
