package br.com.FucturaBope.repositorys;

import br.com.FucturaBope.models.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryAluguel extends JpaRepository<Aluguel, Integer> {
}
