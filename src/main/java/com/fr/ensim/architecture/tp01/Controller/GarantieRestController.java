package com.fr.ensim.architecture.tp01.Controller;

import com.fr.ensim.architecture.tp01.Model.BDD;
import com.fr.ensim.architecture.tp01.Model.Garantie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/api/garantie")
public class GarantieRestController {

    @PostMapping()
    public ResponseEntity<Garantie> addGarantie(@RequestParam("nom") String nom,
                                                @RequestParam("montant") int montant,
                                                @RequestParam("description") String description)
    {
        Garantie garantie = new Garantie(BDD.seq.getAndIncrement(), nom, montant, description);

        System.out.println("Garantie créée.");

        return ResponseEntity.ok().body(garantie);
    }

    @GetMapping()
    public ResponseEntity<Garantie[]> getAll(){
        System.out.println("Toutes les garanties récupérées.");
        Garantie[] garanties = (Garantie[]) BDD.bdd.values().toArray();
        return ResponseEntity.ok().body(garanties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garantie> getGarantie(@PathVariable("id") int id){
        if (BDD.bdd.containsKey(id)){
            Garantie garantie = BDD.bdd.get(id);

            System.out.println("Garantie récupérée :");
            System.out.println(garantie);

            return ResponseEntity.ok().body(garantie);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garantie> updateGarantie(@PathVariable("id") int id, @RequestBody Garantie garantie){
        if (BDD.bdd.containsKey(id)){
            BDD.bdd.put(id, garantie);

            System.out.println("Garantie mise à jour");

            return ResponseEntity.ok().body(garantie);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Garantie> removeGarantie(@PathVariable("id") int id){
        if (BDD.bdd.containsKey(id)){
            BDD.bdd.remove(id);
            System.out.println("Garantie supprimée.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
