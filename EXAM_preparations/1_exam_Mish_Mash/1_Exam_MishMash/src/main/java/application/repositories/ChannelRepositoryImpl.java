package application.repositories;

import application.domain.entities.Channel;
import application.services.UserService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChannelRepositoryImpl implements ChannelRepository {
    private final EntityManager entityManager;
    private final UserService userService;

    @Inject
    public ChannelRepositoryImpl(EntityManager entityManager, UserService userService) {
        this.entityManager = entityManager;
        this.userService = userService;
    }

    @Override
    public Channel save(Channel entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<Channel> findById(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("exam")
                .createEntityManager();
        try{
            Channel channel = em.createQuery("SELECT c FROM Channel c " +
                    "WHERE c.id= :id", Channel.class)
                    .setParameter("id", id)
                    .getSingleResult();
            em.refresh(channel);
            return Optional.of(channel);
        }catch (Exception e){
            return Optional.empty();
        }finally {
            em.clear();
        }
    }

    @Override
    public List<Channel> findAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<Channel> getNotBelongingToUser(String username) {
        EntityManager em = Persistence.createEntityManagerFactory("exam")
                .createEntityManager();

        List<Channel> channels =
                em.createQuery("SELECT c FROM Channel AS c " +
                "LEFT JOIN c.followers AS f ON f.username= :username " +
                        "WHERE f.id IS NULL " +
                        "GROUP BY c.id" , Channel.class)
                .setParameter("username", username)
                .getResultList();



//        List<Channel> channels  = this.entityManager.createNativeQuery("SELECT * FROM channels AS c\n" +
//                "LEFT JOIN users_channels AS uc ON uc.channel_id=c.id\n" +
//                "LEFT JOIN users AS u ON u.id=uc.user_id AND u.username= :username " +
//                "WHERE u.id IS NULL " +
//                "GROUP BY c.id", Channel.class)
//                .setParameter("username",username)
//                .getResultList();

        channels.stream()
                .forEach(c->em.refresh(c));


        em.clear();


        return channels;
    }
}
