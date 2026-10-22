
package com.example.controleprojetos.service;

import com.example.controleprojetos.entity.Projeto;
import com.example.controleprojetos.entity.Funcionario;
import com.example.controleprojetos.repository.ProjetoRepository;
import com.example.controleprojetos.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoService(
            ProjetoRepository projetoRepository,
            FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Projeto adicionar(Projeto projeto) {
        if (projeto.getDescricao() == null || projeto.getDescricao().isBlank()) {
            throw new RuntimeException("A descrição é obrigatória.");
        }

        if (projeto.getDataInicio() == null || projeto.getDataFim() == null) {
            throw new RuntimeException("As datas são obrigatórias.");
        }

        if (projeto.getDataFim().isBefore(projeto.getDataInicio())) {
            throw new RuntimeException(
                    "A data final não pode ser anterior à data inicial.");
        }

        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarProjetoPorId(Integer id) {
        return projetoRepository.buscarProjetoComFuncionarios(id)
                .orElseThrow(() ->
                        new RuntimeException("Projeto não encontrado."));
    }

    public Projeto vincularFuncionario(
            Integer idProjeto, Integer idFuncionario) {

        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() ->
                        new RuntimeException("Projeto não encontrado."));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado."));

        if (!projeto.getFuncionarios().contains(funcionario)) {
            projeto.getFuncionarios().add(funcionario);
        }

        return projetoRepository.save(projeto);
    }
}
