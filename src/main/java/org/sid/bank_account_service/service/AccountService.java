package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dtos.BankAccountDTOResponse;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;

public interface AccountService {
    public BankAccountDTOResponse addAccount(BankAccountRequestDTO bankAccountDTO);
    public BankAccountDTOResponse updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
