package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
//A interface CommandLineRunner nos obriga a implementar um método chamado Run e ele é executado logo após a finalização
//do método Main. Então depois de iniciar a nossa aplicação e iniciar o SpringFramework, nós vamos cair no método Run
//e vai ser executado tudo o que ele contém
    private final CargoRepository cargoRepository;

    public SpringDataApplication(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public static void main(String[] args) {
        //Inicia o framework do spring junto com a nossa aplicação
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor De Software");

		cargoRepository.save(cargo);
    }
}
