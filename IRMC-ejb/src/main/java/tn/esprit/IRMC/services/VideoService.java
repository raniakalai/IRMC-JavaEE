package tn.esprit.IRMC.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.persistence.Video;
import tn.esprit.IRMC.persistence.VideoTags;


@Stateless
public class VideoService implements VideoServiceLocal,VideoServiceRemote{
	
	@PersistenceContext
	EntityManager em ;
	
	@Override
	public Video getVideoByID(long id) {
		return em.find(Video.class, id);
	}

	@Override
	public long saveVideo(Video v) {
		em.persist(v);
		return v.getIdVideo();
	}

	@Override
	public void deleteVideo(long id) {
		Video v = getVideoByID(id);
		if (v!=null) {
			em.remove(v);
		}
	}
//
	@Override
	public int modifyVideo(Video v, long id) {
		Query q = em.createQuery("update video v set v.title=:title ,"
				+ "set v.url=:url ,"
				+ "set v.length_Sec=:length ,"
				+ "set v.description=:description "
				+ ", set v.format=:format"
				+ ", v.enabled=:enabled"
				+ "where v.idVideo=:id");
		q.setParameter("title", v.getTitle());
		q.setParameter("url", v.getUrl());
		q.setParameter("length", v.getLength_Sec());
		q.setParameter("description", v.getDescription());
		q.setParameter("format", v.getFormat());
		q.setParameter("enabled", v.isEnabled());
		q.setParameter("id", id);
		return q.executeUpdate();
	}

	@Override
	public List<Tag> getTagsInVideo(Video v) {
		List<Tag> tags = new ArrayList<Tag>();
		for (VideoTags vt : v.getVideoTags()) {
			tags.add(vt.getTag());
		}
		return tags;
	}

	@Override
	public List<Video> getAllvideos() {
		TypedQuery<Video>query=em.createQuery("select v from Video v",Video.class);
		List<Video> vdv=query.getResultList();
		
		return vdv;
		}
	
	

}
