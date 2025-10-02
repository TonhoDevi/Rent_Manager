package br.com.FucturaBope.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AluguelTest {
    @Test
    void testAluguelId() {
        Aluguel aluguel = new Aluguel();
        aluguel.setId(1);
        assertEquals(1, aluguel.getId());
    }
}

