package org.sid.bank_account_service.web;


import org.sid.bank_account_service.dtos.BankAccountDTOResponse;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.repositories.BankAccountRepositorie;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQlController {
    @Autowired
    private BankAccountRepositorie bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument String id){
        return   bankAccountRepository.findById(id).orElseThrow(
                ()->new RuntimeException(String.format("Bank Account %s not found",id))
        );

    }
    @MutationMapping
    public BankAccountDTOResponse addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountDTOResponse updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return  true;
    }
    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }


}


