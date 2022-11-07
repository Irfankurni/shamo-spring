package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.ProductDao;
import com.example.shamo.dao.ProductGalleryDao;
import com.example.shamo.dao.TransactionDao;
import com.example.shamo.dao.TransactionDetailDao;
import com.example.shamo.dao.UserDao;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.transaction.FindAllTransactionRes;
import com.example.shamo.dto.transaction.FindByIdTransactionRes;
import com.example.shamo.dto.transaction.InsertTransactionReq;
import com.example.shamo.dto.transaction.TransactionData;
import com.example.shamo.dto.transaction.TransactionDetailData;
import com.example.shamo.model.ProductGalleries;
import com.example.shamo.model.Products;
import com.example.shamo.model.TransactionDetails;
import com.example.shamo.model.Transactions;
import com.example.shamo.model.Users;
import com.example.shamo.service.TransactionService;

@Service
public class TransactionServiceImpl extends BaseServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao trxDao;

	@Autowired
	private TransactionDetailDao trxDtlDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductGalleryDao galleryDao;

	@Autowired
	private UserDao userDao;

	@Override
	public FindAllTransactionRes findAll() throws Exception {
		List<TransactionData> trxData = new ArrayList<>();

		List<Transactions> transactions = trxDao.findAllTransactions();
		for (int i = 0; i < transactions.size(); i++) {
			TransactionData trx = new TransactionData();
			trx.setId(transactions.get(i).getId());
			trx.setUserId(transactions.get(i).getUser().getId());
			trx.setShippingPrice(transactions.get(i).getShippingPrice());
			trx.setGrandTotalPrice(transactions.get(i).getGrandTotalPrice());

			List<TransactionDetailData> trxDtlData = new ArrayList<>();
			List<TransactionDetails> trxDtls = trxDtlDao.findAllTransactions();
			for (int j = 0; j < trxDtls.size(); j++) {
				TransactionDetailData trxDtl = new TransactionDetailData();
				trxDtl.setId(trxDtls.get(j).getId());
				trxDtl.setProductId(trxDtls.get(j).getProduct().getId());
				trxDtl.setTransactionId(trxDtls.get(j).getTransaction().getId());

				Products product = productDao.findByIdProduct(trxDtls.get(j).getProduct().getId());
				trxDtl.setProductName(product.getProductName());

				ProductGalleries gallery = galleryDao.findByProduct(product.getId());
				if(gallery != null) {
					trxDtl.setProductPhoto(gallery.getFile().getId());
				}

				trxDtl.setQuantity(trxDtls.get(j).getQuantity());
				trxDtl.setTotalPrice(trxDtls.get(j).getTotalPrice());

				trxDtlData.add(trxDtl);
			}
			trx.setTrxDtlData(trxDtlData);

			trxData.add(trx);
		}

		FindAllTransactionRes res = new FindAllTransactionRes();
		res.setData(trxData);
		return res;
	}

	@Override
	public FindByIdTransactionRes findById(Long id) throws Exception {
		Transactions transactions = trxDao.findByIdTransaction(id);
		TransactionData trx = new TransactionData();
		trx.setId(transactions.getId());
		trx.setUserId(transactions.getUser().getId());
		trx.setShippingPrice(transactions.getShippingPrice());
		trx.setGrandTotalPrice(transactions.getGrandTotalPrice());

		List<TransactionDetailData> trxDtlData = new ArrayList<>();
		List<TransactionDetails> trxDtls = trxDtlDao.findAllTransactions();
		for (int i = 0; i < trxDtls.size(); i++) {
			TransactionDetailData trxDtl = new TransactionDetailData();
			trxDtl.setId(trxDtls.get(i).getId());
			trxDtl.setProductId(trxDtls.get(i).getProduct().getId());
			trxDtl.setTransactionId(trxDtls.get(i).getTransaction().getId());

			Products product = productDao.findByIdProduct(trxDtls.get(i).getProduct().getId());
			trxDtl.setProductName(product.getProductName());

			ProductGalleries gallery = galleryDao.findByProduct(product.getId());
			trxDtl.setProductPhoto(gallery.getFile().getId());

			trxDtl.setQuantity(trxDtls.get(i).getQuantity());
			trxDtl.setTotalPrice(trxDtls.get(i).getTotalPrice());

			trxDtlData.add(trxDtl);
		}
		trx.setTrxDtlData(trxDtlData);

		FindByIdTransactionRes res = new FindByIdTransactionRes();
		res.setData(trx);
		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertRes insert(InsertTransactionReq transaction) throws Exception {
		Transactions trx = new Transactions();

		Users user = userDao.findByIdUser(getUserId());
		trx.setUser(user);

		trx.setShippingPrice(transaction.getShippingPrice());
		trx.setGrandTotalPrice(transaction.getGrandTotalPrice());
		trx.setCreatedBy(getUserId());
		trx.setIsActive(true);

		Transactions inserted = trxDao.insertTransaction(trx);

		for (int i = 0; i < transaction.getTrxDtl().size(); i++) {
			TransactionDetails trxDtl = new TransactionDetails();
			trxDtl.setTransaction(inserted);

			Products product = productDao.findByIdProduct(transaction.getTrxDtl().get(i).getProductId());
			trxDtl.setProduct(product);

			trxDtl.setQuantity(transaction.getTrxDtl().get(i).getQuantity());
			trxDtl.setTotalPrice(transaction.getTrxDtl().get(i).getTotalPrice());
			trxDtl.setCreatedBy(getUserId());
			trxDtl.setIsActive(true);

			trxDtlDao.insertTransaction(trxDtl);
		}

		InsertResData resData = new InsertResData();
		resData.setId(inserted.getId());

		InsertRes res = new InsertRes();
		res.setData(resData);
		res.setMessage("Berhasil");
		return res;
	}

}
