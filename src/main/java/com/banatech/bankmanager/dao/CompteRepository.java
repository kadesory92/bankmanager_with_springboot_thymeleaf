package com.banatech.bankmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banatech.bankmanager.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
