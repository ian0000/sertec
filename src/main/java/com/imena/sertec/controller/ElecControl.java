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

import com.imena.sertec.model.State;
import com.imena.sertec.model.Type;
import com.imena.sertec.services.BrandService;
import com.imena.sertec.services.TypeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/elec")
public class ElecControl {
	@Autowired
	TypeService typeService;
	@Autowired
	BrandService brandService;

	@GetMapping("")
	public String init(Model model) {
		log.info("Cargando datos de pagina elect");
		List<Type> tip = typeService.getTypes();
		
		model.addAttribute("type", tip);
		return "elecListView";
	}

	@GetMapping("/addElec")
	public String toAddElec(Model model) {
		model.addAttribute("brands", brandService.getBrands());
		model.addAttribute("typeSave", new Type());
		return "addElec";
	}

	@PostMapping("/save")
	public String save(@Validated Type type, Model model) {
		type.setName(type.getName().toUpperCase());

		log.info("se intenta guardar el tipo {}", type);
		try {
			typeService.insert(type);
		} catch (Exception e) {
			log.error("no se guardo el tipo " + e);
		}
		return "redirect:/elec";
	}

	@GetMapping("/edit/{id}")
	public String editType(@PathVariable int id, Model model) {
		Optional<Type> type = typeService.getTypeById(id);
		model.addAttribute("brands", brandService.getBrands());
		model.addAttribute("typeSave", type);
		return "/addElec";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		Optional<Type> type = typeService.getTypeById(id);
		if (!type.isEmpty()) {
			if (type.get().getState() == State.DESACTIVADO) {
				type.get().setState(State.ACTIVO);
			} else {
				type.get().setState(State.DESACTIVADO);
			}
			try {
				if (type.get().getName() != "") {
					typeService.insert(type.get());
				}
			} catch (Exception e) {
				log.error("Error al modificar estado de elec");
			}
		}
		return "redirect:/elec";
	}
}
