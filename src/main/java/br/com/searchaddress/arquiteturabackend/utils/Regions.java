package br.com.searchaddress.arquiteturabackend.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Natália Bruno Rabelo.
 * Classe que armazena os UF de acordo com região.
 */
public class Regions {

    public static Set<String> regiaoNorte = new HashSet<>(Arrays.asList("AC", "AM", "AP", "PA", "RO", "RR", "TO"));
    public static Set<String> regiaoNordeste = new HashSet<>(Arrays.asList("AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE"));
    public static Set<String> regiaoCentroOeste = new HashSet<>(Arrays.asList("DF", "GO", "MT", "MS"));
    public static Set<String> regiaoSudeste = new HashSet<>(Arrays.asList("ES", "MG", "RJ", "SP"));
    public static Set<String> regiaoSul = new HashSet<>(Arrays.asList("PR", "RS", "SC"));

}
