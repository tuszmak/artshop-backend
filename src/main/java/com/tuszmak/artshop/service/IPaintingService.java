package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.model.Painting;

import java.util.NoSuchElementException;

public interface IPaintingService {
    Painting createPainting(NewPainting newPainting);

    PaintingDTO getPaintingById(long paintingId);

    PaintingDTO modifyPaintingById(long paintingId, NewPainting painting) throws NoSuchElementException;
}
