package com.minimal_example.domain.balanceplatform;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "legal_entity_association")
public class LegalEntityAssociation {

    @Id
    private Long id;
    private Long legalEntityId;
    private Long associatedLegalEntityId;
    private String jobTitle;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Long legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Long getAssociatedLegalEntityId() {
        return associatedLegalEntityId;
    }

    public void setAssociatedLegalEntityId(Long associatedLegalEntityId) {
        this.associatedLegalEntityId = associatedLegalEntityId;
    }

    @Override
    public String toString() {
        return "LegalEntityAssociation{" +
                "id=" + id +
                ", legalEntityId=" + legalEntityId +
                ", associatedLegalEntityId=" + associatedLegalEntityId +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
