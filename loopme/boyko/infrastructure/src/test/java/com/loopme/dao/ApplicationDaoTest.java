package com.loopme.dao;

import com.loopme.dao.impl.ApplicationDaoImpl;
import com.loopme.mapper.IMapper;
import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@Transactional
public class ApplicationDaoTest {

    @Autowired
    private ApplicationDaoImpl applicationDao;

    @PersistenceContext()
    private EntityManager entityManager;

    @Autowired
    private IMapper<com.loopme.dbmodel.Application, Application> mapper;

    @Test
    public void testSaveOrUpdateSavePart() {
        //given
        Application application = getApplicationBO();
        Query query = createQueryForGetApplicationByBusinessKey(application.getBusinessKey());
        assertThat(query.getResultList()).isEmpty();

        //when
        applicationDao.saveOrUpdate(application);

        //then
        com.loopme.dbmodel.Application savedApplication = (com.loopme.dbmodel.Application) createQueryForGetApplicationByBusinessKey(application.getBusinessKey()).getSingleResult();
        assertThat(savedApplication.getId()).isNotNull();
        assertApplication(mapper.toBO(savedApplication), application);
    }

    @Test
    public void testSaveOrUpdateUpdatePart() {
        //given
        ApplicationType updatedApplicationType = ApplicationType.ANDROID;
        Application application = getApplicationBO();
        com.loopme.dbmodel.Application applicationDaoEntity = mapper.toDao(application);
        entityManager.persist(applicationDaoEntity.getUser());
        entityManager.persist(applicationDaoEntity);
        application.setApplicationType(updatedApplicationType);
        application.setContentTypes(null);

        //when
        applicationDao.saveOrUpdate(application);

        //then
        List allApplications = entityManager.createQuery("select app from Application app").getResultList();
        assertThat(allApplications).hasSize(1);
        com.loopme.dbmodel.Application savedApplication = (com.loopme.dbmodel.Application) createQueryForGetApplicationByBusinessKey(application.getBusinessKey()).getSingleResult();
        assertThat(savedApplication.getId()).isNotNull();
        assertApplication(mapper.toBO(savedApplication), application);
    }

    @Test
    public void testSaveOrUpdateUpdatePartNewUser() {
        //given
        Application application = getApplicationBO();
        com.loopme.dbmodel.Application applicationDaoEntity = mapper.toDao(application);
        entityManager.persist(applicationDaoEntity.getUser());
        entityManager.persist(applicationDaoEntity);
        application.setUser(new User("newName", "newEmail", UserRole.PUBLISHER));

        //when
        applicationDao.saveOrUpdate(application);

        //then
        com.loopme.dbmodel.Application savedApplication = (com.loopme.dbmodel.Application) createQueryForGetApplicationByBusinessKey(application.getBusinessKey()).getSingleResult();
        assertThat(savedApplication.getId()).isNotNull();
        assertApplication(mapper.toBO(savedApplication), application);
    }

    @Test
    public void testSaveOrUpdateUpdatePartUpdateUser() {
        //given
        Application application = getApplicationBO();
        com.loopme.dbmodel.Application applicationDaoEntity = mapper.toDao(application);
        entityManager.persist(applicationDaoEntity.getUser());
        entityManager.persist(applicationDaoEntity);
        application.getUser().setEmail("VeryNewEmail");

        //when
        applicationDao.saveOrUpdate(application);

        //then
        com.loopme.dbmodel.Application savedApplication = (com.loopme.dbmodel.Application) createQueryForGetApplicationByBusinessKey(application.getBusinessKey()).getSingleResult();
        assertThat(savedApplication.getId()).isNotNull();
        assertApplication(mapper.toBO(savedApplication), application);
    }

    @Test
    public void testGetById() {
        //given
        Application application = getApplicationBO();
        com.loopme.dbmodel.Application applicationDaoEntity = mapper.toDao(application);
        entityManager.persist(applicationDaoEntity);

        //when
        Application savedApplication = applicationDao.getById(applicationDaoEntity.getId());

        //then
        assertApplication(savedApplication, application);
    }

    @Test
    public void testDelete() {
        //given
        Application application = getApplicationBO();
        com.loopme.dbmodel.Application applicationDaoEntity = mapper.toDao(application);
        entityManager.persist(applicationDaoEntity);
        com.loopme.dbmodel.Application existingApplication = entityManager.find(com.loopme.dbmodel.Application.class, applicationDaoEntity.getId());
        assertThat(existingApplication).isNotNull();

        //when
        applicationDao.delete(application.getBusinessKey());

        //then
        com.loopme.dbmodel.Application deletedApplication = entityManager.find(com.loopme.dbmodel.Application.class, applicationDaoEntity.getId());
        assertThat(deletedApplication).isNull();
    }

    @Test
    public void getByBusinessKey() {
        //given
        Application application = getApplicationBO();
        com.loopme.dbmodel.Application applicationDaoEntity = mapper.toDao(application);
        entityManager.persist(applicationDaoEntity);

        //when
        Application applicationByKey = applicationDao.getByBusinessKey(application.getBusinessKey());

        //then
        assertApplication(applicationByKey, application);
    }

    @Test
    public void testGetAll() {
        //given
        String firstName = "ApplicationOne";
        String secondName = "ApplicationTwo";
        Application applicationOne = getApplicationBO();
        applicationOne.setName(firstName);
        Application applicationTwo = getApplicationBO();
        applicationTwo.setName(secondName);
        applicationDao.saveOrUpdate(applicationOne);
        applicationDao.saveOrUpdate(applicationTwo);

        //when
        List<Application> allApplications = applicationDao.getAll();

        //then
        assertThat(allApplications).hasSize(2);
        assertThat(allApplications.stream().map(Application::getBusinessKey)).containsExactlyInAnyOrder(firstName, secondName);
    }

    private void assertApplication(Application checkedApplication, Application expectedApplication) {
        assertThat(checkedApplication.getName()).isEqualTo(expectedApplication.getName());
        assertThat(checkedApplication.getBusinessKey()).isEqualTo(expectedApplication.getBusinessKey());
        assertThat(checkedApplication.getApplicationType()).isEqualTo(expectedApplication.getApplicationType());
        assertThat(checkedApplication.getContentTypes()).isEqualTo(expectedApplication.getContentTypes());
        assertUser(checkedApplication.getUser(), expectedApplication.getUser());
    }

    private void assertUser(User checkedUser, User expectedUser) {
        assertThat(checkedUser.getName()).isEqualTo(expectedUser.getName());
        assertThat(checkedUser.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(checkedUser.getRole()).isEqualTo(expectedUser.getRole());
        assertThat(checkedUser.getBusinessKey()).isEqualTo(expectedUser.getBusinessKey());
    }

    private Query createQueryForGetApplicationByBusinessKey(String applicationBusinessKey) {
        Query query = entityManager.createQuery("select app from Application app where app.name = :name");
        query.setParameter("name", applicationBusinessKey);
        return query;
    }

    private com.loopme.model.Application getApplicationBO() {
        Application application = new Application();
        String applicationName = "testApplicationName";
        List<com.loopme.model.ContentType> contentTypes = Arrays.asList(com.loopme.model.ContentType.IMAGE, com.loopme.model.ContentType.VIDEO);
        com.loopme.model.ApplicationType applicationType = com.loopme.model.ApplicationType.WEBSITE;
        application.setApplicationType(applicationType);
        application.setContentTypes(contentTypes);
        application.setName(applicationName);
        application.setUser(getUserBO());
        return application;
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
