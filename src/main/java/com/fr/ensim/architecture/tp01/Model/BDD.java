package com.fr.ensim.architecture.tp01.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Base de données simulée
 * Singleton
 */
public enum BDD {
    INSTANCE;
    public static final Map<Integer, Garantie> bdd  = new HashMap<>();
    public static final AtomicInteger seq = new AtomicInteger();
}
