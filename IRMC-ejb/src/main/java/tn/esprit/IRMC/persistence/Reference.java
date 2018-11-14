package tn.esprit.IRMC.persistence;

import java.io.Serializable;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reference
 *
 */
@Entity

public class Reference implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String url;
	private String description;

	@ManyToOne()
	private Article article ;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	private static final long serialVersionUID = 1L;

	public Reference() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
   
	
}
