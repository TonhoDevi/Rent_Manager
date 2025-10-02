package br.com.FucturaBope.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ControllerImovel.class)
public class ControllerImovelTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // Testa se o contexto carrega
    }
}

