package za.co.assignment.bankingSystem.mapper;

import za.co.assignment.bankingSystem.domain.CurrencyAccountBalance;

import java.util.List;

public interface CurrencyAccountBalanceMapper {
    List<CurrencyAccountBalance> getCurrencyAccountBalanceByClientId(int clientId);
}
