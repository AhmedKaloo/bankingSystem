package za.co.assignment.bankingSystem.mapper;

import za.co.assignment.bankingSystem.domain.AggregateFinancialPositionReport;

import java.util.List;

public interface AggregateFinancialPositionReportMapper {
    List<AggregateFinancialPositionReport> getAggregateFinancialPositionReportForAllClients();
}
