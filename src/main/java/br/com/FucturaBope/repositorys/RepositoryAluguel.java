package br.com.FucturaBope.repositorys;

import br.com.FucturaBope.models.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryAluguel extends JpaRepository<Aluguel, Integer> {
    List<Aluguel> findByPagoTrue();
    List<Aluguel> findByPagoFalse();
    List<Aluguel> findAllByOrderByValorDesc();
    List<Aluguel> findByDataVencimentoBefore(LocalDate data);
    List<Aluguel> findByPagoFalseAndDataVencimentoBefore(LocalDate now);


}
