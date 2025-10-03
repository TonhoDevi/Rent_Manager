package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoInquilino;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.services.ServiceInquilino;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inquilinos")
@Tag(name = "Inquilino", description = "Endpoints para gerenciar inquilinos")
public class ControllerInquilino {

    @Autowired
    private ServiceInquilino serviceInquilino;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Listar todos os inquilinos", description = "Retorna uma lista de todos os inquilinos cadastrados")
    public ResponseEntity<List<DtoInquilino>> findAll() {
        List<Inquilino> inquilinos = serviceInquilino.findAll();
        List<DtoInquilino> dtos = inquilinos.stream()
                .map(DtoInquilino::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar inquilino por ID", description = "Retorna os detalhes de um inquilino específico pelo seu ID")
    public ResponseEntity<DtoInquilino> findById(@PathVariable Integer id) {
        Inquilino inquilino = serviceInquilino.findById(id);
        return ResponseEntity.ok(new DtoInquilino(inquilino));
    }

    @PostMapping
    @Operation(summary = "Criar novo inquilino", description = "Cadastra um novo inquilino no sistema")
    public ResponseEntity<DtoInquilino> create(@RequestBody DtoInquilino dto) {
        Inquilino inquilino = modelMapper.map(dto, Inquilino.class);
        Inquilino saved = serviceInquilino.save(inquilino);
        return ResponseEntity.ok(new DtoInquilino(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar inquilino", description = "Atualiza os dados de um inquilino existente pelo seu ID")
    public ResponseEntity<DtoInquilino> update(@PathVariable Integer id, @RequestBody DtoInquilino dto) {
        dto.setId(id);
        Inquilino inquilino = modelMapper.map(dto, Inquilino.class);
        Inquilino updated = serviceInquilino.update(inquilino);
        return ResponseEntity.ok(new DtoInquilino(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar inquilino", description = "Remove um inquilino do sistema pelo seu ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceInquilino.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Associa um imóvel
    @PutMapping("/{idInquilino}/imoveis/{idImovel}")
    @Operation(summary = "Associar imóvel ao inquilino", description = "Vincula um imóvel existente a um inquilino")
    public ResponseEntity<DtoInquilino> addImovel(@PathVariable Integer idInquilino,
                                                  @PathVariable Integer idImovel) {
        Inquilino inquilino = serviceInquilino.addImovel(idInquilino, idImovel);
        return ResponseEntity.ok(new DtoInquilino(inquilino));
    }

    // Vincula um aluguel existente ao inquilino
    @PutMapping("/{idInquilino}/alugueis/{idAluguel}")
    @Operation(summary = "Associar aluguel ao inquilino", description = "Vincula um aluguel existente a um inquilino")
    public ResponseEntity<DtoInquilino> addAluguelToInquilino(@PathVariable Integer idInquilino, @PathVariable Integer idAluguel) {
        Inquilino inquilino = serviceInquilino.addAluguelToInquilino(idInquilino, idAluguel);
        return ResponseEntity.ok(new DtoInquilino(inquilino));
    }
}