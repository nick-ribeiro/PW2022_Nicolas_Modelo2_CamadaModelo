package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.UnidadeCondominal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestePersistirUnidadeCondominal {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Modelo2-ModelPU");
        EntityManager em = emf.createEntityManager();
        UnidadeCondominal uc = new UnidadeCondominal();
        Pessoa p = em.find(Pessoa.class, 1);
        Condominio c = em.find(Condominio.class, 7);
        uc.setNumero("1020");
        uc.setArea(250.0);
        uc.setDescricao("Nao ha necessidade de pagar taxa condominal já que mora na cobertura");
        uc.setNumeroQuarto(1502);
        c.adicionarUnidadeCondiminal(uc);
        uc.setPessoa(p);
        UnidadeCondominal uc2 = new UnidadeCondominal();
        Pessoa p2 = em.find(Pessoa.class, 2);
        Condominio c2 = em.find(Condominio.class, 8);
        uc2.setNumero("87");
        uc2.setArea(175.0);
        uc2.setDescricao("Necessidade de pagar taxa condominal");
        uc2.setNumeroQuarto(1204);
        c2.adicionarUnidadeCondiminal(uc2);
        uc2.setPessoa(p2);
        em.getTransaction().begin();
        em.persist(uc);
        em.persist(uc2);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}