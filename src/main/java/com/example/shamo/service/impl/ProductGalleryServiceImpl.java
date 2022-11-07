package com.example.shamo.service.impl;

import com.example.shamo.dao.FileDao;
import com.example.shamo.dao.ProductDao;
import com.example.shamo.dao.ProductGalleryDao;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.productgallery.InsertGalleryReq;
import com.example.shamo.model.Files;
import com.example.shamo.model.ProductGalleries;
import com.example.shamo.model.Products;
import com.example.shamo.service.ProductGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductGalleryServiceImpl implements ProductGalleryService {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private ProductGalleryDao galleryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public InsertRes insert(InsertGalleryReq data) throws Exception {
        ProductGalleries galleries = null;
        Products products = productDao.findByIdProduct(data.getProductId());

        for (int i = 0; i < data.getGalleries().size(); i++) {
            Files file = new Files();
            file.setFileName(data.getGalleries().get(i).getFileName());
            file.setFileExtension(data.getGalleries().get(i).getFileExtension());
            file.setIsActive(true);

            Files fileRes = fileDao.insert(file);

            galleries = new ProductGalleries();
            galleries.setProduct(products);
            galleries.setFile(fileRes);
            galleries.setIsActive(true);

            galleryDao.insertGallery(galleries);
        }
        InsertResData resData = new InsertResData();
        resData.setId(galleries.getId());

        InsertRes res = new InsertRes();
        res.setData(resData);
        res.setMessage("Success");

        return res;
    }
}
