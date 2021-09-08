package r2s.service;

import java.util.List;

import r2s.entity.Authorities;

public interface AuthoritesService {

	public List<Authorities> findAuthoritiesOfAdministrators();

	List<Authorities> findAll();

	Authorities save(Authorities authorities);

	void delete(Integer id);

}
