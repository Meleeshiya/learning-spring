package com.example.firstmvn.repositories;

import com.example.firstmvn.entities.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface FactoryRepo extends JpaRepository<Factory, Long> {

    Factory findByIdOrEmail(Long id, String email);

    Factory findByEmail(String email);

    Factory findByProductType(String productType);

    @Modifying
    @Query(
            "UPDATE Factory AS a " +
                    "SET a.email = :email, " +
                    "a.name = :name, " +
                    "a.productType = :productType " +
                    "WHERE a.id = :id"
    )
    void updateOne(
            @Param("id") Long id,
            @Param("email") String email,
            @Param("name") String name,
            @Param("productType") String productType
    );

//    void updateOneIfProductChanged(
//            @Param("productType") String productType
//    );
}
