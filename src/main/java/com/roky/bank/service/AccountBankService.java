package com.roky.bank.service;

import java.util.List;

import com.roky.bank.entities.Compte;
import com.roky.bank.entities.Operation;

/**
 * Service gestion compte Bancaire
 * @author ROKY Ali
 *
 */
public interface AccountBankService {
  
  /**
   * Methode pour consulter un compte
   * @param codeCpte
   * @return Compte
   */
  public Compte consulterCompte(String codeCpte);	
  
  /**
   * Methode pour verser une somme d'argent
   * @param codeCpte , montant
   */
  public void saveMoney(String codeCpte, double montant);
  
  /**
   * Methode pour retrait d'une somme d'argent
   * @param codeCpte , montant
   */
  public void retrieveMoney(String codeCpte, double montant);
  
  /**
   * Methode pour lister l'historique des operations d'un compte
   * @param codeCpte
   * @return List<Operation>
   */
  public List<Operation> operationHistory(String codeCpte); 
}
