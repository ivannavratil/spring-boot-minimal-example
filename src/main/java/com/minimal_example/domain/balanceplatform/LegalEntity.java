package com.minimal_example.domain.balanceplatform;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table(name = "legal_entity")
public class LegalEntity {

    @Id
    private Long id;
    private Long exposedId;

    @MappedCollection(idColumn = "legal_entity_id")
    private AccountHolder accountHolder;

    /**
     * Returns all entities that are associated to this legal entity
     */
    @MappedCollection(idColumn = "legal_entity_id")
    private Set<LegalEntityAssociation> entityAssociations = new HashSet<>();

    /**
     * Returns all legal entities where this entity is associated legal entity
     */
    @MappedCollection(idColumn = "associated_legal_entity_id")
    private Set<LegalEntityAssociation> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExposedId() {
        return exposedId;
    }

    public void setExposedId(Long exposedId) {
        this.exposedId = exposedId;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Set<LegalEntityAssociation> getEntityAssociations() {
        return entityAssociations;
    }

    public void setEntityAssociations(Set<LegalEntityAssociation> entityAssociations) {
        this.entityAssociations = entityAssociations;
    }

    public Set<LegalEntityAssociation> getRoles() {
        return roles;
    }

    public void setRoles(Set<LegalEntityAssociation> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "LegalEntity{" +
                "id=" + id +
                ", exposedId=" + exposedId +
                ", accountHolder=" + accountHolder +
                ", entityAssociations=" + entityAssociations +
                ", roles=" + roles +
                '}';
    }
}
