package com.firmaRehber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firmaRehber.entity.Firma;
import com.firmaRehber.service.FirmaService;

@RestController
@RequestMapping("/firma/admin/firmaRest")
public class FirmaRestController {
	@Autowired
	private FirmaService firmaService;
/*
	@GetMapping(value="/firma/{id}")
	public Firma getFirma(@PathVariable("id")int id) {
		Firma firma = firmaService.getFirma(email)
	}
	*/
	
}
