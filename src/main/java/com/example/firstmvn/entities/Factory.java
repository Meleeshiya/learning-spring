package com.example.firstmvn.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "factory")
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String productType;

    @NotBlank
    @CreationTimestamp
    private Date created;


    public Factory() {
        this.id = -1L;
        this.email = "";
        this.name = "";
        this.productType ="";
        this.created = new Date(System.currentTimeMillis());
    }

    public Factory(String name) {
        this.id = -1L;
        this.email = "";
        this.name = name;
        this.productType = "";
        this.created = new Date(System.currentTimeMillis());
    }


    public Factory(Long id, String name) {
        this.id = id;
        this.email = "";
        this.name = name;
        this.productType = "";
        this.created = new Date(System.currentTimeMillis());
    }

    public Factory(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.productType = "";
        this.created = new Date(System.currentTimeMillis());
    }


    public Factory(String email, String name) {
        this.id = -1L;
        this.email = email;
        this.name = name;
        this.productType = "";
        this.created = new Date(System.currentTimeMillis());
    }


    public Factory(
            Long id,
            String email,
            String name,
            String productType,
            Date created
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.productType = productType;
        this.created = created;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getProductType() {
        return this.productType;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }


    public Date getCreated() {
        return this.created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


}
