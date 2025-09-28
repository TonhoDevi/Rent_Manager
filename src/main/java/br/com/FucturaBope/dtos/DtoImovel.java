package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import java.util.ArrayList;
import java.util.List;

public class DtoImovel {
    private Integer id;
    private String nome;
    private String descricao;

    private Inquilino inquilino;

    public DtoImovel() {
    }

    public DtoImovel(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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
}
