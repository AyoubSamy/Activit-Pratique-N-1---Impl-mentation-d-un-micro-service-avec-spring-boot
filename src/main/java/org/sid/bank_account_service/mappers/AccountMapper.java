package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dtos.BankAccountDTOResponse;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountDTOResponse fromBankAccount(BankAccount bankAccount) {
        BankAccountDTOResponse bankAccountDTOResponse = new BankAccountDTOResponse();
        BeanUtils.copyProperties(bankAccount, bankAccountDTOResponse);

        return bankAccountDTOResponse;
    }
}
