package application.util;

import application.domain.entities.Channel;
import application.repositories.ChannelRepository;
import application.repositories.ChannelRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        EntityManager em = Persistence.createEntityManagerFactory("exam")
                .createEntityManager();

//        List<Channel> suggested = repository.getNotBelongingToUser("ivan");
        List<Channel> channels =
                em.createQuery("SELECT c FROM Channel AS c ,User AS u " +
                        "LEFT JOIN c.followers AS f ON f.username= :username " +
                        "WHERE f.id IS NULL " +
                        " GROUP BY c.id" , Channel.class)
                        .setParameter("username", "BabaMarta")
                        .getResultList();
        System.out.println();

    }
}
