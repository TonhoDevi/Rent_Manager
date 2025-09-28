package br.com.FucturaBope.models;

import jakarta.persistence.*;

@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquilino_id")
    private Inquilino inquilino;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluguel_id")
    private  Aluguel aluguel;

    public Imovel() {
    }

    public Imovel(Integer id, String nome, String descricao, Inquilino inquilino, Aluguel aluguel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inquilino = inquilino;
        this.aluguel = aluguel;
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