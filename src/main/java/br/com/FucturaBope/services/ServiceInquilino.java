package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.repositorys.RepositoryImovel;
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

    @Autowired
    private RepositoryInquilino repositoryInquilino;

    @Autowired
    private RepositoryImovel repositoryImovel;

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
        Inquilino inq = inquilinoRepository.save(inquilino);
        return inq;
    }

    public Inquilino update(Inquilino inquilino) {
        findById(inquilino.getId());
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

    private void tratarDelete(Integer id) {
        Inquilino inq = findById(id);
        if (!inq.getImovel().isEmpty()) {
            throw new DataIntegrityViolationException("Inquilino não pode ser deletada, pois possui livros associados.");
        }
    }
    public Inquilino addImovel(Integer idInquilino, Integer idImovel) {
        Inquilino inquilino = repositoryInquilino.findById(idInquilino)
                .orElseThrow(() -> new ObjectNotFoundException("Inquilino não encontrado"));

        Imovel imovel = repositoryImovel.findById(idImovel)
                .orElseThrow(() -> new ObjectNotFoundException("Imóvel não encontrado"));

        imovel.setInquilino(inquilino);
        repositoryImovel.save(imovel);

        inquilino.getImovel().add(imovel);
        return repositoryInquilino.save(inquilino);
    }

}
