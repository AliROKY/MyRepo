package com.roky.bank.dao;

import com.roky.bank.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Utilitaire d'accés aux données Client
 * @author ROKY
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long>{

}
