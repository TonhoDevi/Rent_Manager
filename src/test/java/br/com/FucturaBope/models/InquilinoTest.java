package br.com.FucturaBope.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InquilinoTest {
    @Test
    void testInquilinoId() {
        Inquilino inquilino = new Inquilino();
        inquilino.setId(1);
        assertEquals(1, inquilino.getId());
    }
}

