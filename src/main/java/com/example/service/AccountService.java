package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.ClientErrorException;
import com.example.exception.UnauthorizedException;
import com.example.exception.UserExistsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account newAccount) {
        boolean validUsername = newAccount.getUsername().length() > 0;
        boolean validPassword = newAccount.getPassword().length() >= 4;

        Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        if(existingAccount.isPresent()) {
            throw new UserExistsException();
        }

        if(!validUsername || !validPassword) {
            throw new ClientErrorException();
        } 

        return accountRepository.save(newAccount);
    }

    public Account login(Account account) {
        Optional<Account> foundAccount = accountRepository.findByUsername(account.getUsername());
        if(foundAccount.isEmpty()) {
            throw new UnauthorizedException();
        }

        //Given password does not match password of found account
        if(!foundAccount.get().getPassword().equals(account.getPassword())) {
            throw new UnauthorizedException();
        }

        return foundAccount.get();
    }
}
