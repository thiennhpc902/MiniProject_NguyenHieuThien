package r2s.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import r2s.entity.Authorities;
import r2s.service.AuthoritesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthoritiesRestController {

	@Autowired
	AuthoritesService service;

	@GetMapping
	public List<Authorities> findAll(@RequestParam("admins") Optional<Boolean> admin) {
		System.out.println("ALo1");
		if (admin.orElse(false)) {
			return service.findAuthoritiesOfAdministrators();
		}
		return service.findAll();
	}

	@PostMapping
	public Authorities post(@RequestBody Authorities authorities) {
		System.out.println("ALo2");
		return service.save(authorities);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		System.out.println("ALo3");
		service.delete(id);
	}
}
