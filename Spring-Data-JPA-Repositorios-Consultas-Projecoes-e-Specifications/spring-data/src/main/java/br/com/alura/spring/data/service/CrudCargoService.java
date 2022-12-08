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
        salvar(scanner);
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
        Iterable<Cargo> listaCargos = cargoRepository.findAll();
        System.out.println("Lista de cargos: ");

        for (Cargo cargo : listaCargos) {
            System.out.print(cargo.getId());
            System.out.println(" " + cargo.getDescricao());
        }

        System.out.println("Insira o número de sua escolha: ");
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
        Iterable<Cargo> listaCargos = cargoRepository.findAll();
        System.out.println("Lista de cargos: ");

        for (Cargo cargo : listaCargos) {
            System.out.print(cargo.getId());
            System.out.println(" " + cargo.getDescricao());
        }

        System.out.println("Insira o número de sua escolha: ");
        int id = scanner.nextInt();

        cargoRepository.deleteById(id);

        System.out.println("Deleção realizada");
    }

}
