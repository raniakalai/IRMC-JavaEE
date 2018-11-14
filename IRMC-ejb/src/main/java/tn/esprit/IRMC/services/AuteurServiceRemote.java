package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Auteur;

@Remote
public interface AuteurServiceRemote {
Auteur addAuteur(Auteur a);
public List<Auteur> getAllAuteur();
public Auteur findByName(String name);
public List<Auteur> getAllAuteurByidArticle(Integer id);
}
