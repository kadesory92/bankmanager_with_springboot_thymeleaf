package com.banatech.bankmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banatech.bankmanager.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
