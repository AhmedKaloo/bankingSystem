package za.co.assignment.bankingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import za.co.assignment.bankingSystem.domain.*;
import za.co.assignment.bankingSystem.mapper.*;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Path("/ATM")
public class ATMController {
    @Autowired
    private TransactionalAccountBalanceMapper transactionalAccountBalanceMapper;

    @Autowired
    private CurrencyAccountBalanceMapper currencyAccountBalanceMapper;

    @Autowired
    private CashWithdrawalMapper cashWithdrawalMapper;

    @Autowired
    private TransactionalAccountReportMapper transactionalAccountReportMapper;

    @Autowired
    private AggregateFinancialPositionReportMapper aggregateFinancialPositionReportMapper;

    @GetMapping("/transactionalAccountBalance/{clientId}")
    public Response getTransactionalBalanceByClientId(@PathVariable(name = "clientId") int clientId) {
        List<TransactionalAccountBalance> transactionalAccountBalance = transactionalAccountBalanceMapper.getTransactionalAccountBalanceByClientId(clientId);

        if (transactionalAccountBalance != null && transactionalAccountBalance.size() != 0) {
            return Response.status(200).entity(transactionalAccountBalance).build();

        } else {
            return Response.status(404).entity("No accounts to display").build();

        }
    }

    @GetMapping("/currencyAccountBalance/{clientId}")
    public Response getCurrencyBalanceByClientId(@PathVariable(name = "clientId") int clientId) {
        List<CurrencyAccountBalance> currencyAccountBalance = currencyAccountBalanceMapper.getCurrencyAccountBalanceByClientId(clientId);

        if (currencyAccountBalance != null && currencyAccountBalance.size() != 0) {
            return Response.status(200).entity(currencyAccountBalance).build();

        } else {
            return Response.status(404).entity("No accounts to display").build();

        }
    }

    @GetMapping("/transactionalAccountReport/")
    public Response getHighestTransactionalBalanceForAllClients() {
        List<TransactionalAccountReport> transactionalAccountReport = transactionalAccountReportMapper.getTransactionalAccountReportForAllClients();

        if (transactionalAccountReport != null && transactionalAccountReport.size() != 0) {
            return Response.status(200).entity(transactionalAccountReport).build();

        } else {
            return Response.status(404).entity("No accounts to display").build();

        }
    }

    @GetMapping("/aggregateFinancialPositionReport/")
    public Response getAggregateFinancialPositionForAllClients() {
        List<AggregateFinancialPositionReport> aggregateFinancialPosition = aggregateFinancialPositionReportMapper.getAggregateFinancialPositionReportForAllClients();

        if (aggregateFinancialPosition != null && aggregateFinancialPosition.size() != 0) {
            return Response.status(200).entity(aggregateFinancialPosition).build();

        } else {
            return Response.status(404).entity("No accounts to display").build();

        }
    }

    @GetMapping("/withdrawCash/{atmId}/{accountNo}/{requiredAmount}")
    public Response withdrawCash(
            @PathVariable(name = "atmId") int atmId,
            @PathVariable(name = "accountNo") String accountNo,
            @PathVariable(name = "requiredAmount") double requiredAmount) {

        // 1. Check AccNo and AccBalance 4027669028
        TransactionalAccountBalance curAccountBalance = cashWithdrawalMapper.getAccountBalance(accountNo);

        if (curAccountBalance == null || curAccountBalance.getAccountBalance() == 0) {
            return Response.status(404).entity("No accounts to display or Insufficient funds").build();

        }

        // 1. Check ATM and ATMBalance
        ATMBalance curATMBalance = cashWithdrawalMapper.getATMBalance(atmId);

        if (curATMBalance == null || curATMBalance.getAtmBalance() == 0) {
            return Response.status(404).entity("ATM not registered or unfunded").build();

        }

        double newAccountBalance = curAccountBalance.getAccountBalance() - requiredAmount;

        // 3. Check CHQ Account funds
        if (curAccountBalance.getAccountTypeCode().equals("CHQ") && newAccountBalance >= -10000) {
            return Response.status(404).entity("Insufficient funds").build();

        }

        // 3. Check for funds
        if (newAccountBalance < 0) {
            return Response.status(404).entity("Insufficient funds").build();

        }

        // 3. Check CCRD Limit
        if (curAccountBalance.getAccountTypeCode().equals("CCRD")) {
            CreditAccountLimit creditAccountLimit = cashWithdrawalMapper.getCreditAccountLimit(accountNo);

            if (requiredAmount > creditAccountLimit.getAccountLimit()) {
                return Response.status(404).entity("Insufficient funds").build();

            }
        }

        // 4. Check withdrawal
        if (requiredAmount > curATMBalance.getAtmBalance()) {
            return Response.status(404).entity("Amount not available, would you like to draw " + curATMBalance.getAtmBalance()).build();

        } else {
            List<ATMBalance> availableNotes = cashWithdrawalMapper.getAvailableNotesFromATM(atmId);
            Map<Integer, Integer> mapNotesToDispense = getNotesToDispense(availableNotes, requiredAmount);

            if (mapNotesToDispense.size() == 0) {
                return Response.status(404).entity("Note denomination not available, please select a different amount").build();

            }

            for (int note : mapNotesToDispense.keySet()) {
                ATMBalance newATMBalance = new ATMBalance();
                newATMBalance.setAtmId(atmId);
                newATMBalance.setCount(mapNotesToDispense.get(note));
                newATMBalance.setValue(note);
                cashWithdrawalMapper.updateATMBalance(newATMBalance);

            }

            TransactionalAccountBalance newTransactionalAccountBalance = new TransactionalAccountBalance();
            newTransactionalAccountBalance.setAccountBalance(newAccountBalance);
            newTransactionalAccountBalance.setAccountNumber(accountNo);
            cashWithdrawalMapper.updateAccountBalance(newTransactionalAccountBalance);

            return Response.status(200).entity(newAccountBalance).build();
        }
    }

    private Map<Integer, Integer> getNotesToDispense(List<ATMBalance> availableNotes, double amount) {
        Map<Integer, Integer> mapAvailableNotes = new LinkedHashMap<>();

        for (int i = 0; i < availableNotes.size(); i++) {
            mapAvailableNotes.put(availableNotes.get(i).getValue(), availableNotes.get(i).getCount());

        }

        Map<Integer, Integer> mapNotesToDispense = new HashMap();
        double remainder;

        for (int note : mapAvailableNotes.keySet()) {
            for (int i = 0; i < mapAvailableNotes.get(note); i++) {
                remainder = amount - note;

                if (remainder >= 0) {
                    amount = remainder;
                    mapNotesToDispense.put(note, i + 1);

                } else {
                    break;

                }
            }
        }

        return mapNotesToDispense;
    }
}
