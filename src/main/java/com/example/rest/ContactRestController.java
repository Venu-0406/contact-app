package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bindings.ContactForm;
import com.example.service.ContactService;

@RestController
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/save")
	public String saveContact(@RequestBody ContactForm form) {
		System.out.println(form);
		String status = contactService.saveContact(form);
		return status;
	}
	
	@GetMapping("/contacts")
	public List<ContactForm> viewAllCotacts(){
		return contactService.viewContacts();
	}
	
	@GetMapping("/edit/{contactId}")
	public ContactForm editContact(@PathVariable Integer contactId) {
		return contactService.editContact(contactId);
	}
	
	@GetMapping("/delete/{contactId}")
	public List<ContactForm> deleteContact(@PathVariable Integer contactId){
		return contactService.deleteContact(contactId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
