package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.repositorys.RepositoryImovel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImovel {
    @Autowired
    private RepositoryImovel imovelRepository;

    @Autowired
    private ServiceInquilino inquilinoService;

    public Imovel findById(Integer id) {
        Optional<Imovel> imovel = imovelRepository.findById(id);
        if (imovel.isPresent()) {
            return imovel.get();
        }
        throw new ObjectNotFoundException("Imovel não encontrada com o id: " + id);
    }

    public List<Imovel> findAllByInquilinoId(Integer idInquilino) {
        inquilinoService.findById(idInquilino);
        List<Imovel> list = imovelRepository.findAllByInquilinoId(idInquilino);
        return list;
    }
    @Transactional
    public Imovel save(Integer idInquilino, Imovel imovel){
        imovel.setId(null);
        Inquilino inquilino = inquilinoService.findById(idInquilino);
        imovel.setInquilino(inquilino);
        return imovelRepository.save(imovel);
    }
    @Transactional
    public Imovel update(Integer idInquilino, Integer id, Imovel imovel) {
        Imovel imovelExistente = findById(id);
        imovelExistente.setNome(imovel.getNome());
        imovelExistente.setDescricao(imovel.getDescricao());
        imovelExistente.setInquilino(imovel.getInquilino());
        Inquilino inquilino = inquilinoService.findById(idInquilino);
        imovelExistente.setInquilino(inquilino);

        return imovelRepository.save(imovelExistente);
    }
    @Transactional
    public void delete(Integer id) {
        findById(id);
        imovelRepository.deleteById(id);
    }
    
}
