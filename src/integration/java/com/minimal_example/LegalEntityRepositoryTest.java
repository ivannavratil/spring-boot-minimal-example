package com.minimal_example;

import com.minimal_example.domain.balanceplatform.AccountHolder;
import com.minimal_example.domain.balanceplatform.BalanceAccount;
import com.minimal_example.domain.balanceplatform.BalanceAccountSweep;
import com.minimal_example.domain.balanceplatform.LegalEntity;
import com.minimal_example.domain.balanceplatform.LegalEntityAssociation;
import com.minimal_example.domain.balanceplatform.LegalEntityRepository;
import com.minimal_example.infrastructure.config.flyway.FlywayConfig;
import com.minimal_example.infrastructure.config.flyway.FlywayProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Sql(config = @SqlConfig(separator = ScriptUtils.EOF_STATEMENT_SEPARATOR))
@DataJdbcTest(properties = {"logging.level.org.springframework.jdbc.core=TRACE"})
@Import({FlywayConfig.class, FlywayProperties.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LegalEntityRepositoryTest {

    static DockerImageName postgresImage = DockerImageName.parse("public.ecr.aws/docker/library/postgres:15-alpine").asCompatibleSubstituteFor("postgres");
    static PostgreSQLContainer<?> postgreSqlContainer;

    static {
        postgreSqlContainer = new PostgreSQLContainer<>(postgresImage).withReuse(true);
        postgreSqlContainer.start();

    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
        registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
        registry.add("spring.datasource.hikari.maximum-pool-size", () -> "2");

    }

    @Autowired
    private LegalEntityRepository legalEntityRepository;

    @Test
    @DisplayName("should save")
    void testSave() {
        long exposedId = 123456L;

        // create account holder
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setBusinessName("test company");
        accountHolder.setBalanceAccount(new BalanceAccount());

        // create legal entity
        LegalEntity legalEntity1 = new LegalEntity();
        legalEntity1.setExposedId(exposedId);

        // associate account holder with legal entity
        legalEntity1.setAccountHolder(accountHolder);

        legalEntityRepository.save(legalEntity1);

        // create 2nd legal entity
        LegalEntity legalEntity2 = new LegalEntity();

        LegalEntityAssociation association1 = new LegalEntityAssociation();
        association1.setLegalEntityId(legalEntity1.getId());

        LegalEntityAssociation association2 = new LegalEntityAssociation();
        association2.setLegalEntityId(legalEntity1.getId());

        // add association to 2nd legal entity
        legalEntity2.getRoles().add(association1);
        legalEntity2.getRoles().add(association2);

        legalEntityRepository.save(legalEntity2);

        // verify association was correctly set
        LegalEntity legalEntity1FromDb = legalEntityRepository.findByExposedId(exposedId).orElseThrow();
        assertThat(legalEntity1FromDb.getEntityAssociations()).hasSize(2);

        // add balance account sweep to balance account
        BalanceAccountSweep balanceAccountSweep = new BalanceAccountSweep();
        balanceAccountSweep.setType("push");

        legalEntity1FromDb.getAccountHolder().getBalanceAccount().getBalanceAccountSweeps().add(balanceAccountSweep);
        legalEntity1FromDb = legalEntityRepository.save(legalEntity1FromDb);

        System.out.println("Saved data " + legalEntity1FromDb);

        // fetch legal entity 1 from db and expect balance account sweep is not empty
        LegalEntity entityFromDb = legalEntityRepository.findByExposedId(exposedId).orElseThrow();
        System.out.println("Loaded data " + entityFromDb);

        assertFalse(entityFromDb.getAccountHolder().getBalanceAccount().getBalanceAccountSweeps().isEmpty());
    }
}
