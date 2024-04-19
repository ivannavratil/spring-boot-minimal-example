package com.minimal_example.domain.balanceplatform;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "balance_account_sweep")
public class BalanceAccountSweep {

    @Id
    private Long id;
    private Long balanceAccountId;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalanceAccountId() {
        return balanceAccountId;
    }

    public void setBalanceAccountId(Long balanceAccountId) {
        this.balanceAccountId = balanceAccountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BalanceAccountSweep{" +
                "id=" + id +
                ", balanceAccountId=" + balanceAccountId +
                ", type='" + type + '\'' +
                '}';
    }
}
