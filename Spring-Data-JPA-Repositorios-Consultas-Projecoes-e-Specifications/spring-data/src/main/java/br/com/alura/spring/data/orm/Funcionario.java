package br.com.alura.spring.data.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    private LocalDate dataContratacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)//Não entendi o que foi isso
    private Cargo cargo;

    @ManyToMany
    /*@JoinTable(name = "funcionarios_unidades", joinColumns = {
            @JoinColumn(name = "fk_funcionario")},
            inverseJoinColumns = {@JoinColumn(name = "fk_unidade")}) - Join não explicado e não entendido*/
    private List<UnidadeDeTrabalho> unidadesDeTrabalho;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<UnidadeDeTrabalho> getUnidadesDeTrabalho() {
        return unidadesDeTrabalho;
    }

    public void setUnidadesDeTrabalho(List<UnidadeDeTrabalho> unidadesDeTrabalho) {
        this.unidadesDeTrabalho = unidadesDeTrabalho;
    }

    @Override
    public String toString() {
        return "Funcionario [" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                ", cargo=" + cargo.getDescricao() +
                ']';
    }
}
