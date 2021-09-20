package com.banatech.bankmanager.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banatech.bankmanager.dao.CompteRepository;
import com.banatech.bankmanager.dao.OperationRepository;
import com.banatech.bankmanager.entities.Compte;
import com.banatech.bankmanager.entities.CompteCourant;
import com.banatech.bankmanager.entities.CompteEpargne;
import com.banatech.bankmanager.entities.Operation;
import com.banatech.bankmanager.entities.Retrait;
import com.banatech.bankmanager.entities.Versement;


@Service
@Transactional
public class BankMetierImpl implements IBankMetier {
	
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp=compteRepository.getById(codeCpte);
		if(cp==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		Versement v=new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		double facilitesCaisse=0;
		if(cp instanceof CompteCourant )
			facilitesCaisse=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse<montant) throw new RuntimeException("Solde insuffisant");
		Retrait r=new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
		return operationRepository.listOperation(codeCpte, PageRequest.of(page, size));
	}

}
