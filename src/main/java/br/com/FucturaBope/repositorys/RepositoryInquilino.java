package br.com.FucturaBope.repositorys;

import br.com.FucturaBope.models.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryInquilino extends JpaRepository<Inquilino, Integer> {
    Optional<Inquilino> findByNomeIgnoreCaseContaining(String nome);

    Optional<Inquilino> findByNomeIgnoreCase(String nome);
}
