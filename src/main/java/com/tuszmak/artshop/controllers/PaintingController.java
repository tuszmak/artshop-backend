package com.tuszmak.artshop.controllers;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.dto.PaintingDTO;
import com.tuszmak.artshop.service.IPaintingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/paintings")
public class PaintingController {

    private IPaintingService paintingService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private Long createPainting(@RequestBody NewPainting paintingDTO) {
        return paintingService.createPainting(paintingDTO).getPublicId();
    }

    @GetMapping("/{id}")
    private PaintingDTO getPaintingById(@PathVariable String id) {
        int paintingId = Integer.parseInt(id);
        return paintingService.getPaintingById(paintingId);
    }

    @PutMapping("/{id}")
    private ResponseEntity modifyPaintingById(@PathVariable String id, @RequestBody NewPainting painting) {
        Long paintingId = Long.getLong(id);
        try {
            return ResponseEntity.status(200).body(paintingService.modifyPaintingById(paintingId, painting));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
