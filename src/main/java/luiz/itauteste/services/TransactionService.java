package luiz.itauteste.services;

import luiz.itauteste.dtos.StatsTransactionDTO;
import luiz.itauteste.entities.Transaction;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final List<Transaction> transactionList = new ArrayList<>();

    public void createNewTransaction(double value, ZonedDateTime time) {
        Transaction transaction = new Transaction(value, time);

        this.transactionList.add(transaction);
    }

    public void deleteAllTransactions() {
        this.transactionList.clear();
    }


    public List<Transaction> getTransactionsInLastSeconds(long seconds) {
        ZonedDateTime cutoffTime = ZonedDateTime.now().minusSeconds(seconds);
        System.out.println(cutoffTime);
        return this.transactionList.stream()
                .filter(transaction -> transaction.getTime().isAfter(cutoffTime))
                .collect(Collectors.toList());
    }

    public StatsTransactionDTO getTransactionsStatsInLastSeconds(long seconds) {
        List<Transaction> transactions = getTransactionsInLastSeconds(seconds);

        int transactionsCount = transactions.size();

        double sum = transactions.stream().mapToDouble(Transaction::getValue).sum();

        double avg = transactions.stream().mapToDouble(Transaction::getValue).average().orElse(0.0);

        double min = transactions.stream().mapToDouble(Transaction::getValue).min().orElse(0.0);

        double max = transactions.stream().mapToDouble(Transaction::getValue).max().orElse(0.0);

        return new StatsTransactionDTO(transactionsCount, sum, avg, min, max);
    }
}
