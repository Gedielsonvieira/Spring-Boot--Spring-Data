package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Classe que isola a criação do EntityManager
//JPAUtil é uma classe utilitária
public class JPAUtil {
    //Existe uma factory de uma EntityManager, então para criar uma EntityManager precisamos de um EntityManagerFactory
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
