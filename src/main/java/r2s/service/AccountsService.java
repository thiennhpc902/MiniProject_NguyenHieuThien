package r2s.service;

import java.util.List;

import r2s.entity.Accounts;

public interface AccountsService {
	Accounts findById(String id);

	List<Accounts> findAll();

	List<Accounts> getAdmin();
	
	
	
}
