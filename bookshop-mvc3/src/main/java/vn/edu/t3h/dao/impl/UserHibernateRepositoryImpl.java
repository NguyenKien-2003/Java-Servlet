package vn.edu.t3h.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vn.edu.t3h.dao.UserRepository;
import vn.edu.t3h.entity.IdentityCard;
import vn.edu.t3h.entity.RoleEntity;
import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.UserDTO;

import java.util.*;
import java.util.stream.Collectors;

@Repository("userHibernateRepositoryImpl")
public class UserHibernateRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> result;
        try (Session session = sessionFactory.openSession()) {
            String hql = "from UserEntity";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            List<UserEntity> users = query.getResultList();
            result = users.stream().map(UserDTO::new).collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public UserDTO getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from UserEntity u where u.id = :id";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            query.setParameter("id", id);
            Optional<UserEntity> userEntity = query.getResultList().stream().findFirst();
            return userEntity.map(UserDTO::new).orElse(null);
        }
    }

    @Override
    public List<UserDTO> findUsers(String keyword) {
        List<UserEntity> users;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM UserEntity u WHERE u.username LIKE :keyword OR u.identityCard.fullName LIKE :keyword";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            query.setParameter("keyword", "%" + keyword + "%");
            users = query.getResultList();
            return users.stream().map(UserDTO::new).collect(Collectors.toList());
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            UserEntity user = session.get(UserEntity.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void addUser(UserDTO userDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        IdentityCard identityCard = new IdentityCard();
        identityCard.setAddress(userDTO.getAddress());
        identityCard.setDateOfBirth(userDTO.getDateOfBirth());
        identityCard.setFullName(userDTO.getFullname());
        identityCard.setIdentityNumber(userDTO.getIdentityNumber());
        user.setIdentityCard(identityCard);

        Set<RoleEntity> roles = new HashSet<>();
        if (userDTO.getRoles() != null) {
            for (String roleName : userDTO.getRoles()) {
                RoleEntity role = getRoleByName(session, roleName);
                if (role != null) {
                    roles.add(role);
                } else {
                    System.out.println("Role không tồn tại: " + roleName);
                }
            }
        }
        user.setRoles(roles);
        session.save(user);
        transaction.commit();
        session.close();
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, userDTO.getId());
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());

            IdentityCard identityCard = user.getIdentityCard();
            if (identityCard == null) {
                identityCard = new IdentityCard();
            }
            identityCard.setAddress(userDTO.getAddress());
            identityCard.setDateOfBirth(userDTO.getDateOfBirth());
            identityCard.setFullName(userDTO.getFullname());
            identityCard.setIdentityNumber(userDTO.getIdentityNumber());
            user.setIdentityCard(identityCard);

            Set<RoleEntity> roles = new HashSet<>();
            if (userDTO.getRoles() != null) {
                for (String roleName : userDTO.getRoles()) {
                    RoleEntity role = getRoleByName(session, roleName);
                    if (role != null) {
                        roles.add(role);
                    } else {
                        System.out.println("Role không tồn tại: " + roleName);
                    }
                }
            }
            user.setRoles(roles);
            session.update(user);
            transaction.commit();
            session.close();
        }
    }


    @Override
    public RoleEntity getRoleByName(Session session, String roleName) {
        Query<RoleEntity> query = session.createQuery("FROM RoleEntity r WHERE r.roleName = :roleName", RoleEntity.class);
        query.setParameter("roleName", roleName);
        System.out.println(roleName);
        return query.uniqueResult();
    }
}
