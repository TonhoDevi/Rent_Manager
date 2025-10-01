package br.com.FucturaBope.services;

import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.exceptions.UnprocessableEntityException;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.repositorys.RepositoryAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public Aluguel save(Aluguel aluguel) {
        aluguel.setId(null);

        if (aluguel.getValor() == null || aluguel.getValor() <= 0) {
            throw new UnprocessableEntityException("O valor do aluguel deve ser um número positivo.");
        }

        return repositoryAluguel.save(aluguel);
    }

    public Aluguel update(Integer id, Aluguel aluguel) {
        Aluguel entity = repositoryAluguel.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Aluguel não encontrado"));

        // aqui você aplica as validações
        if (aluguel.getValor() == null || aluguel.getValor() <= 0) {
            throw new UnprocessableEntityException("O valor do aluguel deve ser um número positivo.");
        }

        // atualizar apenas os campos necessários
        entity.setValor(aluguel.getValor());
        entity.setDataVencimento(aluguel.getDataVencimento());
        entity.setPago(aluguel.getPago());

        return repositoryAluguel.save(entity);
    }


    public void delete(Integer id) {
        findById(id);
        repositoryAluguel.deleteById(id);
    }
    public List<Aluguel> findAllOrderByValorDesc() {
        return repositoryAluguel.findAllByOrderByValorDesc();
    }

    public List<Aluguel> findAllVencidos() {
        LocalDate hoje = LocalDate.now();
        return repositoryAluguel.findByDataVencimentoBefore(hoje);
    }
    public Aluguel pagar(Integer id) {
        Aluguel aluguel = repositoryAluguel.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));

        if (Boolean.TRUE.equals(aluguel.getPago())) {
            throw new RuntimeException("Aluguel já está pago");
        }

        aluguel.setPago(true);
        return repositoryAluguel.save(aluguel);
    }

    public List<Aluguel> findAllPagos() {
        return repositoryAluguel.findByPagoTrue();
    }

    public List<Aluguel> findAllNaoPagos() {
        return repositoryAluguel.findByPagoFalse();
    }

}
