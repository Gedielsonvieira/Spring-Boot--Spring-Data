package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    //Derived query
    List<Funcionario> findByNome(String nome);

    Page<Funcionario> findAll(Pageable pageable);

    //JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
    List<Funcionario> findNomeSalarioMaiordataContratacao (String nome, BigDecimal salario, LocalDate dataContratacao);

    //Native Query
    //@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao", nativeQuery = true)
    @Query("SELECT f FROM Funcionario f WHERE f.dataContratacao >= :dataContratacao")
    List<Funcionario> findFuncionarioDataContratacaoMaior (LocalDate dataContratacao);
}