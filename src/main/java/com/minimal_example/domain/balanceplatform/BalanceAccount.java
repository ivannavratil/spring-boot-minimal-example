package com.minimal_example.domain.balanceplatform;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table(name = "balance_account")
public class BalanceAccount {

    @Id
    private Long id;
    private Long accountHolderId;
    @MappedCollection(idColumn = "balance_account_id")
    private Set<BalanceAccountSweep> balanceAccountSweeps = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(Long accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public Set<BalanceAccountSweep> getBalanceAccountSweeps() {
        return balanceAccountSweeps;
    }

    public void setBalanceAccountSweeps(Set<BalanceAccountSweep> balanceAccountSweeps) {
        this.balanceAccountSweeps = balanceAccountSweeps;
    }

    @Override
    public String toString() {
        return "BalanceAccount{" +
                "id=" + id +
                ", accountHolderId=" + accountHolderId +
                ", balanceAccountSweeps=" + balanceAccountSweeps +
                '}';
    }
}
