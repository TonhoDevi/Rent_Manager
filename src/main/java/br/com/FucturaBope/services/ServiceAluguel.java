package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.repositorys.RepositoryAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAluguel {

    @Autowired
    private RepositoryAluguel repositoryAluguel;


    public List<Aluguel> findAll() {
        List<Aluguel> list = repositoryAluguel.findAll();
        if (!list.isEmpty()) {
            return list;
        }
        throw new ObjectNotFoundException("Nenhum aluguel encontrado.");
    }

    public Aluguel findById(Integer id) {
        Optional<Aluguel> aluguel = repositoryAluguel.findById(id);
        if (aluguel.isPresent()) {
            return aluguel.get();
        }
        throw new ObjectNotFoundException("Aluguel não encontrada com o id: " + id);
    }


    public Aluguel save(Integer idCategoria, Aluguel aluguel){
        aluguel.setId(null);
        return repositoryAluguel.save(aluguel);
    }

    public Aluguel update(Integer idCategoria, Integer id, Aluguel aluguel) {
        Aluguel aluguelExistente = findById(id);

        aluguelExistente.setValor(aluguel.getValor());
        aluguelExistente.setDataVencimento(aluguel.getDataVencimento());
        aluguelExistente.setImovelId(aluguel.getImovelId());
        aluguelExistente.setInquilinoId(aluguel.getInquilinoId());
        return repositoryAluguel.save(aluguelExistente);
    }

    public void delete(Integer id) {
        findById(id);
        repositoryAluguel.deleteById(id);
    }
}
