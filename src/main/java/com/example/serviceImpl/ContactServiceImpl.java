package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bindings.ContactForm;
import com.example.entities.Contact;
import com.example.repository.ContactRepository;
import com.example.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public String saveContact(ContactForm contactForm) {
		// here we have received from binding object(that is model object)
		// Repository save method expecting entity object
		// so copy the data from form binding object to entity object and pass entity
		// object to save method.
		Contact contactEntity = new Contact();
		BeanUtils.copyProperties(contactForm, contactEntity);
		contactEntity.setActiveSw("Y");
		contactEntity = contactRepo.save(contactEntity);
		if (contactEntity.getContactId() != null) {
			return "Contact Saved";
		}
		return "Contact failed to save";
	}

	@Override
	public List<ContactForm> viewContacts() {
		List<ContactForm> dataList = new ArrayList<>();

		List<Contact> findAll = contactRepo.findAll();

		for (Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}
		return dataList;
	}

	@Override
	public ContactForm editContact(Integer contactId) {
		Optional<Contact> findById = contactRepo.findById(contactId);
		//if record is available it should return form object or else null value
		if(findById.isPresent()) {
			Contact entity = findById.get();
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			return form;
		}
		return null;
	}

	@Override
	public List<ContactForm> deleteContact(Integer contactId) {
		//hard delete
		contactRepo.deleteById(contactId);
		return viewContacts();
	}

	
	
}
