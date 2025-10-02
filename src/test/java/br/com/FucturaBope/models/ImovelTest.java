package br.com.FucturaBope.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImovelTest {
    @Test
    void testImovelId() {
        Imovel imovel = new Imovel();
        imovel.setId(1);
        assertEquals(1, imovel.getId());
    }
}

