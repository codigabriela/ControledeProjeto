
package com.example.controleprojetos.controller;

import com.example.controleprojetos.dto.DadosProjetoDTO;
import com.example.controleprojetos.entity.Projeto;
import com.example.controleprojetos.service.FuncionarioService;

import com.example.controleprojetos.dto.FuncionarioDTO;
import com.example.controleprojetos.entity.Funcionario;


import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(
            @PathVariable Integer idFuncionario) {

        List<Projeto> projetos =
                funcionarioService.buscarProjetos(idFuncionario);

        return projetos.stream().map(projeto -> {
            DadosProjetoDTO dto = new DadosProjetoDTO();

            dto.setId(projeto.getId());
            dto.setDescricao(projeto.getDescricao());
            dto.setDataInicio(projeto.getDataInicio());
            dto.setDataFim(projeto.getDataFim());

            dto.setFuncionarios(projeto.getFuncionarios()
                    .stream()
                    .map(funcionario -> funcionario.getNome())
                    .toList());

            return dto;
        }).toList();
    }
    
@PostMapping
public Funcionario adicionar(@RequestBody FuncionarioDTO dto) {
    return funcionarioService.adicionar(dto);
}

}
