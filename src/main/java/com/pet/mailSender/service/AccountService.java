package com.pet.mailSender.service;

import com.pet.mailSender.model.Account;
import com.pet.mailSender.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccount(int id){
        return accountRepository.findById(id).get();
    }

    public void save(Account account){
        accountRepository.save(account);
    }

}
