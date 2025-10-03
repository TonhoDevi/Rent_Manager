package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoInquilino {

    private Integer id;
    private String nome;
    private String email;
    private List<Integer> imovelIds = new ArrayList<>();
    private List<Integer> aluguelIds = new ArrayList<>();

    public DtoInquilino() {}

    public DtoInquilino(Integer id, String nome, String email, List<Integer> imovelIds, List<Integer> aluguelIds) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.imovelIds = imovelIds;
        this.aluguelIds = aluguelIds;
    }

    public DtoInquilino(Inquilino inquilino) {
        this.id = inquilino.getId();
        this.nome = inquilino.getNome();
        this.email = inquilino.getEmail();
        if (inquilino.getImovel() != null) {
            this.imovelIds = inquilino.getImovel().stream().map(Imovel::getId).collect(Collectors.toList());
        }
        if (inquilino.getAlugueis() != null) {
            this.aluguelIds = inquilino.getAlugueis().stream().map(Aluguel::getId).collect(Collectors.toList());
        }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Integer> getImovelIds() { return imovelIds; }
    public void setImovelIds(List<Integer> imovelIds) { this.imovelIds = imovelIds; }
    public List<Integer> getAluguelIds() { return aluguelIds; }
    public void setAluguelIds(List<Integer> aluguelIds) { this.aluguelIds = aluguelIds; }
}
