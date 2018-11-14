package tn.esprit.IRMC.presentation.mbeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;
import tn.esprit.IRMC.persistence.Reference;
import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.services.ArticleServiceImpl;
import tn.esprit.IRMC.services.AuteurServiceImpl;
import tn.esprit.IRMC.services.ReferenceServiceImpl;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class ArticleBean {
	
	//declaration
	@EJB
	ArticleServiceImpl as;
	@EJB
	AuteurServiceImpl aus;
	@EJB
	ReferenceServiceImpl res;
	
  String fichier;
  Date date = new Date();

  
  
	
	private UploadedFile file;
public void fileUploadListener(FileUploadEvent e) throws Exception{

		
		
		this.file = e.getFile();
		
	
		
		file.write("C:\\wamp64\\www\\IRMC\\"+file.getFileName());
		 
		 
		fichier = file.getFileName();
			
		}
			
	
	private Article article = new Article();
	private Auteur auteur = new Auteur();
	private Reference reference = new Reference();

	private List<Auteur> listAuteur = new ArrayList<Auteur>();
	private List<Reference> listReference = new ArrayList<Reference>();

	private List<Auteur> listAuteurVide = new ArrayList<Auteur>();
	private List<String> listAuteurVidee = new ArrayList<String>();
	private List<Reference> listReferenceVide = new ArrayList<Reference>();
	

	private Auteur aut = new Auteur();
	private Reference ref = new Reference();

	private List<String> listTags = new ArrayList<String>();
	private Tag tag = new Tag();
	private List<Tag> listTagVide = new ArrayList<Tag>();

	@PostConstruct
	public void init() {

		listAuteur = aus.getAllAuteur();
		listArticle = as.getAllArticle();

	}
	private List<Article> listArticle = new ArrayList<Article>();

	

	public String addArticle() {
		// as.addArticle(article);

		System.out.println(article);


		return "AjoutAuteur.jsf?faces-redirect=true";

	}

	public String addAuteur() {

		Auteur a = aus.addAuteur(auteur);
		listAuteur.add(a);
		listAuteurVidee.add(a.getNom());

		// aus.getAllAuteur();
		auteur = new Auteur();
		return "AjoutAuteur.jsf?faces-redirect=true";
	}

	public String addAuteurNext() {
		
		System.out.println(listAuteurVidee.size());
		for (String auteurr : listAuteurVidee) {
			System.out.println("Add auteur next: Adding author Line 126 (author string): " + auteurr);
			
			Auteur a = aus.findByName(auteurr);
			listAuteurVide.add(a);
			
			//as.affecterAuteurArticle(auteurr, newArticle);
		}
		
		return "AjoutReference.jsf?faces-redirect=true";
	}

	public String addReferenceVide() {
	
		Reference r = res.addReference(ref);
		listReferenceVide.add(r);
		ref = new Reference();

		return "AjoutReference.jsf?faces-redirect=true";
	}

	public String check() {
		System.out.println(listTags);

		for (String tagg : listTags) {

			Tag tt = new Tag();
			tt.setTitle(tagg);
			Tag newTag = res.addTag(tt);

			listTagVide.add(newTag);

		}
		return "ComfirmAjout.jsf?faces-redirect=true";
	}

	
	
	public String addAll(){
		
		article.setFile(fichier);
		article.setDate_ajout(date);
		Article newArticle  = as.addArticle(article);
		
		
		System.out.println(listAuteurVide.size());
		
		for (Auteur auteurr : listAuteurVide) {
			
			System.out.println("Inserting authors: ");
			
			as.affecterAuteurArticle(auteurr, newArticle);
		
		}
		
		for (Reference ref : listReferenceVide) {
		
			System.out.println("Inserting References: ----");
			as.affecterReferenceArticle(ref, newArticle);
			for (Auteur auth : newArticle.getAuteurs()) {
				System.out.println("Showing authors after modification: " + auth.getNom());
			}
			
		}
		for (Tag tag : listTagVide) {
		
			System.out.println("Inserting Tags to article: --------");
			as.affecterTagArticle(tag, newArticle);
		}
		
		for (Auteur auth : newArticle.getAuteurs()) {
			System.out.println("Showing authors after modification Finalllll: " + auth.getNom());
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "affichage.jsf?faces-redirect=true";

	}
	
	
	//affichage

	
	
	
	
	
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public List<Auteur> getListAuteur() {
		return listAuteur;
	}

	public void setListAuteur(List<Auteur> listAuteur) {
		this.listAuteur = listAuteur;
	}

	public Auteur getAut() {
		return aut;
	}

	public void setAut(Auteur aut) {
		this.aut = aut;
	}

	public List<Auteur> getListAuteurVide() {
		return listAuteurVide;
	}

	public void setListAuteurVide(List<Auteur> listAuteurVide) {
		this.listAuteurVide = listAuteurVide;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Reference> getListReference() {
		return listReference;
	}

	public void setListReference(List<Reference> listReference) {
		this.listReference = listReference;
	}

	public List<Reference> getListReferenceVide() {
		return listReferenceVide;
	}

	public void setListReferenceVide(List<Reference> listReferenceVide) {
		this.listReferenceVide = listReferenceVide;
	}

	public Reference getRef() {
		return ref;
	}

	public void setRef(Reference ref) {
		this.ref = ref;
	}

	public List<String> getListTags() {
		return listTags;
	}

	public void setListTags(List<String> listTags) {
		this.listTags = listTags;
	}

	public List<Tag> getListTagVide() {
		return listTagVide;
	}

	public void setListTagVide(List<Tag> listTagVide) {
		this.listTagVide = listTagVide;
	}

	public List<String> getListAuteurVidee() {
		return listAuteurVidee;
	}

	public void setListAuteurVidee(List<String> listAuteurVidee) {
		this.listAuteurVidee = listAuteurVidee;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	 ///countries
	
	
	
}
