package com.tuszmak.artshop.service;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.model.Painting;

public interface IPaintingService {
    Painting createPainting(NewPainting newPainting);
}
