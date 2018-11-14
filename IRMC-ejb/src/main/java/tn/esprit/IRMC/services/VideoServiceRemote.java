package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.persistence.Video;

@Remote
public interface VideoServiceRemote {
	public Video getVideoByID(long id);
	public long saveVideo(Video v);
	public void deleteVideo(long id);
	public int modifyVideo(Video v,long id);
	public List<Video> getAllvideos();
	public List<Tag> getTagsInVideo(Video v);
	
	 
}
