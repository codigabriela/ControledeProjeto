
package com.example.controleprojetos.controller;

import com.example.controleprojetos.dto.DadosSetorDTO;
import com.example.controleprojetos.dto.SetorDTO;
import com.example.controleprojetos.entity.Setor;
import com.example.controleprojetos.service.SetorService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public Setor adicionar(@RequestBody SetorDTO dto) {
        Setor setor = new Setor();
        setor.setNome(dto.getNome());

        return setorService.adicionar(setor);
    }

    @GetMapping("/{id}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer id) {
        Setor setor = setorService.buscarSetorPorId(id);

        DadosSetorDTO dto = new DadosSetorDTO();
        dto.setId(setor.getId());
        dto.setNome(setor.getNome());

        dto.setFuncionarios(setor.getFuncionarios()
                .stream()
                .map(funcionario -> funcionario.getNome())
                .toList());

        return dto;
    }
}
