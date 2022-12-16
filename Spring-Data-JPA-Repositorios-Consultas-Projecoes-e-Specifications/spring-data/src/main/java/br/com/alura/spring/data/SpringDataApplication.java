package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
    //A interface CommandLineRunner nos obriga a implementar um método chamado Run e ele é executado logo após a finalização
//do método Main. Então depois de iniciar a nossa aplicação e iniciar o SpringFramework, nós vamos cair no método Run
//e vai ser executado tudo o que ele contém
    private final CrudCargoService crudCargoService;

    private final CrudFuncionarioService crudFuncionarioService;

    private final CrudUnidadeDeTrabalhoService crudUnidadeDeTrabalhoService;

    private final RelatoriosService relatoriosService;

    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

    private boolean system = true;

    public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService,
                                 CrudUnidadeDeTrabalhoService crudUnidadeDeTrabalhoService,
                                 RelatoriosService relatoriosService,
                                 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
        this.crudCargoService = crudCargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeDeTrabalhoService = crudUnidadeDeTrabalhoService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }

    public static void main(String[] args) {
        //Inicia o framework do spring junto com a nossa aplicação
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner leDados = new Scanner(System.in);

        while (system) {
            System.out.println("---------- Menu ----------");
            System.out.println("Escolha uma opção: ");
            System.out.println("0 - Sair");
            System.out.println("1 - Operações de Cargo");
            System.out.println("2 - Operações de Funcionário");
            System.out.println("3 - Operações de Unidade de trabalho");
            System.out.println("4 - Relatórios");
            System.out.println("5 - Relatórios Dinâmico");

            int action = leDados.nextInt();

            if (action == 0) {
                system = false;
            } else if (action == 1) {
                crudCargoService.inicial(leDados);
            } else if (action == 2) {
                crudFuncionarioService.inicial(leDados);
            } else if (action == 3) {
                crudUnidadeDeTrabalhoService.inicial(leDados);
            } else if (action == 4) {
                relatoriosService.inicial(leDados);
            } else if (action == 5) {
                relatorioFuncionarioDinamico.inicial(leDados);
            } else {
                System.out.println("Opção inválida");
            }
        }
    }
}
