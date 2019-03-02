package com.roky.bank.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entité Retrait
 * @author ROKY
 *
 */
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{

  public Retrait() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Retrait(Date dateOperation, double montant, Compte compte) {
    super(dateOperation, montant, compte);
    // TODO Auto-generated constructor stub
  }

  
}
