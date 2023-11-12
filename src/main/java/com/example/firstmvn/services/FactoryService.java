package com.example.firstmvn.services;

import com.example.firstmvn.daos.FactoryDao;
import com.example.firstmvn.entities.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class FactoryService {

    @Autowired
    private final FactoryDao factoryDao;

    public FactoryService(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }

    public List<Factory> getAll() {
        return factoryDao.getAll();
    }

    public Factory getOne(Long id) throws EntityNotFoundException {
        return factoryDao.getOne(id);
    }

    public void addOne(Factory factory) throws EntityExistsException {
        factoryDao.addOne(factory);
    }

    public void updateOne(Factory factory) throws RuntimeException {
        factoryDao.updateOne(factory);
    }

    public void deleteOne(Long id) throws EntityNotFoundException {
        factoryDao.deleteOne(id);
    }

//    public void updateOneIfProductChanged(Factory factory) throws RuntimeException {
//        factoryDao.updateOneIfProductChanged(factory);
//    }

}
