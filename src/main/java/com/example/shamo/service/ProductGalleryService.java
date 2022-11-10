package com.example.shamo.service;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.productgallery.FindAllProductGalleriesRes;
import com.example.shamo.dto.productgallery.InsertGalleryReq;

public interface ProductGalleryService {

    InsertRes insert(InsertGalleryReq data) throws Exception;
    FindAllProductGalleriesRes findByProductId(Long productId) throws Exception;
    DeleteRes delete(Long id) throws Exception;

}
