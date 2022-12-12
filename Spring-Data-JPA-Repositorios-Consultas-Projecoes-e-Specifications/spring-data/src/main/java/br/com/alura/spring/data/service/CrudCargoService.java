package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        boolean system = true;

        while (system) {
            System.out.println("---------- Sub-Menu ----------");
            System.out.println("0 - Sair");
            System.out.println("1 - Inserir Cargo");
            System.out.println("2 - Atualizar Cargo");
            System.out.println("3 - Deletar Cargo");
            System.out.println("4 - Visualizar Cargos");

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
        System.out.println("Descrição: ");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    public void atualizar(Scanner scanner) {
        System.out.println("Insira o id do cargo a ser atualizado: ");
        int id = scanner.nextInt();

        Optional<Cargo> idEscolhido = cargoRepository.findById(id);

        if (idEscolhido.isPresent()) {
            System.out.println("Descrição do cargo: ");
            String descricao = scanner.next();
            Cargo cargo = new Cargo();
            cargo.setId(id);
            cargo.setDescricao(descricao);
            cargoRepository.save(cargo);
            System.out.println("Cargo atualizado");
        } else {
            System.out.println("ID inválido");
        }
    }

    public void deletar(Scanner scanner) {
        System.out.println("Insira o id do cargo a ser deletado: ");
        int id = scanner.nextInt();

        Optional<Cargo> idEscolhido = cargoRepository.findById(id);

        if (idEscolhido.isPresent()) {
            cargoRepository.deleteById(id);
            System.out.println("Deleção realizada");
        } else {
            System.out.println("Id inexistente");
        }
    }

    public void visualizar() {
        Iterable<Cargo> listaCargos = cargoRepository.findAll();
        System.out.println("Lista de cargos: ");

        listaCargos.forEach(cargo -> System.out.println(cargo));
    }


}
