package com.tuszmak.artshop.controllers;

import com.tuszmak.artshop.dto.NewPainting;
import com.tuszmak.artshop.service.IPaintingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class PaintingController {

    private IPaintingService paintingService;
    @GetMapping("/")
    private String test(){
        return "Hello World!";
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private Long createPainting(@RequestBody NewPainting paintingDTO){
        return paintingService.createPainting(paintingDTO).getPublicId();
    }
}
