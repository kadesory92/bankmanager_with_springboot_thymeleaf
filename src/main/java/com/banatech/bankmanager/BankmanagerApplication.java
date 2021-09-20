package com.banatech.bankmanager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.banatech.bankmanager.dao.ClientRepository;
import com.banatech.bankmanager.dao.CompteRepository;
import com.banatech.bankmanager.dao.OperationRepository;
import com.banatech.bankmanager.entities.Client;
import com.banatech.bankmanager.entities.Compte;
import com.banatech.bankmanager.entities.CompteCourant;
import com.banatech.bankmanager.entities.CompteEpargne;
import com.banatech.bankmanager.entities.Retrait;
import com.banatech.bankmanager.entities.Versement;
import com.banatech.bankmanager.metier.IBankMetier;

@SpringBootApplication
public class BankmanagerApplication implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	IBankMetier bankMetier;

	public static void main(String[] args) {
	 SpringApplication.run(BankmanagerApplication.class, args);
	
	
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1=clientRepository.save(new Client("Sory Bana","bana02@gmail.com"));
		Client c2=clientRepository.save(new Client("Kade Mara","kade@gmail.com"));
		Client c3=clientRepository.save(new Client("Saran Krouma","krouma@gmail.com"));
		Client c4=clientRepository.save(new Client("Mousto Kaba","mouskba@gmail.com"));
		
		Compte cp1=compteRepository.save(new 
				CompteEpargne("c1", new Date(), 40000, c4, 4.5));
		Compte cp2=compteRepository.save(new 
				CompteCourant("c2", new Date(), 140000, c1, 15000));
		Compte cp3=compteRepository.save(new 
				CompteEpargne("c3", new Date(), 70000, c3, 5.2));
		Compte cp4=compteRepository.save(new 
				CompteCourant("c4", new Date(), 100000, c2, 15000));
		
		operationRepository.save(new Versement(new Date(), 20000, cp1));
		operationRepository.save(new Versement(new Date(), 15000, cp1));
		operationRepository.save(new Retrait(new Date(), 10000, cp1));
		
		operationRepository.save(new Versement(new Date(), 25000, cp2));
		operationRepository.save(new Versement(new Date(), 5000, cp2));
		operationRepository.save(new Retrait(new Date(), 8000, cp2));
		
		operationRepository.save(new Versement(new Date(), 40000, cp3));
		operationRepository.save(new Versement(new Date(), 35000, cp3));
		operationRepository.save(new Retrait(new Date(), 20000, cp3));
		
		operationRepository.save(new Versement(new Date(), 80000, cp4));
		operationRepository.save(new Versement(new Date(), 50000, cp4));
		operationRepository.save(new Retrait(new Date(), 75000, cp4));
		
		bankMetier.verser("c1", 110101);
		
	}

}
