package tn.esprit.IRMC.presentation.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;
import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.services.ArticleServiceImpl;
import tn.esprit.IRMC.services.AuteurServiceImpl;
import tn.esprit.IRMC.services.ReferenceServiceImpl;

@ManagedBean(name = "dtFilterView")
@ViewScoped
public class FiltreView implements Serializable {

	@EJB
	ArticleServiceImpl as;
	@EJB
	AuteurServiceImpl aus;
	@EJB
	ReferenceServiceImpl res;

	
	private Article selectedarticle;
	private List<Article> articles;
	private List<Auteur> listAuteur;
	private List<Tag> listtags;

	private List<Article> filteredarticles;

	private List<String> listAuteurss = new ArrayList<String>();
	private List<String> tags = new ArrayList<String>();

	public List<String> getListAuteurss() {
		return listAuteurss;
	}

	public void setListAuteurss(List<String> listAuteurss) {
		this.listAuteurss = listAuteurss;
	}

	@ManagedProperty("#{articleBean}")
	private ArticleBean service;

	@PostConstruct
	public void init() {
		
		run();
		listpays.size();
		articles = as.getAllArticle();
		listAuteur = aus.getAllAuteur();
		listtags = res.getAllTag();

		tags = listtags.stream().map(s -> String.valueOf(s.getTitle())).collect(Collectors.toList());
		listAuteurss = listAuteur.stream().map(s -> String.valueOf(s.getNom())).collect(Collectors.toList());
		System.out.println(listAuteurss.size());
		System.out.println(listAuteur.size());

	}
	private List<Article> listArticle = new ArrayList<Article>();
	
	public String editArticle() {
		// as.addArticle(article);
		selectedarticle.setFile(service.fichier);
as.updateArticle(selectedarticle);

		return "affichage.jsf?faces-redirect=true";

	}

	public void remove(Article article) {
	    try {
	        as.deleteArticle(article.getId());
	        addMessage("Success", "Article supprimé");
           init();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public ArticleBean getService() {
		return service;
	}

	public void setService(ArticleBean service) {
		this.service = service;
	}

	public List<Article> getFilteredarticles() {
		return filteredarticles;
	}

	public void setFilteredarticles(List<Article> filteredarticles) {
		this.filteredarticles = filteredarticles;
	}

	public List<Auteur> getListAuteur() {
		return listAuteur;
	}

	public void setListAuteur(List<Auteur> listAuteur) {
		this.listAuteur = listAuteur;
	}

	public List<Tag> getListtags() {
		return listtags;
	}

	public void setListtags(List<Tag> listtags) {
		this.listtags = listtags;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	/// pays

	private List<String> listpays = new ArrayList<String>();

	public void run() {

		String[] locales = Locale.getISOCountries();

		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);

			listpays.add(obj.getDisplayCountry());
			
			
			

		}

		System.out.println("Done");
	}

	public List<String> getListpays() {
		return listpays;
	}

	public void setListpays(List<String> listpays) {
		this.listpays = listpays;
	}

	
	
	////
	 public void destroyWorld() {
	        addMessage("Success", "Article supprimé");
	    }
	     
	    public void addMessage(String summary, String detail) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    ///

		public Article getSelectedarticle() {
			return selectedarticle;
		}

		public void setSelectedarticle(Article selectedarticle) {
			this.selectedarticle = selectedarticle;
		}
		
		public List<Auteur> getInitAuthors(Article article) {
			List<Auteur> auths = new ArrayList<Auteur>();
			for (Auteur auth : article.getAuteurs() ) {
				if (auths.contains(auth)  == false) {
					auths.add(auth);
				}
			}
			return auths;
		}
		
		public List<Tag> getInitTags(Article article) {
			List<Tag> tags = new ArrayList<Tag>();
			for (Tag tag : article.getTags() ) {
				if (tags.contains(tag)  == false) {
					tags.add(tag);
				}
			}
			return tags;
		}
	    
	    
}
