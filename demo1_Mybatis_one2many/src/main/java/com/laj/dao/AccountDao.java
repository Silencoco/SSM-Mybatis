package com.laj.dao;

import com.laj.domain.Account;
import com.laj.domain.AccountUser;

import java.util.List;

public interface AccountDao{
    List<Account> findAll();
    List<AccountUser> findAllAccount();
}
