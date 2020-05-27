package com.fr.ensim.architecture.tp01.Client;

import com.fr.ensim.architecture.tp01.Model.Garantie;
import org.springframework.web.client.RestTemplate;

public class ClientREST {
    public static void main(String[] args) {
        String url = "http://localhost:8080/";

        RestTemplate restTemplate = new RestTemplate();

        // Get all garanties
        Garantie[] garanties = restTemplate.getForObject(url + "api/garantie", Garantie[].class);

        if( garanties != null ) {
            for (Garantie g:
                    garanties) {
                System.out.println(g);
            }
        }

        // Create a new Garantie object
        Garantie garantie = new Garantie(42, "Garantie 42", 42, "La garantie 42");

        // Put garantie
        restTemplate.put(url + "api/garantie/{id}", garantie, garantie.getId());

        // Get garantie
        garantie = restTemplate.getForObject(url + "api/garantie/{id}", Garantie.class, 42);

        if( garantie != null ) {
            // Delete
            restTemplate.delete(url + "api/garantie/{id}", garantie, garantie.getId());
        }
    }
}
