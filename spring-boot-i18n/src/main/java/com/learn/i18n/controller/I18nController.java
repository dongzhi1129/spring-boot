package com.learn.i18n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.i18n.component.I18nComponent;

@RestController
@RequestMapping("/i18n")
public class I18nController {

	@Autowired
	private I18nComponent i18nComponent;

	@GetMapping("hello")
	public Object helloWord() {
		return i18nComponent.getLoacleMessage("hello.word");

	}

}
