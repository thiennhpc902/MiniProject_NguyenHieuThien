package r2s.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import r2s.dao.RoleDAO;
import r2s.entity.Roles;
import r2s.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO dao;
	
	@Override
	public List<Roles> findAll() {
		return dao.findAll();
	}

}
