package za.co.assignment.bankingSystem.mapper;

import za.co.assignment.bankingSystem.domain.TransactionalAccountReport;

import java.util.List;

public interface TransactionalAccountReportMapper {
    List<TransactionalAccountReport> getTransactionalAccountReportForAllClients();
}
