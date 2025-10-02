package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoImovel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.services.ServiceImovel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imoveis")
public class ControllerImovel {

    @Autowired
    private ServiceImovel serviceImovel;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<List<DtoImovel>> findAll() {
        List<Imovel> list = serviceImovel.findAll();
        return ResponseEntity.ok().body(list.stream()
                .map(obj -> modelMapper.map(obj, DtoImovel.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoImovel> findById(@PathVariable Integer id) {
        Imovel imovel = serviceImovel.findById(id);
        return ResponseEntity.ok().body(modelMapper.map(imovel, DtoImovel.class));
    }

    @GetMapping
    public ResponseEntity<List<DtoImovel>> findAllByInquilino(@RequestParam(value = "inquilino", defaultValue = "0") Integer idInquilino) {
        List<Imovel> list = serviceImovel.findAllByInquilinoId(idInquilino);
        return ResponseEntity.ok().body(list.stream()
                .map(obj -> modelMapper.map(obj, DtoImovel.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<DtoImovel> save(@RequestParam(value = "inquilino", defaultValue = "0") Integer idInquilino,
                                          @RequestBody DtoImovel imovelDto) {
        Imovel imovel = serviceImovel.save(idInquilino, modelMapper.map(imovelDto, Imovel.class));
        return ResponseEntity.ok().body(modelMapper.map(imovel, DtoImovel.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoImovel> update(@RequestParam(value = "inquilino", defaultValue = "0") Integer idInquilino,
                                            @PathVariable Integer id,
                                            @RequestBody DtoImovel imovelDto) {
        Imovel imovel = serviceImovel.update(idInquilino, id, modelMapper.map(imovelDto, Imovel.class));
        return ResponseEntity.ok().body(modelMapper.map(imovel, DtoImovel.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceImovel.delete(id);
        return ResponseEntity.noContent().build();
    }

}
