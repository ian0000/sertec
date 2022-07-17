package com.imena.sertec.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imena.sertec.model.Client;
import com.imena.sertec.model.OT;
import com.imena.sertec.services.ClientService;
import com.imena.sertec.services.OTService;
import com.imena.sertec.services.PersonService;
import com.imena.sertec.services.TypeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminControl {
	@Autowired
	OTService otService;
	@Autowired
	ClientService clientService;
	@Autowired
	TypeService typeService;
	@Autowired
	PersonService personService;
//	String dir(String dir, String est) {
//		return "/"+dir+est;
//	}

	@GetMapping("/ver")
	public String ci(Model model) {

		log.info("cuando se inicia cliente");
		model.addAttribute("cli", new Client());

		return "verifyCI";
	}
	@GetMapping("")
	public String init(Model model) {
		model.addAttribute("ot", otService.getOTs());
		return "adminView";
	}

	@PostMapping("/verificar")
	public String verify(@Validated Client cli, Model model) {
		log.info("cuando se inicia cliente");
		try {
			Optional<Client> cl = clientService.findByCiClient(cli.getClientId());
			if (!cl.isEmpty()) {
				log.info("cli encontrado", cl);

				log.info("dirigir a addOT desde verify");
				return addOTGen(cl.get(), model);
			} else {
				log.error("cli no encontrado");
				Client te = new Client();
				te.setClientId(cli.getClientId());
				model.addAttribute("addCli", te);
				return "addCli";
			}
		} catch (Exception e) {
			log.error("fallo al buscar cli" + e);
			return "redirect:/admin?error";
		}
	}

	@PostMapping("/saveCLI")
	public String saveCLI(@Validated Client client, Model model) {
		client.getLastName().toUpperCase();
		client.getName().toUpperCase();
		try {
			clientService.insert(client);
			log.info("cliente creado");
		} catch (Exception e) {
			log.error("error al crear cli" + e);
		}
		log.info("dirigir a addOT desde save");
		return addOTGen(client, model);
	}

	public String addOTGen(Client client, Model model) {
		OT ot = new OT();
		ot.setClient(client);
		log.info("{}",client);
		model.addAttribute("addOT", ot);
		model.addAttribute("types", typeService.getTypes());
		return "addOT";
	}
	
	@PostMapping("/saveOT")
	public String saveOT(@Validated OT ot, Model model) {
		log.info("aqui2");
		try {
			Date d = new Date();
			ot.setEntryDate(d);
			ot.setProblem("aa");
			log.info("aqui3");
			 otService.insert(ot);
			
		} catch (Exception e) {
			log.error("insert fail"+e);
		}
		
		return "redirect:/admin";
	}

}
