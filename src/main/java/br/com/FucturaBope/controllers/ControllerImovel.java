package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoImovel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.services.ServiceImovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imoveis")
public class ControllerImovel {

    @Autowired
    private ServiceImovel serviceImovel;

    // GET /imoveis/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DtoImovel> getById(@PathVariable Integer id) {
        Imovel imovel = serviceImovel.findById(id);
        return ResponseEntity.ok(toDTO(imovel));
    }

    // GET /imoveis/inquilino/{idInquilino}
    @GetMapping("/inquilino/{idInquilino}")
    public ResponseEntity<List<DtoImovel>> getByInquilino(@PathVariable Integer idInquilino) {
        List<Imovel> lista = serviceImovel.findAllByInquilinoId(idInquilino);
        List<DtoImovel> dtoList = lista.stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // POST /imoveis
    @PostMapping
    public ResponseEntity<DtoImovel> create(@RequestBody DtoImovel dto) {
        Imovel imovel = fromDTO(dto);
        Imovel criado = serviceImovel.save(dto.getInquilino().getId(), imovel);
        return new ResponseEntity<>(toDTO(criado), HttpStatus.CREATED);
    }

    // PUT /imoveis/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DtoImovel> update(@PathVariable Integer id, @RequestBody DtoImovel dto) {
        Imovel imovel = fromDTO(dto);
        Imovel atualizado = serviceImovel.update(dto.getInquilino().getId(), id, imovel);
        return ResponseEntity.ok(toDTO(atualizado));
    }

    // DELETE /imoveis/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceImovel.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =========================
    // Conversões entre Entity e DTO
    // =========================
    private DtoImovel toDTO(Imovel imovel) {
        DtoImovel dto = new DtoImovel();
        dto.setId(imovel.getId());
        dto.setNome(imovel.getNome());
        dto.setDescricao(imovel.getDescricao());
        dto.setInquilino(imovel.getInquilino() != null ? imovel.getInquilino() : null);
        return dto;
    }

    private Imovel fromDTO(DtoImovel dto) {
        Imovel imovel = new Imovel();
        imovel.setNome(dto.getNome());
        imovel.setDescricao(dto.getDescricao());
        return imovel;
    }
}
