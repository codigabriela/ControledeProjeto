
package com.example.controleprojetos.service;



import com.example.controleprojetos.dto.FuncionarioDTO;
import com.example.controleprojetos.entity.Setor;
import com.example.controleprojetos.repository.SetorRepository;
import com.example.controleprojetos.entity.Funcionario;
import com.example.controleprojetos.entity.Projeto;
import com.example.controleprojetos.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FuncionarioService {

    private final SetorRepository setorRepository;

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(
        FuncionarioRepository funcionarioRepository,
        SetorRepository setorRepository) {
    this.funcionarioRepository = funcionarioRepository;
    this.setorRepository = setorRepository;
}
    

    public List<Projeto> buscarProjetos(Integer idFuncionario) {

        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RuntimeException("Funcionário não encontrado.");
        }

        return funcionarioRepository.buscarProjetosPorFuncionario(idFuncionario);
    }
    
public Funcionario adicionar(FuncionarioDTO dto) {

    if (dto.getNome() == null || dto.getNome().isBlank()) {
        throw new RuntimeException("O nome é obrigatório.");
    }

    Setor setor = setorRepository.findById(dto.getIdSetor())
            .orElseThrow(() ->
                    new RuntimeException("Setor não encontrado."));

    Funcionario funcionario = new Funcionario();
    funcionario.setNome(dto.getNome());
    funcionario.setSetor(setor);

    return funcionarioRepository.save(funcionario);
}

}
