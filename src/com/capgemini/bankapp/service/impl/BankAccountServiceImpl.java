package com.capgemini.bankapp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountDao bankAccountDao;

	public BankAccountServiceImpl() {
		bankAccountDao = new BankAccountDaoImpl();
	}

	@Override
	public double checkBalance(long accountId) {

		return bankAccountDao.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		double currentBalance = bankAccountDao.getBalance(accountId);

		if (currentBalance > amount) {
			currentBalance = currentBalance - amount;
			bankAccountDao.updateBalance(accountId, currentBalance);
			return currentBalance;
		} else
			throw new LowBalanceException("insufficient balance");

	}

	@Override
	public double deposit(long accountId, double amount) {

		double newBalance = bankAccountDao.getBalance(accountId) + amount;
		bankAccountDao.updateBalance(accountId, newBalance);
		return newBalance;
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		
		return bankAccountDao.deleteBankAccount(accountId);
		
	}

	@Override
	public boolean addNewBankAccount(BankAccount account) {
		return bankAccountDao.addNewBankAccount(account);
	}

	@Override
	public List<BankAccount> displayAllAccounts() {
		
		return bankAccountDao.displayAllAccounts();
	}

	@Override
	public double fundTransfer(long fromAccountId, long toAccountId, double amount) throws LowBalanceException {
		double result = withdraw(fromAccountId, amount);
		deposit(toAccountId, amount);
		
		return result;
	}

	@Override
	public BankAccount findAccountById(long accountId) throws SQLException {
		return bankAccountDao.displaySingleAccount(accountId);
	}

}
