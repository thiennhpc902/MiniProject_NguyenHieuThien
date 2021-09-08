package r2s.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import r2s.entity.Product;
import r2s.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService service;

	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<String> id) {
		if (id.isPresent()) {
			List<Product> list = service.findByCategoryId(id.get());
			model.addAttribute("items", list);
		} 		else {
			List<Product> list = service.findAll();
			model.addAttribute("items", list);
		}
		return "product/list";
	}

	@RequestMapping("/product/details/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = service.findById(id);
		model.addAttribute("item", product);
		return "product/detail";
	}

}
