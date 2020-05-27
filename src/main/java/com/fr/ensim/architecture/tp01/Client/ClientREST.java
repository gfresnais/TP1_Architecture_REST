package com.fr.ensim.architecture.tp01.Client;

import com.fr.ensim.architecture.tp01.Model.Garantie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Client REST (main)
 */
public class ClientREST {
    public static void main(String[] args) {
        String url = "http://localhost:8080/api/garantie";

        RestTemplate restTemplate = new RestTemplate();

        // Get all garanties
        ResponseEntity<Garantie[]> responseEntity = restTemplate.getForEntity(url, Garantie[].class);
        Garantie[] garanties = responseEntity.getBody();

        if( garanties != null ) {
            for (Garantie g:
                    garanties) {
                System.out.println(g);
            }
        }

        // Create a new Garantie object
        Integer id = restTemplate.postForObject(url + "?nom={nom}&montant={montant}&description={description}",
                                                    null, Integer.class,
                                                "Garantie 42",
                                                            42,
                                                            "La garantie 42");

        // Get garantie
        Garantie garantie = restTemplate.getForObject(url + "/{id}", Garantie.class, id);

        if( garantie != null ) {
            System.out.println(garantie);

            // Update some values
            garantie.setNom("Garantie toto");
            garantie.setMontant(100);
            garantie.setDescription("La garantie toto 100");
            // Put garantie
            restTemplate.put(url + "/{id}", garantie, garantie.getId());
        }

        if( garantie != null ) {
            System.out.println(garantie);

            // Delete
            restTemplate.delete(url + "/{id}", garantie.getId());
        }
    }
}
