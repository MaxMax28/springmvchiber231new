package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.UserDaoJPARepositoryExtends;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //private UserDaoJPARepositoryExtends userDaoJPARepositoryExtends;
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

//    @Autowired
//    public UserServiceImpl(UserDaoJPARepositoryExtends userDaoJPARepositoryExtends) {
//        this.userDaoJPARepositoryExtends = userDaoJPARepositoryExtends;
//    }

    @Override
    public void addUser(User user) {
        //userDaoJPARepositoryExtends.save(user);
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserById(long id) {
        //return userDaoJPARepositoryExtends.getOne(id);
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        //return userDaoJPARepositoryExtends.findAll();
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUser(long id) {
        //userDaoJPARepositoryExtends.delete(id);
        userDao.deleteUser(id);
    }
}
