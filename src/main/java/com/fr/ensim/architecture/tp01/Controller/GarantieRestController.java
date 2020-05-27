package com.fr.ensim.architecture.tp01.Controller;

import com.fr.ensim.architecture.tp01.Model.BDD;
import com.fr.ensim.architecture.tp01.Model.Garantie;
import com.sun.istack.internal.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/api/garantie")
public class GarantieRestController {

    BDD bdd = BDD.INSTANCE;

    @PostMapping("")
    public ResponseEntity<Garantie> addGarantie(@RequestParam("nom") String nom,
                                                      @RequestParam("montant") Integer montant,
                                                      @RequestParam("description") String description)
    {
        Garantie garantie = new Garantie(bdd.seq.getAndIncrement(), nom, montant, description);

        System.out.println("Garantie créée.");

        return ResponseEntity.ok().body(garantie);
    }

    @GetMapping()
    public ResponseEntity<Collection<Garantie>> getAll(){
        System.out.println("Toutes les garanties récupérées.");
        return ResponseEntity.ok().body(bdd.bdd.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garantie> getGarantie(@PathVariable("id") @NotNull Integer id){
        if (bdd.bdd.containsKey(id)){
            Garantie garantie = bdd.bdd.get(id);

            System.out.println("Garantie récupérée :");
            System.out.println(garantie);

            return ResponseEntity.ok().body(garantie);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garantie> updateGarantie(@PathVariable("id") @NotNull Integer id, @RequestBody Garantie garantie){
        if (bdd.bdd.containsKey(id)){
            bdd.bdd.put(id, garantie);

            System.out.println("Garantie mise à jour");

            return ResponseEntity.ok().body(garantie);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeGarantie(@PathVariable("id") @NotNull Integer id){
        if (bdd.bdd.containsKey(id)){
            bdd.bdd.remove(id);
            System.out.println("Garantie supprimée.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
