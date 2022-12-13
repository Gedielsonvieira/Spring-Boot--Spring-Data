package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        boolean system = true;

        while (system) {
            System.out.println("---------- Sub-Menu ----------");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionário por nome");
            System.out.println("2 - Filtrar por nome, salario e data de contratação");
            System.out.println("3 - Filtrar por data de contratação");

            int action = scanner.nextInt();

            switch (action) {
                case 0:
                    system = false;
                    break;
                case 1:
                    buscarPorNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioPorNomeSalarioMaiorDataContratacao(scanner);
                    break;
                case 3:
                    buscaFuncionarioPorDataContratacao(scanner);
                    break;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
        }
    }

    private void buscaFuncionarioPorDataContratacao(Scanner scanner) {
        System.out.println("Digite a data de contratação: ");
        String data = scanner.next();
        LocalDate dataFormatada = LocalDate.parse(data, formatter);//Convertendo String para LocalDate no formato desejado

        List<Funcionario> listaFuncionarioDataContratacao = funcionarioRepository.findFuncionarioDataContratacaoMaior(dataFormatada);

        if (listaFuncionarioDataContratacao.isEmpty()) {
            System.out.println("Funcionario não encontrado na base de dados!");
        } else {
            listaFuncionarioDataContratacao.forEach(funcionario -> System.out.println(funcionario));
        }

    }

    private void buscaFuncionarioPorNomeSalarioMaiorDataContratacao(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        System.out.println("Digite a data de contratação: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);//Convertendo String para LocalDate no formato desejado

        System.out.println("Digite o salário: ");
        BigDecimal salario = scanner.nextBigDecimal();

        List<Funcionario> listaNomeSalarioMaiordataContratacao = funcionarioRepository.findNomeSalarioMaiordataContratacao(nome, salario, localDate);

        if (listaNomeSalarioMaiordataContratacao.isEmpty()) {
            System.out.println("Funcionario não encontrado na base de dados!");
        } else {
            listaNomeSalarioMaiordataContratacao.forEach(funcionario -> System.out.println(funcionario));
        }
    }

    private void buscarPorNome(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        List<Funcionario> listaDeNomes = funcionarioRepository.findByNome(nome);

        if (listaDeNomes.isEmpty()) {
            System.out.println("Nome não encontrado na base de dados!");
        } else {
            listaDeNomes.forEach(name -> System.out.println(name));
        }
    }
}
