package org.sid.bank_account_service.web;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepositorie;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {


    private BankAccountRepositorie bankAccountRepositorie;

    public AccountRestController(BankAccountRepositorie bankAccountRepositorie) {
        this.bankAccountRepositorie = bankAccountRepositorie;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return  bankAccountRepositorie.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return  bankAccountRepositorie.findById(id).orElseThrow(
                ()->new RuntimeException(String.format("Bank account with id %s not found", id))
        );
    }

    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount) {
        if(bankAccount.getId() == null) bankAccount.setId(UUID.randomUUID().toString());
        if(bankAccount.getCreatedAt() == null) bankAccount.setCreatedAt(new Date());
        return bankAccountRepositorie.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        // 1. Charger l'entité existante depuis la BDD (état 'Managed' par JPA)
        BankAccount account = bankAccountRepositorie.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

        // 2. Modifier UNIQUEMENT si la valeur reçue dans la requête n'est pas null
        if (bankAccount.getBalance() != null) {
            account.setBalance(bankAccount.getBalance());
        }
        if (bankAccount.getCurrency() != null) {
            account.setCurrency(bankAccount.getCurrency());
        }
        if (bankAccount.getType() != null) {
            account.setType(bankAccount.getType());
        }

        // 3. Sauvegarder
        return bankAccountRepositorie.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void  deleteAccount(@PathVariable String id) {

        bankAccountRepositorie.deleteById(id);

    }


}
