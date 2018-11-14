package tn.esprit.IRMC.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.persistence.Video;
import tn.esprit.IRMC.persistence.VideoTags;

@Stateless
public class TagService implements TagServiceLocal,TagServiceRemote{

	EntityManager em ;
	
	@Override
	public Tag getTagByID(String id) {
		return em.find(Tag.class, id);
	}

	@Override
	public void saveTag(Tag t) {
		em.persist(t);
	}

	@Override
	public void deleteTag(String id) {
		Tag v = getTagByID(id);
		if (v!=null) {
			em.remove(v);
		}
		
	}

	@Override
	public int modifyTag(Tag t, String id) {
		Query q = em.createQuery("update Tag t set title=:title ,"
				+ "set description=:description "
				+ "where t.title=:id");
		q.setParameter("title", t.getTitle());
		//q.setParameter("description", t.getDescription());
		q.setParameter("id", id);
		return q.executeUpdate();
	}

	@Override
	public List<Tag> getAllTags() {
		TypedQuery<Tag> q = em.createQuery("select * from Tag t",Tag.class);
		return q.getResultList();
	}

	@Override
	public List<Video> getVideosWithTag(Tag t) {
		List<Video> videos = new ArrayList<Video>();
		/*for (VideoTags vt : t.getVideoTags()) {
			videos.add(vt.getVideo());
		}
		*/
		return videos;
	}

	}

