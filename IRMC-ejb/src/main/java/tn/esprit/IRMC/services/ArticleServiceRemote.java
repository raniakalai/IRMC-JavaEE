package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;
import tn.esprit.IRMC.persistence.Reference;
import tn.esprit.IRMC.persistence.Tag;

@Remote
public interface ArticleServiceRemote {


	public Article addArticle(Article a);
	public void updateArticle(Article a);
	public void affecterAuteurArticle(Auteur a , Article b );
	public void affecterReferenceArticle(Reference r  , Article b );
	public void affecterTagArticle(Tag t , Article b );
	public void deleteArticle(Integer id);
	public Article getArticlebyid(Integer id);
	public List<Article> getAllArticle();
	public List<Article> findByYear(Integer year);
	public List<Article> countByCountry(String country);



}
