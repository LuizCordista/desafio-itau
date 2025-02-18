package luiz.itauteste.services;

import luiz.itauteste.dtos.StatsTransactionDTO;
import luiz.itauteste.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    void createNewTransaction() {
        ZonedDateTime now = ZonedDateTime.now();
        transactionService.createNewTransaction(100.0, now);

        List<Transaction> transactions = transactionService.getAllTransactions();
        assertEquals(1, transactions.size());
        assertEquals(100.0, transactions.get(0).getValue());
        assertEquals(now, transactions.get(0).getTime());
    }

    @Test
    void deleteAllTransactions() {
        ZonedDateTime now = ZonedDateTime.now();
        transactionService.createNewTransaction(100.0, now);
        transactionService.deleteAllTransactions();

        List<Transaction> transactions = transactionService.getAllTransactions();
        assertTrue(transactions.isEmpty());
    }

    @Test
    void getTransactionsInLastSeconds() {
        ZonedDateTime now = ZonedDateTime.now();
        transactionService.createNewTransaction(100.0, now.minusSeconds(30));
        transactionService.createNewTransaction(200.0, now.minusSeconds(90));

        List<Transaction> transactions = transactionService.getTransactionsInLastSeconds(60);
        assertEquals(1, transactions.size());
        assertEquals(100.0, transactions.get(0).getValue());
    }

    @Test
    void getTransactionsStatsInLastSeconds() {
        ZonedDateTime now = ZonedDateTime.now();
        transactionService.createNewTransaction(100.0, now.minusSeconds(3));
        transactionService.createNewTransaction(200.0, now.minusSeconds(3));
        transactionService.createNewTransaction(200.0, now.minusSeconds(3));
        transactionService.createNewTransaction(200.0, now.minusSeconds(3));
        transactionService.createNewTransaction(200.0, now.minusSeconds(3));
        transactionService.createNewTransaction(300.0, now.minusSeconds(3));
        transactionService.createNewTransaction(200.0, now.minusSeconds(90));


        StatsTransactionDTO stats = transactionService.getTransactionsStatsInLastSeconds(60);
        assertEquals(6, stats.count());
        assertEquals(1200.0, stats.sum());
        assertEquals(200.0, stats.avg());
        assertEquals(100.0, stats.min());
        assertEquals(300.0, stats.max());
    }

    @Test
    void getTransactionsStatsInLastSeconds_noTransactions() {
        StatsTransactionDTO stats = transactionService.getTransactionsStatsInLastSeconds(60);

        assertEquals(0, stats.count());
        assertEquals(0.0, stats.sum());
        assertEquals(0.0, stats.avg());
        assertEquals(0.0, stats.min());
        assertEquals(0.0, stats.max());
    }
}