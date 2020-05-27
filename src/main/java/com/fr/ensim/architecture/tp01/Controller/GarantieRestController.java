package com.fr.ensim.architecture.tp01.Controller;

import com.fr.ensim.architecture.tp01.Model.BDD;
import com.fr.ensim.architecture.tp01.Model.Garantie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController()
public class GarantieRestController {

    /**
     * Requete POST qui créé une nouvelle garantie dans la base de donnée.
     * @param nom
     * @param montant
     * @param description
     * @return id de la garantie créé
     */
    @PostMapping("/api/garantie")
    public ResponseEntity<Integer> addGarantie(@RequestParam("nom") String nom,
                                                @RequestParam("montant") int montant,
                                                @RequestParam("description") String description)
    {
        Garantie garantie = new Garantie(BDD.seq.getAndIncrement(), nom, montant, description);
        BDD.bdd.put(garantie.getId(), garantie);

        System.out.println("Garantie créée.");

        return ResponseEntity.ok().body(garantie.getId());
    }

    /**
     * Requete GET qui récupère toutes les garantie de la base de donnée
     * @return Collection de toute les garanties de la base de donnée
     */
    @GetMapping("/api/garantie")
    public ResponseEntity<Collection<Garantie>> getAll(){
        System.out.println("Toutes les garanties récupérées.");
        return ResponseEntity.ok().body(BDD.bdd.values());
    }

    /**
     * Requete GET qui recupère une garantie dans la base de donnée selon l'id donnée en paramètre
     * @param id
     * @return un objet Garantie
     */
    @GetMapping("/api/garantie/{id}")
    public ResponseEntity<Garantie> getGarantie(@PathVariable("id") int id){
        if (BDD.bdd.containsKey(id)){
            Garantie garantie = BDD.bdd.get(id);

            System.out.println("Garantie récupérée :");
            System.out.println(garantie);

            return ResponseEntity.ok().body(garantie);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Requete PUT qui mes à jours une garantie dans la base de donnée selon l'id avec une garantie donnés en paramètre
     * @param id
     * @param garantie
     * @return un objet Garantie
     */
    @PutMapping("/api/garantie/{id}")
    public ResponseEntity<Garantie> updateGarantie(@PathVariable("id") int id, @RequestBody Garantie garantie){
        if (BDD.bdd.containsKey(id)){
            BDD.bdd.put(id, garantie);

            System.out.println("Garantie mise à jour");

            return ResponseEntity.ok().body(garantie);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Requete DELETE qui supprime de la base de donnée une garantie selon l'id donnée en paramètre
     * @param id 
     */
    @DeleteMapping("/api/garantie/{id}")
    public ResponseEntity<Garantie> removeGarantie(@PathVariable("id") int id){
        if (BDD.bdd.containsKey(id)){
            BDD.bdd.remove(id);
            System.out.println("Garantie supprimée.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
