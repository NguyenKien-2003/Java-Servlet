package vn.edu.t3h.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.t3h.dao.UserRepository;
import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository("userHibernateRepositoryImpl")
public class UserHibernateRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        Session session = sessionFactory.openSession();
        String hql = "from UserEntity";
        Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
        List<UserEntity> users = query.getResultList();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findUsers(String keyword) {
        Session session = sessionFactory.openSession();
        String hql = "FROM UserEntity u WHERE u.username LIKE :keyword OR u.identityCard.fullName LIKE :keyword";
        Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
        query.setParameter("keyword", "%" + keyword + "%");
        List<UserEntity> users = query.getResultList();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }


}
