package com.example.repository;

import com.example.entity.Account;

public interface AccountRepository {

    Account getAccountByUsername(String username);

    Account insertAccount(Account newAccount);

    Account getAccountById(int postedBy);
}
