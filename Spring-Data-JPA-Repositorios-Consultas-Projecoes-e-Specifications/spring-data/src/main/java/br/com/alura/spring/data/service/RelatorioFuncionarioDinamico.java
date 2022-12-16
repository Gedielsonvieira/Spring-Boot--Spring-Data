package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

//como eu quero apresentar esse valor e dar a possibilidade para o usuário acessar essa classe, acessar os recursos
//que nós estamos criando aqui, eu vou anotar com a notação de @service para que possamos depois fazer a injeção de
//dependência dentro dessa classe.
@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite um nome: ");
        String nome = scanner.next();

        if (nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }


        System.out.println("Digite um CPF: ");
        String cpf = scanner.next();

        if (nome.equalsIgnoreCase("NULL")) {
            cpf = null;
        }


        System.out.println("Digite um salário: ");
        BigDecimal salario = scanner.nextBigDecimal();

        if (salario == new BigDecimal("0")) {
            salario = null;
        }


        List<Funcionario> listaFuncionarios = funcionarioRepository.findAll(Specification
                .where(
                        SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.salario(salario))
        );
        listaFuncionarios.forEach(funcionario -> System.out.println(funcionario));
    }
}
