package za.co.assignment.bankingSystem.mapper;

import za.co.assignment.bankingSystem.domain.ATMBalance;
import za.co.assignment.bankingSystem.domain.CreditAccountLimit;
import za.co.assignment.bankingSystem.domain.TransactionalAccountBalance;

import java.util.List;

public interface CashWithdrawalMapper {
    TransactionalAccountBalance getAccountBalance(String accountNo);

    CreditAccountLimit getCreditAccountLimit(String accountNo);

    ATMBalance getATMBalance(int atmId);

    List<ATMBalance> getAvailableNotesFromATM(int atmId);

    void updateAccountBalance(TransactionalAccountBalance transactionalAccountBalance);

    void updateATMBalance(ATMBalance atmBalance);
}
