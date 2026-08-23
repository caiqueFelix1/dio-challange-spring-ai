package dio.budgeting.application;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class QueryBalanceByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public QueryBalanceByCategoryUseCase(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }


    @Tool(name = "query-balance-by-category", description = "Consulta o saldo total ou a soma das transações financeiras gastas em uma categoria específica")
    public Long execute(@ToolParam(description = "Categoria para somar saldo")Category category) {
        return transactionRepository.findAllByCategory(category)
                .stream()
                .mapToLong(Transaction::getAmount)
                .sum();
    }
}
