package com.pet.mailSender.mapper;

import com.pet.mailSender.dto.AccountRequest;
import com.pet.mailSender.model.Account;

public class StaticMapper {
    private StaticMapper() {}
    
    public static Account mapToAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setFirstName(accountRequest.getFirstName());
        account.setLastName(accountRequest.getLastName());
        account.setEmail(accountRequest.getEmail());
        account.setPassword(accountRequest.getPassword());
        return account;
    }
}
