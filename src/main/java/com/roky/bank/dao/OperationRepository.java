package com.roky.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.roky.bank.entities.Operation;

/**
 * Utilitaire d'accés aux données Operations
 * @author ROKY
 *
 */
public interface OperationRepository extends JpaRepository<Operation, Long>{
  
  /**
   * Methode pour consulter l'historique d'un compte
   * @param codeCpte
   * @return List<Operation>
   */
  @Query("Select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
  public List<Operation> operationHistory(@Param("x") String CodeCpte);

}
