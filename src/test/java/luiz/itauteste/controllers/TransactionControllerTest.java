package luiz.itauteste.controllers;

import luiz.itauteste.dtos.CreateTransactionDTO;
import luiz.itauteste.dtos.StatsTransactionDTO;
import luiz.itauteste.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewTransaction_validTransaction() {
        CreateTransactionDTO transactionDTO = new CreateTransactionDTO(100.0, ZonedDateTime.now().minusSeconds(10));

        ResponseEntity<Void> response = transactionController.createNewTransaction(transactionDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transactionService, times(1)).createNewTransaction(transactionDTO.value(), transactionDTO.time());
    }

    @Test
    void createNewTransaction_invalidTransaction() {
        CreateTransactionDTO transactionDTO = new CreateTransactionDTO(100.0, ZonedDateTime.now().plusSeconds(10));

        ResponseEntity<Void> response = transactionController.createNewTransaction(transactionDTO);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        verify(transactionService, never()).createNewTransaction(anyDouble(), any(ZonedDateTime.class));
    }

    @Test
    void deleteAllTransactions() {
        ResponseEntity<Void> response = transactionController.deleteAllTransactions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).deleteAllTransactions();
    }

    @Test
    void getStatsByLastMinute() {
        StatsTransactionDTO stats = new StatsTransactionDTO(1, 100.0, 100.0, 100.0, 100.0);
        when(transactionService.getTransactionsStatsInLastSeconds(anyLong())).thenReturn(stats);

        ResponseEntity<StatsTransactionDTO> response = transactionController.getStatsByLastMinute();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stats, response.getBody());
        verify(transactionService, times(1)).getTransactionsStatsInLastSeconds(anyLong());
    }
}