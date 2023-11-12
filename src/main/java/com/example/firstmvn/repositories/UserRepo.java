package com.example.firstmvn.repositories;

import javax.transaction.Transactional;

import com.example.firstmvn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    User findByIdOrEmail(Long id, String email);

    User findByEmail(String email);

    @Modifying
    @Query(
            "UPDATE User AS a " +
                    "SET a.email = :email, " +
                    "a.name = :name, " +
                    "a.pwdHash = :pwdHash " +
                    "WHERE a.id = :id"
    )
    void updateOne(
            @Param("id") Long id,
            @Param("email") String email,
            @Param("name") String name,
            @Param("pwdHash") String pwdHash
    );

}
