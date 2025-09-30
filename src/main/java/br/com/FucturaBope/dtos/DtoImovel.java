package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DtoImovel {

    @Autowired
    private ModelMapper modelMapper;

    private Integer id;
    private String nome;
    private String descricao;

    private Inquilino inquilino;
    private Aluguel aluguel;

    public DtoImovel() {
    }

    public DtoImovel(Integer id, String nome, String descricao, Inquilino inquilino, Aluguel aluguel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inquilino = inquilino;
        this.aluguel = aluguel;
    }
    public DtoImovel(Imovel imovel) {
        this.id = imovel.getId();
        this.nome = imovel.getNome();
        this.descricao = imovel.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
}
