package com.mycompany.easycondows.easycondows.condominium.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Condominium {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public String name;

    @NotNull
    public String street;

    @NotNull
    public String neighborhood;

    @NotNull
    public String number;

    @NotNull
    public String zipCode;

    @NotNull
    public Boolean active;

    @ManyToOne
    Manager manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
