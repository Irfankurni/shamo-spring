package com.example.shamo.controller;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.productgallery.InsertGalleryReq;
import com.example.shamo.service.ProductGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("galleries")
public class ProductGalleryController {

    @Autowired
    private ProductGalleryService galleryService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody InsertGalleryReq data) throws Exception {
        InsertRes res = galleryService.insert(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
