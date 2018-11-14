package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;

@Stateless
@LocalBean
public class AuteurServiceImpl implements AuteurService,AuteurServiceRemote {
	
	@PersistenceContext
	EntityManager em;
	@EJB
	ArticleService se;

	

	@Override
	public Auteur addAuteur(Auteur a) {
		em.persist(a);	
		return a ;
	}



	@Override
	public List<Auteur> getAllAuteur() {
		Query q = em.createQuery("select  d from Auteur d",Auteur.class);
		return q.getResultList();
	}



	@Override
	public Auteur findByName(String name) {
		TypedQuery<Auteur>  aut= em.createQuery("SELECT a FROM Auteur a where a.nom=:name", Auteur.class);
		return aut.setParameter("name", name).getSingleResult();
	}



	@Override
	public List<Auteur> getAllAuteurByidArticle(Integer id) {
		Article a = new Article();
	a = se.getArticlebyid(id);
    TypedQuery<Auteur>  aut = em.createQuery("SELECT  a FROM Auteur a left join a.Articles s where s.id=:id", Auteur.class);
		
		return aut.setParameter("id", a.getId()).getResultList();
	}

}
