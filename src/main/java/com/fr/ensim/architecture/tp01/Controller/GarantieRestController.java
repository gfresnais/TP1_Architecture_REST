package com.fr.ensim.architecture.tp01.Controller;

import com.fr.ensim.architecture.tp01.Model.BDD;
import com.fr.ensim.architecture.tp01.Model.Garantie;
import com.sun.istack.internal.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/api/garantie")
public class GarantieRestController {

    BDD bdd = BDD.INSTANCE;

    @GetMapping()
    public ResponseEntity<Collection<Garantie>> getAll(){
        Collection<Garantie> listGarantie = bdd.bdd.values();
        return ResponseEntity.ok().body(listGarantie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garantie> getGarantie(@PathVariable("id") @NotNull Integer id){
        Garantie garantie = bdd.bdd.get(id);
        return ResponseEntity.ok().body(garantie);
    }

}
