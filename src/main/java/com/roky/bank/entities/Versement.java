package com.roky.bank.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entité Versement
 * @author ROKY
 *
 */
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation{

  public Versement() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Versement(Date dateOperation, double montant, Compte compte) {
    super(dateOperation, montant, compte);
    // TODO Auto-generated constructor stub
  }

  
}
