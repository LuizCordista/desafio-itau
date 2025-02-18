package luiz.itauteste.controllers;

import luiz.itauteste.dtos.CreateTransactionDTO;
import luiz.itauteste.dtos.StatsTransactionDTO;
import luiz.itauteste.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transacao")
    public ResponseEntity<Void> createNewTransaction(@RequestBody CreateTransactionDTO transactionDTO) {

        ZonedDateTime now = ZonedDateTime.now();

        if (transactionDTO.time().isAfter(now) || transactionDTO.value() < 0) {
            return ResponseEntity.unprocessableEntity().build();
        }
        transactionService.createNewTransaction(transactionDTO.value(), transactionDTO.time());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<StatsTransactionDTO> getStatsByLastMinute() {
        return ResponseEntity.ok(transactionService.getTransactionsStatsInLastSeconds(60));
    }
}