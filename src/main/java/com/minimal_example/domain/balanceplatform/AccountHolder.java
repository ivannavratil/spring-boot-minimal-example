package com.minimal_example.domain.balanceplatform;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "account_holder")
public class AccountHolder {

    @Id
    private Long legalEntityId;
    private String businessName;

    @MappedCollection(idColumn = "account_holder_id")
    private BalanceAccount balanceAccount;

    public Long getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Long legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public BalanceAccount getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(BalanceAccount balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "legalEntityId=" + legalEntityId +
                ", businessName='" + businessName + '\'' +
                ", balanceAccount=" + balanceAccount +
                '}';
    }
}
