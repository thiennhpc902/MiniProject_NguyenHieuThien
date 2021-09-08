package r2s.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import r2s.dao.ProductDAO;
import r2s.entity.Product;
import r2s.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Product findById(Integer id) {

		return dao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String id) {
		// TODO Auto-generated method stub
		return dao.findByCategoryId(id);
	}

	@Override
	public Product create(Product product) {
		return dao.save(product);
	}

	@Override
	public Product update(Product product) {
		return dao.save(product);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
