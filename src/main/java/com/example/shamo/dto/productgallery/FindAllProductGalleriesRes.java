package com.example.shamo.dto.productgallery;

import java.util.List;

public class FindAllProductGalleriesRes {

    private List<ProductGalleryData> data;

    public List<ProductGalleryData> getData() {
        return data;
    }

    public void setData(List<ProductGalleryData> data) {
        this.data = data;
    }
}
