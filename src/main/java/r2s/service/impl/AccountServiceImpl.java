package r2s.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import r2s.dao.AccountDAO;
import r2s.entity.Accounts;
import r2s.service.AccountsService;

@Service
public class AccountServiceImpl implements AccountsService {
	@Autowired
	AccountDAO dao;

	@Override
	public Accounts findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<Accounts> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Accounts> getAdmin() {
		// TODO Auto-generated method stub
		return dao.findAdmin();
	}

}
