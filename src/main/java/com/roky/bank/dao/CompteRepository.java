package com.roky.bank.dao;

import com.roky.bank.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Utilitaire d'accés aux données Compte
 * @author ROKY
 *
 */
public interface CompteRepository extends JpaRepository<Compte, String>{

}
