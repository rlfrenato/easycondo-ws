package com.mycompany.easycondows.condominium.service;

import com.mycompany.easycondows.condominium.model.Condominium;
import com.mycompany.easycondows.condominium.repository.CondominiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondominiumService {

    @Autowired
    CondominiumRepository condominiumRepository;

    public Condominium saveCondominium(Condominium condominium) {
        return condominiumRepository.save(condominium);
    }

    public void deleteCondominium(Long id) {
        condominiumRepository.delete(id);
    }

    public List<Condominium> getAllCondominiums() {
        return (List<Condominium>) condominiumRepository.findAll();
    }

    public Condominium getCondominiumById(Long id) {
        return condominiumRepository.findOne(id);
    }
}
