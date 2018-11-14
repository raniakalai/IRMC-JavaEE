package tn.esprit.IRMC.presentation.ressources;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.IRMC.persistence.Article;
import tn.esprit.IRMC.persistence.Auteur;
import tn.esprit.IRMC.persistence.Reference;
import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.services.ArticleServiceRemote;
import tn.esprit.IRMC.services.AuteurServiceRemote;
import tn.esprit.IRMC.services.ReferenceServiceRemote;
import tn.esprit.IRMC.services.TagServiceRemote;
@Path(value = "Article")
@Stateless
public class ArticleRest {
	@EJB
	ArticleServiceRemote asr;
	@EJB
	AuteurServiceRemote ausr;
	@EJB
	TagServiceRemote tsr;
	@EJB
	ReferenceServiceRemote rsr;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallarticle(){
		List<Article> t= asr.getAllArticle() ;
		List<Auteur>  t1 = ausr.getAllAuteur();
		for (Article article : t) {
			
			
			article.setAuteurs(null);
			article.setReferences(null);
			article.setTags(null);
			
			
		}
		return Response.ok().entity(t).build();
	}
	
	@GET
	@Path(value="article")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getarticle(@QueryParam(value = "id") int id){
		
		Article a = asr.getArticlebyid(id);
		a.setAuteurs(null);
		a.setReferences(null);
		a.setTags(null);

		
		return Response.ok().entity(a).build();
	}
	@GET
	@Path(value="auteurs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getauteur(@QueryParam(value = "id") int id){
		
		List<Auteur> la = ausr.getAllAuteurByidArticle(id);
		List<Auteur> aut = new ArrayList<Auteur>();
		
		for (Auteur auteur : la) {
			
			
			
			auteur.setArticles(null);
			if (aut.contains(auteur)  == false) {
				aut.add(auteur);
			}
			
		}

		
		return Response.ok().entity(aut).build();
	}
	@GET
	@Path(value="tags")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gettags(@QueryParam(value = "id") int id){
		
		List<Tag> la = rsr.getAllTagsByidArticle(id);
		List<Tag> aut = new ArrayList<Tag>();

		for (Tag auteur : la) {
			
			
			
			auteur.setArticles(null);
			
			if (aut.contains(auteur)== false) {
				aut.add(auteur);
			}
			
		}

		
		return Response.ok().entity(aut).build();
	}
	
	@GET
	@Path(value="refs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getref(@QueryParam(value = "id") int id){
				
		List<Reference> la = rsr.getAllRefByidArticle(id);
		
		for (Reference reference : la) {
			
			reference.setArticle(null);
			
		}
	

		
		return Response.ok().entity(la).build();
	}
	
	@PUT
	@Path("updateArticle")
	public Response updateuser(Article article) {
		asr.updateArticle(article);
		Response.status(Status.CREATED).entity("Article Modified").build();
		return Response.ok("Your Article has been Modified!").build();
	}
	
	@POST
	@Path("addArticle")
	public Response addUser(Article article) {
		asr.addArticle(article);
		Response.status(Status.CREATED).entity("Article added").build();
		return Response.ok("Your Article has been CREATED!").build();
		
	}
	
	@Path("deleteArticle")
	@DELETE
	public Response removeUser(@QueryParam(value = "id") int id) {
		asr.deleteArticle(id);

			
		return Response.status(Status.OK).entity("Votre Article associe a l'ID: "+id+" a ete supprime avec succes!").build();

		}
}
