package com.capgemini.bankapp.service;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountService {

	public double checkBalance(long accountId);
	public double withdraw(long accountId , double amount) throws LowBalanceException;
	public double deposit(long accountId,double amount);
	public boolean deleteBankAccount(long accountId);
	public boolean addNewBankAccount(BankAccount account);
	public List<BankAccount> displayAllAccounts();
	public double fundTransfer(long fromAccountId,long toAccountId,double amount) throws LowBalanceException;
	public BankAccount findAccountById(long accountId) throws SQLException;
	
}
