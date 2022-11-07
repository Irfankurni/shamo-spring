package com.example.shamo.service;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.productgallery.InsertGalleryReq;

public interface ProductGalleryService {

    InsertRes insert(InsertGalleryReq data) throws Exception;

}
