package com.mycompany.easycondows.easycondows.condominium;

import com.mycompany.easycondows.easycondows.condominium.model.Condominium;
import com.mycompany.easycondows.easycondows.condominium.service.CondominiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CondominiumController {

    @Autowired
    CondominiumService condominiumService;

    @RequestMapping(value = "/condominium", method = RequestMethod.PUT)
    public ResponseEntity<Condominium> saveCondominium(@RequestBody Condominium condominium) {

        Condominium persistedCondominium = condominiumService.saveCondominium(condominium);

        return new ResponseEntity<Condominium>(persistedCondominium, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/condominium/{condominiumId}", method = RequestMethod.GET)
    public ResponseEntity<Condominium> getCondominiumById(@PathVariable Long condominiumId) {

        Condominium condominium = condominiumService.getCondominiumById(condominiumId);

        return new ResponseEntity<Condominium>(condominium, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/condominium", method = RequestMethod.GET)
    public ResponseEntity<List<Condominium>> getAllCondominiums() {

        List<Condominium> condominiums = condominiumService.getAllCondominiums();

        return new ResponseEntity<List<Condominium>>(condominiums, HttpStatus.CREATED);
    }
}
