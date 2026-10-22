
package com.example.controleprojetos.service;

import com.example.controleprojetos.entity.Setor;
import com.example.controleprojetos.repository.SetorRepository;

import org.springframework.stereotype.Service;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor adicionar(Setor setor) {

        if (setor.getNome() == null || setor.getNome().isBlank()) {
            throw new RuntimeException("O nome do setor é obrigatório.");
        }

        return setorRepository.save(setor);
    }

    public Setor buscarSetorPorId(Integer id) {
        return setorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Setor não encontrado."));
    }
}
