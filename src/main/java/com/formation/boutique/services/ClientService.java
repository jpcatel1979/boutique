package com.formation.boutique.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.boutique.entities.Client;
import com.formation.boutique.repositories.ClientRepository;

@Service
public class ClientService {
	private final ClientRepository clientRepository;

	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public Iterable<Client> getAll() {
		return clientRepository.findAll();
	}

	public Client save(Client client) {
		return clientRepository.save(client);
	}

	public void deleteByEmail(String email) {
		clientRepository.deleteById(email);
	}

	public Client login(String email, String password) {
		return clientRepository.findClientByEmailAndPassword(email, password);
	}

	public Client getOne(String email) {
		return clientRepository.findByEmail(email);
	}
}