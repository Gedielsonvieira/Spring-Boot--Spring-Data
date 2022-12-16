package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.dto.FuncionarioDto;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecaoInterface;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>,
        ListPagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
    //Derived query
    List<Funcionario> findByNome(String nome);

    //JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = " +
            ":dataContratacao")
    List<Funcionario> findNomeSalarioMaiordataContratacao(String nome, BigDecimal salario, LocalDate dataContratacao);

    //Native Query
    @Query(value = "SELECT * FROM alura.funcionarios f WHERE f.data_contratacao >= :dataContratacao",
            nativeQuery = true)
    List<Funcionario> findFuncionarioDataContratacaoMaior(LocalDate dataContratacao);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM alura.funcionarios f", nativeQuery = true)
    List<FuncionarioProjecaoInterface> findFuncionarioSalario();

    //Segundo a documentação do Spring Data Jpa não é possível usar Projection de DTO com query nativa
    //Alternativa - Usar JPQL e passar o construtor do DTO (temos q passar o caminho completo do pacote até a classe)
    @Query(value = "SELECT new br.com.alura.spring.data.dto.FuncionarioDto(f.id, f.nome, f.salario) FROM Funcionario f")
    List<FuncionarioDto> findFuncionarioSalarioComProjecaoClasse();

}
