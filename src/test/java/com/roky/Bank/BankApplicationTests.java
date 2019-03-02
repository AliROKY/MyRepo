package com.roky.Bank;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.roky.bank.dao.ClientRepository;
import com.roky.bank.dao.CompteRepository;
import com.roky.bank.dao.OperationRepository;
import com.roky.bank.entities.Client;
import com.roky.bank.entities.Compte;
import com.roky.bank.entities.CompteCourant;
import com.roky.bank.entities.Operation;
import com.roky.bank.entities.Retrait;
import com.roky.bank.entities.Versement;
import com.roky.bank.service.AccountBankService;
import com.roky.bank.service.AccountBankServiceImpl;

/**
 * Classe test des transactions bancaires
 * @author ROKY
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

	@Autowired
    private AccountBankService accountBankService;
	
	@Autowired
    private CompteRepository compteRepository;
	
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private OperationRepository operationRepository;
	
	@Before
    public void setUp() throws Exception {
		
		Client client1 = clientRepository.save(new Client("Ali", "adresse1"));
		Client client2 = clientRepository.save(new Client("Yassine", "adresse2"));
		Client client3 = clientRepository.save(new Client("Jack", "adresse3"));
		Client client4 = clientRepository.save(new Client("Bernadette", "adresse4"));
		Client client5 = clientRepository.save(new Client("Julie", "adresse5"));
		Client client6 = clientRepository.save(new Client("Societe fabrication", "adresse6"));
		Client client7 = clientRepository.save(new Client("Pierre", "adresse7"));
		
		compteRepository.save(new CompteCourant("C1", new Date(), 1000, client1, 0));
		compteRepository.save(new CompteCourant("C2", new Date(), 2000, client2, 0));
		compteRepository.save(new CompteCourant("C3", new Date(), 3000, client3, 0));
		compteRepository.save(new CompteCourant("C4", new Date(), 4000, client4, 0));
		compteRepository.save(new CompteCourant("C5", new Date(), 5000, client5, 0));
		compteRepository.save(new CompteCourant("C6", new Date(), 6000, client6, 0));
		compteRepository.save(new CompteCourant("C7", new Date(), 7000, client7, 0));

    }
	

	/**
	 * Methode test pour verser
	 * @throws Exception
	 */
    @Test
    public void testsaveMoney() throws Exception {
    	
    	Compte compte = accountBankService.consulterCompte("C1");
    	double AccountBalance = compte.getSolde();
        double amount = 100.0;
        accountBankService.saveMoney("C1", amount);
        compte = accountBankService.consulterCompte("C1");
        assertEquals(amount + AccountBalance, compte.getSolde(), 0.0);
    }
    
    /**
     * Methode test pour retrait
     * @throws Exception
     */
    @Test
    public void testretrieveMoney() throws Exception {
    	
       	Compte compte = accountBankService.consulterCompte("C1");
    	double AccountBalance = compte.getSolde();
        double amount = 500.0;
        accountBankService.retrieveMoney("C1", amount);
        compte = accountBankService.consulterCompte("C1");

        assertEquals(AccountBalance - amount, compte.getSolde(), 0.0);
    }
    
    /**
     * Methode test pour virement
     * @throws Exception
     */
    @Test
    public void testTransferMoney() throws Exception {
    	
    	Compte compte1 = accountBankService.consulterCompte("C1");
    	Compte compte2 = accountBankService.consulterCompte("C2");
    	
    	double AccountBalance1 = compte1.getSolde();
    	double AccountBalance2 = compte2.getSolde();
    	
    	double amount = 50.0;
    	accountBankService.retrieveMoney("C1", amount);
    	accountBankService.saveMoney("C2", amount);
    	
    	compte1 = accountBankService.consulterCompte("C1");
    	compte2 = accountBankService.consulterCompte("C2");

        assertEquals(0, (AccountBalance1 - compte1.getSolde()) - (compte2.getSolde() - AccountBalance2) , 0.0);
    }
    
    
    /**
     * Methode test pour historique operations
     * @throws Exception
     */
    @Test
    public void testOperationHistory() throws Exception {
    	

    	long size = operationRepository.count();
    	
    	 double amount = 200.0;
         accountBankService.saveMoney("C1", amount);
         
    	long newSize = operationRepository.count();
    	
    	assertEquals(newSize, size + 1 , 0.0);
    	
    }
    

}
