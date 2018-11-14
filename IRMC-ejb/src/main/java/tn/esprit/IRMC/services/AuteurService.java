package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;

@Local
public interface AuteurService  {
Auteur addAuteur(Auteur a);
public List<Auteur> getAllAuteur();
public Auteur findByName(String name);
public List<Auteur> getAllAuteurByidArticle(Integer id);
}
