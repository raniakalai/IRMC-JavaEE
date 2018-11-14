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
import tn.esprit.IRMC.persistence.Reference;
import tn.esprit.IRMC.persistence.Tag;

@Stateless
@LocalBean
public class ReferenceServiceImpl implements ReferenceService,ReferenceServiceRemote {
	
	@PersistenceContext
	EntityManager em;
	@EJB
	ArticleService se;

	



	@Override
	public Reference addReference(Reference r) {
		em.persist(r);
		return r ;
	
	}



	@Override
	public List<Reference> getAllReference() {
		Query q = em.createQuery("select d from reference d",Reference.class);
		return q.getResultList();
	}



	@Override
	public Tag addTag(Tag t) {
		em.persist(t);
		return t ;
		
	}



	@Override
	public List<Tag> getAllTag() {
		Query q = em.createQuery("select d from Tag d",Tag.class);
		return q.getResultList();	}







	@Override
	public List<Tag> getAllTagsByidArticle(Integer id) {
		Article a = new Article();
		a = se.getArticlebyid(id);
	    TypedQuery<Tag>  aut = em.createQuery("SELECT  a FROM Tag a left join a.Articles s where s.id=:id", Tag.class);
			
			return aut.setParameter("id", a.getId()).getResultList();
	}



	@Override
	public List<Reference> getAllRefByidArticle(Integer id) {
		Article a = new Article();
		a = se.getArticlebyid(id);
	    TypedQuery<Reference>  aut = em.createQuery("SELECT  a FROM Reference a left join a.article s where s.id=:id", Reference.class);
			
			return aut.setParameter("id", a.getId()).getResultList();
	}

}
