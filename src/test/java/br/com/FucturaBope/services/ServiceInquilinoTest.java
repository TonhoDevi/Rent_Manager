package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.repositorys.RepositoryAluguel;
import br.com.FucturaBope.repositorys.RepositoryImovel;
import br.com.FucturaBope.repositorys.RepositoryInquilino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceInquilinoTest {

    @Mock
    private RepositoryInquilino repositoryInquilino;

    @Mock
    private RepositoryImovel repositoryImovel;

    @Mock
    private RepositoryAluguel repositoryAluguel;

    @InjectMocks
    private ServiceInquilino serviceInquilino;

    private Inquilino inquilino;
    private Imovel imovel;
    private Aluguel aluguel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        inquilino = new Inquilino();
        inquilino.setId(1);
        inquilino.setNome("João");
        inquilino.setImovel(new ArrayList<>());
        inquilino.setAlugueis(new ArrayList<>());

        imovel = new Imovel();
        imovel.setId(10);
        imovel.setDescricao("Apartamento 101");
        imovel.setInquilino(inquilino);

        aluguel = new Aluguel();
        aluguel.setId(100);
        aluguel.setImovel(imovel);
        aluguel.setInquilino(inquilino);
    }

    @Test
    void testFindById_Success() {
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));

        Inquilino result = serviceInquilino.findById(1);

        assertNotNull(result);
        assertEquals("João", result.getNome());
    }

    @Test
    void testFindById_NotFound() {
        when(repositoryInquilino.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> serviceInquilino.findById(1));
    }

    @Test
    void testFindAll_Success() {
        when(repositoryInquilino.findAll()).thenReturn(List.of(inquilino));

        List<Inquilino> result = serviceInquilino.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testFindAll_NotFound() {
        when(repositoryInquilino.findAll()).thenReturn(List.of());

        assertThrows(ObjectNotFoundException.class, () -> serviceInquilino.findAll());
    }

    @Test
    void testSave() {
        when(repositoryInquilino.save(inquilino)).thenReturn(inquilino);

        Inquilino result = serviceInquilino.save(inquilino);

        assertEquals("João", result.getNome());
    }

    @Test
    void testUpdate() {
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));
        when(repositoryInquilino.save(inquilino)).thenReturn(inquilino);

        Inquilino result = serviceInquilino.update(inquilino);

        assertEquals("João", result.getNome());
    }

    @Test
    void testDelete_Success() {
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));

        serviceInquilino.delete(1);

        verify(repositoryInquilino, times(1)).deleteById(1);
    }

    @Test
    void testDelete_WithImoveis_ShouldThrowException() {
        inquilino.getImovel().add(imovel);
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));

        assertThrows(DataIntegrityViolationException.class, () -> serviceInquilino.delete(1));
    }

    @Test
    void testFindByNome_Success() {
        when(repositoryInquilino.findByNomeIgnoreCaseContaining("João")).thenReturn(Optional.of(inquilino));

        Inquilino result = serviceInquilino.findByNome("João");

        assertNotNull(result);
        assertEquals("João", result.getNome());
    }

    @Test
    void testFindByNome_NotFound() {
        when(repositoryInquilino.findByNomeIgnoreCaseContaining("Maria")).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> serviceInquilino.findByNome("Maria"));
    }

    @Test
    void testAddImovel() {
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));
        when(repositoryImovel.findById(10)).thenReturn(Optional.of(imovel));
        when(repositoryInquilino.save(any())).thenReturn(inquilino);

        Inquilino result = serviceInquilino.addImovel(1, 10);

        assertTrue(result.getImovel().contains(imovel));
    }

    @Test
    void testAddAluguel_Success() {
        inquilino.getImovel().add(imovel);
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));
        when(repositoryAluguel.findById(100)).thenReturn(Optional.of(aluguel));
        when(repositoryInquilino.save(any())).thenReturn(inquilino);

        Inquilino result = serviceInquilino.addAluguel(1, 100);

        assertTrue(result.getAlugueis().contains(aluguel));
    }

    @Test
    void testAddAluguel_ImovelNotLinked_ShouldThrowException() {
        when(repositoryInquilino.findById(1)).thenReturn(Optional.of(inquilino));
        when(repositoryAluguel.findById(100)).thenReturn(Optional.of(aluguel));

        assertThrows(DataIntegrityViolationException.class, () -> serviceInquilino.addAluguel(1, 100));
    }
}
