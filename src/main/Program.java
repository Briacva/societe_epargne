package main;

import interfaces.AccountDepositOrWithdraw;
import interfaces.CreationClientForm;
import models.Compte;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CreationClientForm NewClientForm = new CreationClientForm();
		Compte compte = new Compte(764858, 150, 150, false, false, 1);
		compte.setId(1);
		AccountDepositOrWithdraw frame = new AccountDepositOrWithdraw(compte);
	}

}
