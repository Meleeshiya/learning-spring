package com.example.firstmvn.services;

import com.example.firstmvn.daos.UserDao;
import com.example.firstmvn.entities.User;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getOne(Long id) throws EntityNotFoundException {
        return userDao.getOne(id);
    }

    public void addOne(User user) throws EntityExistsException {
        userDao.addOne(user);
    }

    public void updateOne(User user) throws RuntimeException {
        userDao.updateOne(user);
    }

    public void deleteOne(Long id) throws EntityNotFoundException {
        userDao.deleteOne(id);
    }
}
