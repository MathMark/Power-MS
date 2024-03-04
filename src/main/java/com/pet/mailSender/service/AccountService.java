package com.pet.mailSender.service;

import com.pet.mailSender.dto.AccountRequest;
import com.pet.mailSender.mapper.StaticMapper;
import com.pet.mailSender.model.Account;
import com.pet.mailSender.repository.AccountRepository;
import com.pet.mailSender.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Validator validator;

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccount(int id){
        return accountRepository.findById(id).get();
    }

    public boolean save(AccountRequest accountRequest){
        Account account = StaticMapper.mapToAccount(accountRequest);
        //boolean isValid = validator.validateCredentials(accountRequest);
        return true;
//        if (isValid) {
//            accountRepository.save(account);
//            return true;
//        }
//        return false;
    }

}
