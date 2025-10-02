package br.com.FucturaBope.services;

import br.com.FucturaBope.dtos.DtoAluguel;
import br.com.FucturaBope.exceptions.ObjectNotFoundException;
import br.com.FucturaBope.exceptions.UnprocessableEntityException;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.repositorys.RepositoryAluguel;
import br.com.FucturaBope.repositorys.RepositoryImovel;
import br.com.FucturaBope.repositorys.RepositoryInquilino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAluguel {

    @Autowired
    private RepositoryAluguel repositoryAluguel;
    @Autowired
    private RepositoryImovel repositoryImovel;
    @Autowired
    private RepositoryInquilino repositoryInquilino;


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


    public Aluguel save(DtoAluguel dto) {
        Imovel imovel = repositoryImovel.findById(dto.getImovelId())
            .orElseThrow(() -> new ObjectNotFoundException("Imóvel não encontrado"));
        Inquilino inquilino = null;
        if (dto.getInquilinoId() != null) {
            inquilino = repositoryInquilino.findById(dto.getInquilinoId())
                .orElseThrow(() -> new ObjectNotFoundException("Inquilino não encontrado"));
        }
        Aluguel aluguel = new Aluguel();
        aluguel.setValor(dto.getValor());
        aluguel.setDataVencimento(dto.getDataVencimento());
        aluguel.setImovel(imovel);
        aluguel.setInquilino(inquilino);
        aluguel.setPago(dto.getPago());
        aluguel.setDiasAtraso((int) dto.getDiasAtraso());
        return repositoryAluguel.save(aluguel);
    }

    public Aluguel update(Integer id, DtoAluguel dto) {
        Aluguel entity = repositoryAluguel.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Aluguel não encontrado"));
        Imovel imovel = repositoryImovel.findById(dto.getImovelId())
            .orElseThrow(() -> new ObjectNotFoundException("Imóvel não encontrado"));
        Inquilino inquilino = null;
        if (dto.getInquilinoId() != null) {
            inquilino = repositoryInquilino.findById(dto.getInquilinoId())
                .orElseThrow(() -> new ObjectNotFoundException("Inquilino não encontrado"));
        }
        entity.setValor(dto.getValor());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setImovel(imovel);
        entity.setInquilino(inquilino);
        entity.setPago(dto.getPago());
        entity.setDiasAtraso((int) dto.getDiasAtraso());
        return repositoryAluguel.save(entity);
    }

    public void delete(Integer id) {
        findById(id);
        repositoryAluguel.deleteById(id);
    }
    public List<Aluguel> findAllOrderByValorDesc() {
        return repositoryAluguel.findAllByOrderByValorDesc();
    }

    public Aluguel pagar(Integer id) {
        Aluguel aluguel = repositoryAluguel.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));

        if (aluguel.getPago()) {
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

    public List<Aluguel> findAtrasados() {
        List<Aluguel> atrasados = repositoryAluguel.findByPagoFalseAndDataVencimentoBefore(LocalDate.now());
        for (Aluguel aluguel : atrasados) {
            long diasAtraso = 0;
            if (aluguel.getDataVencimento() != null) {
                LocalDate venc = aluguel.getDataVencimento().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                diasAtraso = ChronoUnit.DAYS.between(venc, LocalDate.now());
                diasAtraso = diasAtraso > 0 ? diasAtraso : 0;
            }
            aluguel.setDiasAtraso((int) diasAtraso);
        }
        return atrasados;
    }

}
