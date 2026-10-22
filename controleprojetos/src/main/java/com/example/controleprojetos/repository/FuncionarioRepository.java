
package com.example.controleprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.controleprojetos.entity.Funcionario;
import com.example.controleprojetos.entity.Projeto;

public interface FuncionarioRepository
        extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT DISTINCT p FROM Projeto p JOIN p.funcionarios f WHERE f.id = :idFuncionario")
    List<Projeto> buscarProjetosPorFuncionario(@Param("idFuncionario") Integer idFuncionario);

}
