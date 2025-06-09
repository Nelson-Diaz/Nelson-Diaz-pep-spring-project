package com.example.service;

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

    public Account createAccount(Account newAccount) throws ClientErrorException, UserExistsException {
        boolean validUsername = newAccount.getUsername().length() > 0;
        boolean validPassword = newAccount.getPassword().length() >= 4;

        Account existingAccount = accountRepository.getAccountByUsername(newAccount.getUsername());
        if(existingAccount == null) {
            throw new UserExistsException();
        }

        if(!validUsername || !validPassword) {
            throw new ClientErrorException();
        } 

        return accountRepository.insertAccount(newAccount);
    }

    public Account login(Account account) throws UnauthorizedException {
        Account foundAccount = accountRepository.getAccountByUsername(account.getUsername());

        if(!foundAccount.getPassword().equals(account.getPassword())) {
            throw new UnauthorizedException();
        }

        return foundAccount;
    }
}
