package com.example.firstmvn.daos;

import com.example.firstmvn.entities.Factory;
import com.example.firstmvn.repositories.FactoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class FactoryDao {

    private static final String ID_NOT_FOUND_MSG_1 = "Factory with id \"";
    private static final String ID_NOT_FOUND_MSG_2 = "\" not found.";
    private static final String ADD_ERR_MSG = "Factory with that id and/or email already persists";
    private static final String EMAIL_TAKEN_MSG_1 = "The email \"";
    private static final String EMAIL_TAKEN_MSG_2 = "\" has already been taken by another factory.";
    private static final String PRODUCTYPE_TAKEN_MSG_1 = "The Product Type \"";
    private static final String PRODUCTYPE_TAKEN_MSG_2 = "\" already has selling by a existing Factory.";

    @Autowired
    private final FactoryRepo factoryRepo;

    public FactoryDao(FactoryRepo factoryRepo) {
        this.factoryRepo = factoryRepo;
    }

    public List<Factory> getAll() {
        return factoryRepo.findAll();
    }

    public Factory getOne(Long id) {
        Optional<Factory> resp = factoryRepo.findById(id);
        return resp.orElseThrow(() -> {
            String msg = FactoryDao.getIdNotFoundMsg(id);
            return new EntityNotFoundException(msg);
        });
    }

    public void addOne(Factory factory) {
        Factory resp = factoryRepo.findByIdOrEmail(factory.getId(), factory.getEmail());
        if (resp != null) {
            String msg = FactoryDao.getAlreadyPersistsMsg(factory.getId(), factory.getEmail());
            throw new EntityExistsException(msg);
        }
        factoryRepo.save(factory);
    }

    public void updateOne(Factory factory) {
        // Check id not found
        Optional<Factory> resp = factoryRepo.findById(factory.getId());
        resp.orElseThrow(() -> {
            String msg = FactoryDao.getIdNotFoundMsg(factory.getId());
            throw new EntityNotFoundException(msg);
        });
        // Check email already persists
        Factory factoryWithEmail = factoryRepo.findByEmail(factory.getEmail());
        if (factoryWithEmail != null && factoryWithEmail.getId() != factory.getId()) {
            String msg = FactoryDao.getEmailAlreadyTakenMsg(factory.getEmail());
            throw new RuntimeException(msg);
        }
        // Update factory
        factoryRepo.updateOne(factory.getId(), factory.getEmail(), factory.getName(), factory.getProductType());
    }

    public void deleteOne(Long id) {
        // Check id not found
        Optional<Factory> resp = factoryRepo.findById(id);
        if (!resp.isPresent()) {
            String msg = FactoryDao.getIdNotFoundMsg(id);
            throw new EntityNotFoundException(msg);
        }
        // Delete by id
        factoryRepo.deleteById(id);
    }

//    public void updateOneIfProductChanged(Factory factory) {
//        // Check id not found
//        Optional<Factory> resp = factoryRepo.findById(factory.getId());
//        resp.orElseThrow(() -> {
//            String msg = FactoryDao.getIdNotFoundMsg(factory.getId());
//            throw new EntityNotFoundException(msg);
//        });
//        // Check product Type already persists with a factory
//        Factory factoryWithProductType = factoryRepo.findByProductType(factory.getProductType());
//        if (factoryWithProductType != null) {
//            String msg = FactoryDao.getProductAlreadyTakenMsg(factory.getEmail());
//            throw new RuntimeException(msg);
//        } else {
//            // Update factory with the new product
//            factoryRepo.updateOneIfProductChanged(factory.getProductType());
//        }
//    }

    public static String getIdNotFoundMsg(Long id) {
        return ID_NOT_FOUND_MSG_1 + id + ID_NOT_FOUND_MSG_2;
    }

    public static String getAlreadyPersistsMsg(Long id, String email) {
        return ADD_ERR_MSG + " [id: " + id + ", email: " + email + "]";
    }

    public static String getEmailAlreadyTakenMsg(String email) {
        return EMAIL_TAKEN_MSG_1 + email + EMAIL_TAKEN_MSG_2;
    }

    public static String getProductAlreadyTakenMsg(String productType) {
        return PRODUCTYPE_TAKEN_MSG_1 + productType + PRODUCTYPE_TAKEN_MSG_2;
    }
}
