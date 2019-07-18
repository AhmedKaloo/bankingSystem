package za.co.assignment.bankingSystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.assignment.bankingSystem.domain.*;
import za.co.assignment.bankingSystem.mapper.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankingSystemApplicationTests {

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

    @Test
    public void getTransactionalAccountBalanceByClientId() {
        List<TransactionalAccountBalance> transactionalAccountBalance = transactionalAccountBalanceMapper.getTransactionalAccountBalanceByClientId(1);
        assertNotNull(transactionalAccountBalance);
        assertTrue(transactionalAccountBalance.get(0).getAccountType().equals("Cheque Account"));
    }

    @Test
    public void getCurrencyAccountBalanceByClientId() {
        List<CurrencyAccountBalance> currencyAccountBalance = currencyAccountBalanceMapper.getCurrencyAccountBalanceByClientId(1);
        assertNotNull(currencyAccountBalance);
        assertTrue(currencyAccountBalance.get(0).getClientAccountNumber().equals("9755978035"));
        assertTrue(currencyAccountBalance.get(0).getCurrencyCode().equals("GBP"));
    }

    @Test
    public void getTransactionalAccountBalance() {
        TransactionalAccountBalance curAccountBalance = cashWithdrawalMapper.getAccountBalance("4027669028");
        assertNotNull(curAccountBalance);
        assertTrue(curAccountBalance.getAccountBalance() != 0);

    }

    @Test
    public void getATMBalance() {
        ATMBalance curATMBalance = cashWithdrawalMapper.getATMBalance(3);
        assertNotNull(curATMBalance);
        assertTrue(curATMBalance.getAtmBalance() != 0);

    }

    @Test
    public void getTransactionalAccountReportForAllClients() {
        List<TransactionalAccountReport> transactionalAccountReport = transactionalAccountReportMapper.getTransactionalAccountReportForAllClients();
        assertNotNull(transactionalAccountReport);
        assertTrue(transactionalAccountReport.size() == 15);
    }

    @Test
    public void getAggregateFinancialPositionReportForAllClients() {
        List<AggregateFinancialPositionReport> aggregateFinancialPosition = aggregateFinancialPositionReportMapper.getAggregateFinancialPositionReportForAllClients();
        assertNotNull(aggregateFinancialPosition);
        assertTrue(aggregateFinancialPosition.size() == 3);
    }
}
