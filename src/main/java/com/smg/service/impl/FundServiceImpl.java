package com.smg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.smg.dao.FundDAO;
import com.smg.dao.RegisterDAO;
import com.smg.dao.impl.FundDAOImpl;
import com.smg.dao.impl.RegisterDAOImpl;
import com.smg.model.FundModel;
import com.smg.model.TransactionModel;
import com.smg.service.FundService;
import com.smg.utility.Rand;

public class FundServiceImpl implements FundService {
	
	private FundDAO fundDao = null;
	private RegisterDAO registerDao = null;
	private TransactionModel transact = null;
	private Rand random = new Rand();
	private String error_Code = "";
	
	public ArrayList<String> customersAccount(String customer_id) {
		
		fundDao = new FundDAOImpl();
		ArrayList<String> account = new ArrayList<String>();
		account = fundDao.checkAccountNumberbyCustomer(customer_id);
		return account;
	}
	
	public long generateTAC(long account_no) {
		
		fundDao = new FundDAOImpl();
		long tac = 0;
		Rand random = new Rand();
		tac = random.genRandomDigits(10);
		fundDao.InsertTAC(account_no, tac);
		return tac;
		
	}
	
	
	public void validateFundTransfer(FundModel fund) {
		
		fundDao = new FundDAOImpl();
		registerDao = new RegisterDAOImpl();
		BigDecimal minBalance = new BigDecimal("500");
		BigDecimal SenderBalance = fundDao.checkCustomerBalance(fund.getAccount_no_from());
		BigDecimal ReceiverBalance = fundDao.checkCustomerBalance(fund.getAccount_no_to());
		
		// Check Account Number's Balance
		if (SenderBalance.compareTo(minBalance) == 1) {
			// Check Account Number if existing
			if (registerDao.checkCustomerLinkIfExist(fund.getAccount_no_to())) {
				// Check if Sender and Receiver not Same
				if (fund.getAccount_no_from()!=fund.getAccount_no_to()) {
					// Check if customer balance is equal or greater than 500.00 after transfer
					if (SenderBalance.subtract(fund.getAmount()).compareTo(minBalance)!=-1) {
						// Check verification with Sender Info 
						if (fundDao.checkAccountNoIfExist(fund.getAccount_no_from(), fund.getTac())) {
							
							// Insert and Update Balances
							BigDecimal SenderUpdatedBalance = SenderBalance.subtract(fund.getAmount());
							BigDecimal ReceiverUpdatedBalance = ReceiverBalance.add(fund.getAmount());
							long Sendertransaction_id = random.genRandomDigits(10);
							long Receivertransaction_id = random.genRandomDigits(10);
							
							// Sender Transaction Report
							transact = new TransactionModel(Sendertransaction_id, null, "S", 
									String.valueOf(SenderBalance), String.valueOf(SenderUpdatedBalance), fund.getAccount_no_to(), 
									fund.getAccount_no_from(), "NA", "NA");
							fundDao.insertTransaction(transact);
							
							// Receiver Transaction Report
							transact = new TransactionModel(Receivertransaction_id, null, "R", 
									String.valueOf(ReceiverBalance), String.valueOf(ReceiverUpdatedBalance), fund.getAccount_no_to(), 
									fund.getAccount_no_from(), "NA", "NA");
							fundDao.insertTransaction(transact);
							
							fundDao.updateTransfer(fund);
							fundDao.updateBalance(SenderUpdatedBalance, fund.getAccount_no_from());
							fundDao.updateBalance(ReceiverUpdatedBalance, fund.getAccount_no_to());
							
							
						} else {
							// Verification does not exists for the Sender Account Number. 
							setFundError("F01");
						}
					} else {
						// Customer Balance must not be less than 500.00 after Transfer Fund.
						setFundError("F04");
					}
				} else {
					// Sender Account number must not be the same as Receiver Account Number
					setFundError("F02");
				}
			} else {
				// Account Number does not exists.
				setFundError("A01");
			}
		} else {
			// Balance must be 500.00 and up to proceed.
			setFundError("F03");
		}
		
	}
	
	
	public void setFundError(String error) {
		this.error_Code = error;
	}
	
	public String getFundError() {
		return error_Code;
	}
	
}
