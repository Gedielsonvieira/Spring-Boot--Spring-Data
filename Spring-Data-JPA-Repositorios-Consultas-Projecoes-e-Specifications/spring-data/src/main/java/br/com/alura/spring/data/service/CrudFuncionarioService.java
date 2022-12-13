package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        boolean system = true;

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
                    visualizar(scanner);
                    break;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {
        System.out.println("Nome: ");
        String nome = scanner.next();

        System.out.println("CPF: ");
        String cpf = scanner.next();

        System.out.println("Salário: ");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.print("Digite o número do cargo do funcionario: ");
        int idCargoDoFuncionario = scanner.nextInt();
        Optional<Cargo> existeIdCargo = cargoRepository.findById(idCargoDoFuncionario);

        List<UnidadeDeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setCargo(existeIdCargo.get());
        funcionario.setUnidadesDeTrabalho(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Insira o id do funcionario a ser atualizado: ");
        int id = scanner.nextInt();

        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("CPF: ");
        String cpf = scanner.next();

        System.out.print("Salário: ");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.print("Digite o número do cargo do funcionario: ");
        int idCargoDoFuncionario = scanner.nextInt();
        Optional<Cargo> existeIdCargo = cargoRepository.findById(idCargoDoFuncionario);

        List<UnidadeDeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setCargo(existeIdCargo.get());
        funcionario.setUnidadesDeTrabalho(unidades);

        funcionarioRepository.save(funcionario);

        System.out.println("funcionario atualizado");
    }

    private void deletar(Scanner scanner) {
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

    private void visualizar(Scanner scanner) {
        System.out.println("Qual página você deseja visualizar: ");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));

        Iterable<Funcionario> listaFuncionarios = funcionarioRepository.findAll(pageable);
        System.out.println("Lista de funcionarios: ");

        listaFuncionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private List<UnidadeDeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
}
