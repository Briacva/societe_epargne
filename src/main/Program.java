package main;

import java.sql.ResultSet;
import java.sql.Statement;

import interfaces.CreationClientForm;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreationClientForm NewClientForm = new CreationClientForm();
		DatabaseConnexion dbconnection =  new DatabaseConnexion();
		dbconnection.connect();
		

	}

}
