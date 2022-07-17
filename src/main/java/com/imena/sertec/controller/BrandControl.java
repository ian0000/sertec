package com.imena.sertec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imena.sertec.model.Brand;
import com.imena.sertec.model.State;
import com.imena.sertec.services.BrandService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/brand")
public class BrandControl {
	@Autowired
	BrandService brandService;

	@GetMapping("")
	public String listar(Model model) {
		// para crear un nuevo modelo y crear
		List<Brand> brands = brandService.getBrands();
		model.addAttribute("brands", brands);
		model.addAttribute("brandSave", new Brand());
		// listar
		return "brandListView";
	}

	@PostMapping("/save")
	public String save(@Validated Brand brand, Model model) {
		brand.setName(brand.getName().toUpperCase());

		try {
			if (!brand.getName().equals("")) {
				brandService.insert(brand);
			}
		} catch (Exception e) {
			log.error("duplicado");
		}
		return "redirect:/brand?condition=saved";
	}
	 

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Optional<Brand> brand = brandService.getBrandById(id);
		model.addAttribute("brandSave", brand);
		List<Brand> brands = brandService.getBrands();
		model.addAttribute("brands", brands);
		return "brandListView";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		Optional<Brand> brand = brandService.getBrandById(id);
		if (!brand.isEmpty()) {
			if (brand.get().getState() == State.DESACTIVADO) {
				brand.get().setState(State.ACTIVO); 
			} else {
				brand.get().setState(State.DESACTIVADO);
			}
			try {
				if (brand.get().getName() != "") {
					brandService.insert(brand.get());
				}
			} catch (Exception e) {
				log.error("Error al modificar");
			}
		}
		return "redirect:/brand?condition=mod";
	}
}
