package com.minimal_example.domain.balanceplatform;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalEntityRepository extends CrudRepository<LegalEntity, Long> {
    Optional<LegalEntity> findByExposedId(Long exposedId);

    @Query("SELECT count(*) FROM account_holder WHERE ppn_id=:ppnId")
    boolean existsByPpnId(@Param("ppnId") String ppnId);

}
