package br.com.FucturaBope.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Inquilino> inquilino = new ArrayList<>();

    public Imovel() {
    }

    public Imovel(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public List<Inquilino> getInquilinos() {
        return inquilino;
    }

    public void setInquilinos(List<Inquilino> inquilinos) {
        this.inquilino = inquilinos;
    }
}