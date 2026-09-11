package com.nhlstenden.validation;

import com.nhlstenden.useraccount.UserAccount;

public interface Validation
{
    boolean isValid(UserAccount userAccount);
}