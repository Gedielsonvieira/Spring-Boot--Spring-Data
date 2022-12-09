package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeDeTrabalhoService {

    private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
    private boolean system = true;

    public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("---------- Sub-Menu ----------");
            System.out.println("0 - Sair");
            System.out.println("1 - Inserir Unidade de trabalho");
            System.out.println("2 - Atualizar Unidade de trabalho");
            System.out.println("3 - Deletar Unidade de trabalho");
            System.out.println("4 - Visualizar Unidades de trabalho");

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
        System.out.println("Digite o nome da unidade");
        String descricao = scanner.next();

        System.out.println("Endereço: ");
        String endereco = scanner.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setDescricao(descricao);
        unidadeDeTrabalho.setEndereco(endereco);

        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Salvo");
    }

    public void atualizar(Scanner scanner) {
        System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome da unidade");
        String nome = scanner.next();

        System.out.println("Digite o endereco");
        String endereco = scanner.next();

        UnidadeDeTrabalho unidadeTrabalho = new UnidadeDeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        unidadeDeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Alterado");
    }

    public void deletar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        unidadeDeTrabalhoRepository.deleteById(id);
        System.out.println("Deletado");
    }

    public void visualizar() {
        Iterable<UnidadeDeTrabalho> unidades = unidadeDeTrabalhoRepository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade));
    }


}
