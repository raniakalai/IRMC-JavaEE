package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Typed;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity implementation class for Entity: Article
 *
 */
@Entity
@Cacheable(false)

public class Article implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String titre;
	private String Source;
	private Date date;
	private Integer numero;
	private String pays;
	private String abstrait;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_ajout;
	private String file;
	

    @ManyToMany(fetch=FetchType.EAGER,cascade = { 
        CascadeType.MERGE, CascadeType.REMOVE,CascadeType.PERSIST
        
    })
    @JoinTable(name = "Article_Auteur",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "auteur_id")
    )
    
    private List<Auteur> Auteurs = new ArrayList<>();
    
    
    @OneToMany(fetch=FetchType.EAGER,
	       mappedBy="article"
	    )
	    private List<Reference> references = new ArrayList<>();
    
   
    @ManyToMany(fetch=FetchType.EAGER,cascade = { 
            CascadeType.MERGE, CascadeType.REMOVE,CascadeType.PERSIST
            
        })
        @JoinTable(name = "Article_Tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private List<Tag> Tags = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getAbstrait() {
		return abstrait;
	}

	public void setAbstrait(String abstrait) {
		this.abstrait = abstrait;
	}

	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public List<Auteur> getAuteurs() {
		
		return Auteurs;
	}
	
	
	public void setAuteurs(List<Auteur> auteurs) {
		
		Auteurs = auteurs;
	}

	public Article() {
		super();
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public List<Tag> getTags() {
		return Tags;
	}

	public void setTags(List<Tag> tags) {
		Tags = tags;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDate_ajout() {
		return date_ajout;
	}

	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}
   
}
