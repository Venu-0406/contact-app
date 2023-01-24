package com.example.service;

import java.util.List;

import com.example.bindings.ContactForm;

public interface ContactService {

	public String saveContact(ContactForm contactForm);
	
	public List<ContactForm> viewContacts();
	
	public ContactForm editContact(Integer contactId);
	
	public List<ContactForm> deleteContact(Integer contactId);
	
}
