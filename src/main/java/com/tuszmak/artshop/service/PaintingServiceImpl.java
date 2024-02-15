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

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaintingServiceImpl implements IPaintingService {
    private final PaintingRepository paintingRepository;
    private final ArtistRepository artistRepository;

    public Painting createPainting(NewPainting newPainting) {
        Artist artist = artistRepository.getArtistByName(newPainting.artistName());
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


}
