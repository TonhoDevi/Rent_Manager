package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.repositorys.RepositoryImovel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceImovelTest {

    @Mock
    private RepositoryImovel imovelRepository;

    @Mock
    private ServiceInquilino inquilinoService;

    @InjectMocks
    private ServiceImovel serviceImovel;

    private Imovel imovel;
    private Inquilino inquilino;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        inquilino = new Inquilino();
        inquilino.setId(1);
        inquilino.setNome("Carlos Silva");

        imovel = new Imovel();
        imovel.setId(10);
        imovel.setDescricao("Apartamento");
        imovel.setEndereco("Rua A, 123");
        imovel.setInquilino(inquilino);
    }

    @Test
    void testFindAll_ok() {
        when(imovelRepository.findAll()).thenReturn(Arrays.asList(imovel));

        List<Imovel> result = serviceImovel.findAll();

        assertEquals(1, result.size());
        assertEquals("Apartamento", result.get(0).getDescricao());
    }

    @Test
    void testFindAll_empty_throwsException() {
        when(imovelRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ObjectNotFoundException.class, () -> serviceImovel.findAll());
    }

    @Test
    void testFindById_ok() {
        when(imovelRepository.findById(10)).thenReturn(Optional.of(imovel));

        Imovel result = serviceImovel.findById(10);

        assertNotNull(result);
        assertEquals("Rua A, 123", result.getEndereco());
    }

    @Test
    void testFindById_notFound() {
        when(imovelRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> serviceImovel.findById(99));
    }

    @Test
    void testFindAllByInquilinoId_ok() {
        when(inquilinoService.findById(1)).thenReturn(inquilino);
        when(imovelRepository.findAllByInquilinoId(1)).thenReturn(Arrays.asList(imovel));

        List<Imovel> result = serviceImovel.findAllByInquilinoId(1);

        assertEquals(1, result.size());
        assertEquals("Apartamento", result.get(0).getDescricao());
    }

    @Test
    void testSave_ok() {
        Imovel novo = new Imovel();
        novo.setDescricao("Casa");
        novo.setEndereco("Rua B, 456");

        when(inquilinoService.findById(1)).thenReturn(inquilino);
        when(imovelRepository.save(any(Imovel.class))).thenAnswer(inv -> {
            Imovel i = inv.getArgument(0);
            i.setId(11);
            return i;
        });

        Imovel result = serviceImovel.save(1, novo);

        assertNotNull(result.getId());
        assertEquals("Carlos Silva", result.getInquilino().getNome());
    }

    @Test
    void testUpdate_ok() {
        Imovel update = new Imovel();
        update.setDescricao("Casa Reformada");
        update.setEndereco("Rua C, 789");
        update.setInquilino(inquilino);

        when(imovelRepository.findById(10)).thenReturn(Optional.of(imovel));
        when(inquilinoService.findById(1)).thenReturn(inquilino);
        when(imovelRepository.save(any(Imovel.class))).thenAnswer(inv -> inv.getArgument(0));

        Imovel result = serviceImovel.update(1, 10, update);

        assertEquals("Casa Reformada", result.getDescricao());
        assertEquals("Rua C, 789", result.getEndereco());
        assertEquals("Carlos Silva", result.getInquilino().getNome());
    }

    @Test
    void testDelete_ok() {
        when(imovelRepository.findById(10)).thenReturn(Optional.of(imovel));
        doNothing().when(imovelRepository).deleteById(10);

        serviceImovel.delete(10);

        verify(imovelRepository, times(1)).deleteById(10);
    }

    @Test
    void testDelete_notFound() {
        when(imovelRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> serviceImovel.delete(99));
    }
}
