package br.com.FucturaBope.services;

import br.com.FucturaBope.dtos.DtoAluguel;
import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.exceptions.UnprocessableEntityException;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.repositorys.RepositoryAluguel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceAluguelTest {

    @Mock
    private RepositoryAluguel repositoryAluguel;

    @InjectMocks
    private ServiceAluguel serviceAluguel;

    private Aluguel aluguel;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        aluguel = new Aluguel();
        aluguel.setId(1);
        aluguel.setValor(1200.0); // pelo teu código, valor é numérico simples
        aluguel.setPago(false);

        // Data de vencimento atrasada
        aluguel.setDataVencimento(new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    void testFindAll_ok() {
        when(repositoryAluguel.findAll()).thenReturn(Arrays.asList(aluguel));

        List<Aluguel> result = serviceAluguel.findAll();

        assertEquals(1, result.size());
        assertEquals(1200.0, result.get(0).getValor());
    }

    @Test
    void testFindAll_empty_throwsException() {
        when(repositoryAluguel.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ObjectNotFoundException.class, () -> serviceAluguel.findAll());
    }

    @Test
    void testFindById_ok() {
        when(repositoryAluguel.findById(1)).thenReturn(Optional.of(aluguel));

        Aluguel result = serviceAluguel.findById(1);

        assertEquals(1, result.getId());
        assertEquals(1200.0, result.getValor());
    }

    @Test
    void testFindById_notFound() {
        when(repositoryAluguel.findById(99)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> serviceAluguel.findById(99));
    }

    @Test
    void testSave_ok() {
        Aluguel novo = new Aluguel();
        novo.setValor(1500.0);
        novo.setPago(false);

        when(repositoryAluguel.save(any(Aluguel.class))).thenAnswer(inv -> {
            Aluguel a = inv.getArgument(0);
            a.setId(2);
            return a;
        });

        Aluguel result = serviceAluguel.save(novo);

        assertNotNull(result.getId());
        assertEquals(1500.0, result.getValor());
    }

    @Test
    void testSave_invalidValue_throwsException() {
        Aluguel invalido = new Aluguel();
        invalido.setValor(-10.0);

        assertThrows(UnprocessableEntityException.class, () -> serviceAluguel.save(invalido));
    }

    @Test
    void testUpdate_ok() {
        when(repositoryAluguel.findById(1)).thenReturn(Optional.of(aluguel));
        when(repositoryAluguel.save(any(Aluguel.class))).thenAnswer(inv -> inv.getArgument(0));

        Aluguel update = new Aluguel();
        update.setValor(2000.0);
        update.setPago(true);
        update.setDataVencimento(new Date());

        Aluguel result = serviceAluguel.update(1, update);

        assertEquals(2000.0, result.getValor());
        assertTrue(result.getPago());
    }

    @Test
    void testPagar_ok() {
        when(repositoryAluguel.findById(1)).thenReturn(Optional.of(aluguel));
        when(repositoryAluguel.save(any(Aluguel.class))).thenAnswer(inv -> inv.getArgument(0));

        Aluguel result = serviceAluguel.pagar(1);

        assertTrue(result.getPago());
    }

    @Test
    void testPagar_alreadyPaid_throwsException() {
        aluguel.setPago(true);
        when(repositoryAluguel.findById(1)).thenReturn(Optional.of(aluguel));

        assertThrows(RuntimeException.class, () -> serviceAluguel.pagar(1));
    }

    @Test
    void testFindAtrasados_ok() {
        when(repositoryAluguel.findByPagoFalseAndDataVencimentoBefore(any(java.time.LocalDate.class)))
                .thenReturn(Arrays.asList(aluguel));

        List<Aluguel> atrasados = serviceAluguel.findAtrasados();

        assertEquals(1, atrasados.size());
        assertTrue(atrasados.get(0).getDiasAtraso() > 0);
    }

    @Test
    void testFindAllPagos() {
        aluguel.setPago(true);
        when(repositoryAluguel.findByPagoTrue()).thenReturn(Arrays.asList(aluguel));

        List<Aluguel> result = serviceAluguel.findAllPagos();

        assertEquals(1, result.size());
        assertTrue(result.get(0).getPago());
    }

    @Test
    void testFindAllNaoPagos() {
        aluguel.setPago(false);
        when(repositoryAluguel.findByPagoFalse()).thenReturn(Arrays.asList(aluguel));

        List<Aluguel> result = serviceAluguel.findAllNaoPagos();

        assertEquals(1, result.size());
        assertFalse(result.get(0).getPago());
    }
}
