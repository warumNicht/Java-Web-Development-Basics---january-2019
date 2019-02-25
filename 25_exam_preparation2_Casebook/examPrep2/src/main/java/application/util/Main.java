package application.util;

import application.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em =
                Persistence.createEntityManagerFactory("exam")
                .createEntityManager();

        String query="SElect * from users as u \n" +
                "where u.id not in\n" +
                "(SELECt u2.friend_id from users_friends as u2 \n" +
                "where u2.user_id='{id}')" +
                "AND u.id not in " +
                " (SELECt u3.user_id from users_friends as u3 \n" +
                "where u3.friend_id='{id}')" +
                "AND u.id!='{id}'";
        query=query.replace("{id}", "5107af49-6714-44be-92c8-c8b333f7826d");


        List<User> users = em.createNativeQuery(query, User.class)
                .getResultList();


        List<User> resultList = em.createQuery("SELECT f FROM User AS u2 " +
                        "JOIN u2.friends AS f " +
                        "WHERE u2.username = :username"
                , User.class)
                .setParameter("username", "Nicolás Maduro")
                .getResultList();

        List<User> users2 = em.createQuery("SELECT u FROM User AS u " +
                "WHERE u not in(SELECT f FROM User AS u2 " +
                "JOIN u2.friends AS f " +
                "WHERE u2.username = :username) " +
                "AND u.username not like :username" , User.class)
                .setParameter("username", "Nicolás Maduro")
                .getResultList();



        System.out.println();

    }
}
