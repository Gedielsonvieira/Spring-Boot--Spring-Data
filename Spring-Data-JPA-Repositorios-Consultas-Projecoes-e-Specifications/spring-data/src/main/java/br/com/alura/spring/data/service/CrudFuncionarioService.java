package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("---------- Sub-Menu ----------");
            System.out.println("0 - Sair");
            System.out.println("1 - Inserir funcionario");
            System.out.println("2 - Atualizar funcionario");
            System.out.println("3 - Deletar funcionario");
            System.out.println("4 - Visualizar funcionarios");

            int action = scanner.nextInt();

            switch (action) {
                case 0:
                    system = false;
                    break;
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    deletar(scanner);
                    break;
                case 4:
                    visualizar();
                    break;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
        }
    }

    public void salvar(Scanner scanner) {
        System.out.println("Nome: ");
        String nome = scanner.next();

        System.out.println("CPF: ");
        String cpf = scanner.next();

        System.out.println("Salário: ");
        BigDecimal salario = scanner.nextBigDecimal();

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    public void atualizar(Scanner scanner) {
        System.out.println("Insira o id do funcionario a ser atualizado: ");
        int id = scanner.nextInt();

        Optional<Funcionario> idEscolhido = funcionarioRepository.findById(id);

        if (idEscolhido.isPresent()) {
            System.out.println("Nome: ");
            String nome = scanner.next();

            System.out.println("CPF: ");
            String cpf = scanner.next();

            System.out.println("Salário: ");
            BigDecimal salario = scanner.nextBigDecimal();

            Funcionario funcionario = new Funcionario();
            funcionario.setId(id);
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setSalario(salario);

            funcionarioRepository.save(funcionario);

            System.out.println("funcionario atualizado");
        } else {
            System.out.println("ID inválido");
        }
    }

    public void deletar(Scanner scanner) {
        System.out.println("Insira o id do funcionario a ser deletado: ");
        int id = scanner.nextInt();

        Optional<Funcionario> idEscolhido = funcionarioRepository.findById(id);

        if (idEscolhido.isPresent()) {
            funcionarioRepository.deleteById(id);
            System.out.println("Deleção realizada");
        } else {
            System.out.println("Id inexistente");
        }
    }

    public void visualizar() {
        Iterable<Funcionario> listaFuncionarios = funcionarioRepository.findAll();
        System.out.println("Lista de funcionarios: ");

        listaFuncionarios.forEach(funcionario -> System.out.println(funcionario));
    }


}
