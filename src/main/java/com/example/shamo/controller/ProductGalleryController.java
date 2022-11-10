package com.example.shamo.controller;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.productgallery.FindAllProductGalleriesRes;
import com.example.shamo.dto.productgallery.InsertGalleryReq;
import com.example.shamo.service.ProductGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("galleries")
public class ProductGalleryController {

    @Autowired
    private ProductGalleryService galleryService;

    @GetMapping("{productId}")
    public ResponseEntity<?> findAllByProduct(@PathVariable Long productId) throws Exception {
        FindAllProductGalleriesRes data = galleryService.findByProductId(productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody InsertGalleryReq data) throws Exception {
        InsertRes res = galleryService.insert(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        DeleteRes res = galleryService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
