package kh.educat.cstad.aftmobilebankingapi.service;

import kh.educat.cstad.aftmobilebankingapi.model.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> findAllTransactions();
    Transaction findById(Long id);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransaction(Long id);
}
