package br.com.FucturaBope.repositorys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
public class RepositoryAluguelTest {
    @Autowired
    private RepositoryAluguel repositoryAluguel;

    @Test
    void contextLoads() {
        // Testa se o contexto carrega
    }
}

