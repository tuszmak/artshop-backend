package com.tuszmak.artshop.controllers;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.service.IPaintingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class PaintingController {

    private IPaintingService paintingService;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private Long createPainting(@RequestBody NewPainting paintingDTO){
        return paintingService.createPainting(paintingDTO).getPublicId();
    }

    @GetMapping("/{id}")
    private PaintingDTO getPaintingById(@PathVariable String id) {
        int paintingId = Integer.parseInt(id);
        return paintingService.getPaintingById(paintingId);
    }
}
