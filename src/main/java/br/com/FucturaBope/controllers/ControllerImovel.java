package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoImovel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.services.ServiceImovel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Imóvel", description = "Endpoints para gerenciar imóveis")
@RequestMapping("/imoveis")
public class ControllerImovel {

    @Autowired
    private ServiceImovel serviceImovel;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Listar todos os imóveis", description = "Retorna uma lista de todos os imóveis cadastrados")
    public ResponseEntity<List<DtoImovel>> findAll() {
        List<Imovel> list = serviceImovel.findAll();
        return ResponseEntity.ok().body(list.stream()
                .map(obj -> modelMapper.map(obj, DtoImovel.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar imóvel por ID", description = "Retorna os detalhes de um imóvel específico pelo seu ID")
    public ResponseEntity<DtoImovel> findById(@PathVariable Integer id) {
        Imovel imovel = serviceImovel.findById(id);
        return ResponseEntity.ok().body(modelMapper.map(imovel, DtoImovel.class));
    }

    @GetMapping
    @Operation(summary = "Listar imóveis por inquilino", description = "Retorna uma lista de imóveis associados a um inquilino específico")
    public ResponseEntity<List<DtoImovel>> findAllByInquilino(@RequestParam(value = "inquilino", defaultValue = "0") Integer idInquilino) {
        List<Imovel> list = serviceImovel.findAllByInquilinoId(idInquilino);
        return ResponseEntity.ok().body(list.stream()
                .map(obj -> modelMapper.map(obj, DtoImovel.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    @Operation(summary = "Criar novo imóvel", description = "Cadastra um novo imóvel no sistema, opcionalmente associando-o a um inquilino")
    public ResponseEntity<DtoImovel> save(@RequestParam(value = "inquilino", defaultValue = "0") Integer idInquilino,
                                          @RequestBody DtoImovel imovelDto) {
        Imovel imovel = serviceImovel.save(idInquilino, modelMapper.map(imovelDto, Imovel.class));
        return ResponseEntity.ok().body(modelMapper.map(imovel, DtoImovel.class));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar imóvel", description = "Atualiza os dados de um imóvel existente pelo seu ID, opcionalmente associando-o a um inquilino")
    public ResponseEntity<DtoImovel> update(@RequestParam(value = "inquilino", defaultValue = "0") Integer idInquilino,
                                            @PathVariable Integer id,
                                            @RequestBody DtoImovel imovelDto) {
        Imovel imovel = serviceImovel.update(idInquilino, id, modelMapper.map(imovelDto, Imovel.class));
        return ResponseEntity.ok().body(modelMapper.map(imovel, DtoImovel.class));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar imóvel", description = "Remove um imóvel do sistema pelo seu ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceImovel.delete(id);
        return ResponseEntity.noContent().build();
    }

}
