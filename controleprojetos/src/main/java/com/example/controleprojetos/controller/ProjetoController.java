
package com.example.controleprojetos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controleprojetos.entity.Projeto;
import com.example.controleprojetos.service.ProjetoService;
import com.example.controleprojetos.dto.DadosProjetoDTO;
import com.example.controleprojetos.dto.ProjetoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        Projeto projeto = projetoService.buscarProjetoPorId(id);

        DadosProjetoDTO dto = new DadosProjetoDTO();
        dto.setId(projeto.getId());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataInicio(projeto.getDataInicio());
        dto.setDataFim(projeto.getDataFim());

        List<String> nomes = projeto.getFuncionarios()
                .stream()
                .map(funcionario -> funcionario.getNome())
                .toList();

        dto.setFuncionarios(nomes);

        return dto;
    }

    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    public DadosProjetoDTO vincularFuncionario(
            @PathVariable Integer idProjeto,
            @PathVariable Integer idFuncionario) {

        Projeto projeto = projetoService.vincularFuncionario(
                idProjeto, idFuncionario);

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
    } 
@PostMapping
public Projeto adicionar(@RequestBody ProjetoDTO dto) {
    Projeto projeto = new Projeto();

    projeto.setDescricao(dto.getDescricao());
    projeto.setDataInicio(dto.getDataInicio());
    projeto.setDataFim(dto.getDataFim());

    return projetoService.adicionar(projeto);
}

@GetMapping
public List<Projeto> listarTodos() {
    return projetoService.listarTodos();
}

    }
