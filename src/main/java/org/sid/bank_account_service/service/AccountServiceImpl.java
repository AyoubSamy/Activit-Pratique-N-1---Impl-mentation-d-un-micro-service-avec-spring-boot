package org.sid.bank_account_service.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.bank_account_service.dtos.BankAccountDTOResponse;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    @Autowired
    BankAccountRepositorie bankAccountRepositorie;
    AccountMapper accountMapper;

    @Override
    public BankAccountDTOResponse addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())

                .build();
        BankAccount savedBankAccount=bankAccountRepositorie.save(bankAccount);
        return accountMapper.fromBankAccount(savedBankAccount);
    }

    @Override
    public BankAccountDTOResponse updateAccount(String id,BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())

                .build();
        BankAccount savedBankAccount=bankAccountRepositorie.save(bankAccount);


        return accountMapper.fromBankAccount(savedBankAccount);
    }
}
