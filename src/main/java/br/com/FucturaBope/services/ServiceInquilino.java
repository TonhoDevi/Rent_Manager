package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.repositorys.RepositoryInquilino;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ServiceInquilino {

    @Autowired
    private RepositoryInquilino inquilinoRepository;

    public Inquilino findById(Integer id) {

        Optional<Inquilino> inq = inquilinoRepository.findById(id);
        if (inq.isPresent()) {
            return inq.get();
        }
        throw new ObjectNotFoundException("Inquilino não encontrada com o id: " + id);
    }

    public List<Inquilino> findAll() {
        List<Inquilino> list = inquilinoRepository.findAll();
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Nenhuma inquilino encontrada.");
    }

    public Inquilino save(Inquilino inquilino) {
        buscarPorNome(inquilino);
        Inquilino inq = inquilinoRepository.save(inquilino);
        return inq;
    }

    public Inquilino update(Inquilino inquilino) {
        findById(inquilino.getId());
        buscarPorNome(inquilino);
        return inquilinoRepository.save(inquilino);

    }

    public void delete(Integer id) {
        tratarDelete(id);
        inquilinoRepository.deleteById(id);
    }

    public Inquilino findByNome(String nome) {
        Optional<Inquilino> inq = inquilinoRepository.findByNomeIgnoreCaseContaining(nome);
        if (inq.isPresent()) {
            return inq.get();
        }
        throw new ObjectNotFoundException("Inquilino não encontrada com este nome: " + nome);
    }

    private void buscarPorNome(Inquilino inquilino) {
        Optional<Inquilino> inq = inquilinoRepository.findByNomeIgnoreCase(inquilino.getNome());
        if (inq.isPresent() && !inq.get().getId().equals(inquilino.getId())) {
            throw new IllegalArgumentException("Inquilino já existe com o nome: " + inquilino.getNome());
        }
    }

    private void tratarDelete(Integer id) {
        Inquilino inq = findById(id);
        if (!inq.getImovel().isEmpty()) {
            throw new DataIntegrityViolationException("Inquilino não pode ser deletada, pois possui livros associados.");
        }
    }
}
