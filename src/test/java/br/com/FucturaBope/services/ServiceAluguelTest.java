package br.com.FucturaBope.services;

import br.com.FucturaBope.dtos.DtoAluguel;
import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.exceptions.UnprocessableEntityException;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.repositorys.RepositoryAluguel;
import br.com.FucturaBope.repositorys.RepositoryImovel;
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
    private RepositoryImovel repositoryImovel;

    @Mock
    private RepositoryAluguel repositoryAluguel;

    @InjectMocks
    private ServiceAluguel serviceAluguel;

    private Aluguel aluguel;
    private Imovel imovelMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        imovelMock = new Imovel();
        imovelMock.setId(1);

        aluguel = new Aluguel();
        aluguel.setId(1);
        aluguel.setValor(1200.0);
        aluguel.setPago(false);
        aluguel.setImovel(imovelMock);

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
        when(repositoryImovel.findById(1)).thenReturn(Optional.of(imovelMock));
        when(repositoryAluguel.save(any(Aluguel.class))).thenAnswer(inv -> inv.getArgument(0));

        Aluguel toSave = new Aluguel();
        toSave.setValor(100.0);
        toSave.setImovel(imovelMock);

        Aluguel saved = serviceAluguel.save(new DtoAluguel(toSave));

        assertNotNull(saved);
        assertEquals(100.0, saved.getValor());
        assertEquals(1, saved.getImovel().getId());
    }

    @Test
    void testSave_invalidValue_throwsException() {
        when(repositoryImovel.findById(1)).thenReturn(Optional.of(imovelMock));

        Aluguel invalido = new Aluguel();
        invalido.setValor(-10.0);
        invalido.setImovel(imovelMock);

        DtoAluguel dto = new DtoAluguel(invalido);

        assertThrows(UnprocessableEntityException.class, () -> serviceAluguel.save(dto));
    }

    @Test
    void testUpdate_ok() {
        when(repositoryAluguel.findById(1)).thenReturn(Optional.of(aluguel));
        when(repositoryImovel.findById(1)).thenReturn(Optional.of(imovelMock));
        when(repositoryAluguel.save(any(Aluguel.class))).thenAnswer(inv -> inv.getArgument(0));

        Aluguel updatedAluguel = new Aluguel();
        updatedAluguel.setValor(150.0);
        updatedAluguel.setImovel(imovelMock);

        Aluguel updated = serviceAluguel.update(1, new DtoAluguel(updatedAluguel));

        assertNotNull(updated);
        assertEquals(150.0, updated.getValor());
        assertEquals(1, updated.getImovel().getId());
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
        when(repositoryAluguel.findByPagoFalseAndDataVencimentoBefore(any()))
                .thenReturn(Arrays.asList(aluguel));

        List<Aluguel> atrasados = serviceAluguel.findAtrasados();

        assertEquals(1, atrasados.size());
        assertTrue(atrasados.get(0).getDiasAtraso() >= 0);
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
