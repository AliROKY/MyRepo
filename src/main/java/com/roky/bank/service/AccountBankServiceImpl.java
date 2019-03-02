package com.roky.bank.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roky.bank.dao.CompteRepository;
import com.roky.bank.dao.OperationRepository;
import com.roky.bank.entities.Compte;
import com.roky.bank.entities.Operation;
import com.roky.bank.entities.Retrait;
import com.roky.bank.entities.Versement;

/** 
 * Service gestion compte Bancaire
 * @author ROKY
 *
 */
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService{
  
  @Autowired
  private CompteRepository compteRepository;
  @Autowired
  private OperationRepository OperationRepository;

  /**
   * Methode pour verser une somme d'argent
   * @param codeCpte , montant
   */
  @Override
  public void saveMoney(String codeCpte, double montant) {
    
	  Compte compte = consulterCompte(codeCpte);
      Versement versement = new Versement(new Date(), montant, compte);
      OperationRepository.save(versement);
      compte.setSolde(compte.getSolde() + montant);
      compteRepository.save(compte);

  }

  /**
   * Methode pour retrait d'une somme d'argent
   * @param codeCpte , montant
   */
  @Override
  public void retrieveMoney(String codeCpte, double montant) {
	  Compte compte = consulterCompte(codeCpte);
      Retrait retrait = new Retrait(new Date(), montant, compte);
      OperationRepository.save(retrait);
      compte.setSolde(compte.getSolde() - montant);
      compteRepository.save(compte);
    
  }

  /**
   * Methode pour lister l'historique des operations d'un compte
   * @param codeCpte
   * @return List<Operation>
   */
  @Override
  public List<Operation> operationHistory(String codeCpte) {
    // TODO Auto-generated method stub
    return OperationRepository.operationHistory(codeCpte);
  }

  /**
   * Methode pour consulter un compte
   * @param codeCpte
   * @return Compte
   */

  @Override
  public Compte consulterCompte(String codeCpte) {

    Compte cp = null;
    Optional<Compte> compte = compteRepository.findById(codeCpte);
    if (compte.isPresent()) {
      cp = compte.get();
    }
    return cp;
  }

}
