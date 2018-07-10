package com.mycompany.easycondows.condominium;

import com.mycompany.easycondows.condominium.model.Condominium;
import com.mycompany.easycondows.condominium.service.CondominiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CondominiumController {

    @Autowired
    CondominiumService condominiumService;

    @RequestMapping(value = "/condominium", method = RequestMethod.POST)
    public ResponseEntity<Condominium> saveCondominium(@RequestBody Condominium condominium) {

        Condominium persistedCondominium = condominiumService.saveCondominium(condominium);

        return new ResponseEntity<Condominium>(persistedCondominium, HttpStatus.OK);
    }

    @RequestMapping(value = "/condominium/{condominiumId}", method = RequestMethod.GET)
    public ResponseEntity<Condominium> getCondominiumById(@PathVariable Long condominiumId) {

        Condominium condominium = condominiumService.getCondominiumById(condominiumId);

        return new ResponseEntity<Condominium>(condominium, HttpStatus.OK);
    }

    @RequestMapping(value = "/condominium", method = RequestMethod.GET)
    public ResponseEntity<List<Condominium>> getAllCondominiums() {

        List<Condominium> condominiums = condominiumService.getAllCondominiums();

        return new ResponseEntity<List<Condominium>>(condominiums, HttpStatus.OK);
    }

    @RequestMapping(value = "/condominium/{condominiumId}", method = RequestMethod.DELETE)
    public ResponseEntity<Condominium> removeCondominiumById(@PathVariable Long condominiumId) {

        condominiumService.deleteCondominium(condominiumId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
