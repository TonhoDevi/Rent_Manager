package br.com.FucturaBope.repositorys;

import br.com.FucturaBope.models.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryImovel extends JpaRepository<Imovel, Integer> {
    List<Imovel> findAllByInquilinoId(Integer idInquilino);
}
