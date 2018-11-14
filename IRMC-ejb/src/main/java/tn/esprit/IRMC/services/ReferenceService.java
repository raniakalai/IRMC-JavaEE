package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;
import tn.esprit.IRMC.persistence.Reference;
import tn.esprit.IRMC.persistence.Tag;

@Local
public interface ReferenceService  {
Reference addReference(Reference r);
public List<Reference> getAllReference();
Tag addTag(Tag t);
public List<Tag> getAllTag();
public List<Tag> getAllTagsByidArticle(Integer id);
public List<Reference> getAllRefByidArticle(Integer id);


}
