package za.co.assignment.bankingSystem.mapper;

import za.co.assignment.bankingSystem.domain.*;

import java.util.List;

public interface TransactionalAccountBalanceMapper {
    List<TransactionalAccountBalance> getTransactionalAccountBalanceByClientId(int clientId);
}
