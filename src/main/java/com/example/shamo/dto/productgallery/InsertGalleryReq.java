package com.example.shamo.dto.productgallery;

import java.util.List;

public class InsertGalleryReq {

    private Long productId;
    private List<InsertGalleryData> galleries;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<InsertGalleryData> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<InsertGalleryData> galleries) {
        this.galleries = galleries;
    }
}
