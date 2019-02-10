package com.loopme.dao;

import com.loopme.dao.impl.UserDaoImpl;
import com.loopme.mapper.IMapper;
import com.loopme.model.User;
import com.loopme.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@Transactional
public class UserDaoTest {

    @Autowired
    private UserDaoImpl userDao;

    @PersistenceContext()
    private EntityManager entityManager;

    @Autowired
    private IMapper<com.loopme.dbmodel.User, User> mapper;

    @Test
    public void testSaveOrUpdateSavePart() {
        //given
        User user = getUserBO();
        Query query = createQueryForGetUserByBusinessKey(user.getBusinessKey());
        assertThat(query.getResultList()).isEmpty();

        //when
        userDao.saveOrUpdate(user);

        //then
        com.loopme.dbmodel.User savedUser = (com.loopme.dbmodel.User) createQueryForGetUserByBusinessKey(user.getBusinessKey()).getSingleResult();
        assertThat(savedUser.getId()).isNotNull();
        assertUser(mapper.toBO(savedUser), user);
    }

    @Test
    public void testSaveOrUpdateUpdatePart() {
        //given
        User user = getUserBO();
        entityManager.persist(mapper.toDao(user));

        user.setEmail("veryNewEmail");
        user.setRole(UserRole.PUBLISHER);

        //when
        userDao.saveOrUpdate(user);

        //then
        List allUsers = entityManager.createQuery("select u from User u").getResultList();
        assertThat(allUsers).hasSize(1);
        com.loopme.dbmodel.User savedUser = (com.loopme.dbmodel.User) createQueryForGetUserByBusinessKey(user.getBusinessKey()).getSingleResult();
        assertThat(savedUser.getId()).isNotNull();
        assertUser(mapper.toBO(savedUser), user);
    }

    @Test
    public void testGetById() {
        //given
        User user = getUserBO();
        com.loopme.dbmodel.User userDaoEntity = mapper.toDao(user);
        entityManager.persist(userDaoEntity);

        //when
        User savedUser = userDao.getById(userDaoEntity.getId());

        //then
        assertUser(savedUser, user);
    }

    @Test
    public void testDelete() {
        //given
        User user = getUserBO();
        com.loopme.dbmodel.User userDaoEntity = mapper.toDao(user);
        entityManager.persist(userDaoEntity);
        com.loopme.dbmodel.User existingUser = entityManager.find(com.loopme.dbmodel.User.class, userDaoEntity.getId());
        assertThat(existingUser).isNotNull();

        //when
        userDao.delete(user.getBusinessKey());

        //then
        com.loopme.dbmodel.User deletedUser = entityManager.find(com.loopme.dbmodel.User.class, userDaoEntity.getId());
        assertThat(deletedUser).isNull();
    }

    @Test
    public void getByBusinessKey() {
        //given
        User user = getUserBO();
        com.loopme.dbmodel.User userDaoEntity = mapper.toDao(user);
        entityManager.persist(userDaoEntity);

        //when
        User userByKey = userDao.getByBusinessKey(user.getBusinessKey());

        //then
        assertUser(userByKey, user);
    }

    @Test
    public void testGetAll() {
        //given
        String firstName = "UserOne";
        String secondName = "UserTwo";
        User userOne = getUserBO();
        userOne.setName(firstName);
        User userTwo = getUserBO();
        userTwo.setName(secondName);
        userDao.saveOrUpdate(userOne);
        userDao.saveOrUpdate(userTwo);

        //when
        List<User> allApplications = userDao.getAll();

        //then
        assertThat(allApplications).hasSize(2);
        assertThat(allApplications.stream().map(User::getBusinessKey)).containsExactlyInAnyOrder(firstName, secondName);
    }

    private void assertUser(User checkedUser, User expectedUser) {
        assertThat(checkedUser.getName()).isEqualTo(expectedUser.getName());
        assertThat(checkedUser.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(checkedUser.getRole()).isEqualTo(expectedUser.getRole());
        assertThat(checkedUser.getBusinessKey()).isEqualTo(expectedUser.getBusinessKey());
    }

    private Query createQueryForGetUserByBusinessKey(String userBusinessKey) {
        Query query = entityManager.createQuery("select u from User u where u.name = :name");
        query.setParameter("name", userBusinessKey);
        return query;
    }

    private User getUserBO() {
        User user = new User();
        String userName = "testUserName";
        String email = "test@email.com";
        UserRole userRole = UserRole.ADMIN;
        user.setName(userName);
        user.setEmail(email);
        user.setRole(userRole);
        return user;
    }
}
