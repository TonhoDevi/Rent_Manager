package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoInquilino;
import br.com.FucturaBope.models.Inquilino;
import br.com.FucturaBope.services.ServiceInquilino;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inquilinos")
public class ControllerInquilino {

    @Autowired
    private ServiceInquilino serviceInquilino;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DtoInquilino>> findAll() {
        List<Inquilino> inquilinos = serviceInquilino.findAll();
        List<DtoInquilino> dtos = inquilinos.stream()
                .map(DtoInquilino::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoInquilino> findById(@PathVariable Integer id) {
        Inquilino inquilino = serviceInquilino.findById(id);
        return ResponseEntity.ok(new DtoInquilino(inquilino));
    }

    @PostMapping
    public ResponseEntity<DtoInquilino> create(@RequestBody DtoInquilino dto) {
        Inquilino inquilino = modelMapper.map(dto, Inquilino.class);
        Inquilino saved = serviceInquilino.save(inquilino);
        return ResponseEntity.ok(new DtoInquilino(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoInquilino> update(@PathVariable Integer id, @RequestBody DtoInquilino dto) {
        dto.setId(id);
        Inquilino inquilino = modelMapper.map(dto, Inquilino.class);
        Inquilino updated = serviceInquilino.update(inquilino);
        return ResponseEntity.ok(new DtoInquilino(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceInquilino.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Associa um imóvel
    @PutMapping("/{idInquilino}/imoveis/{idImovel}")
    public ResponseEntity<DtoInquilino> addImovel(@PathVariable Integer idInquilino,
                                                  @PathVariable Integer idImovel) {
        Inquilino inquilino = serviceInquilino.addImovel(idInquilino, idImovel);
        return ResponseEntity.ok(new DtoInquilino(inquilino));
    }
}