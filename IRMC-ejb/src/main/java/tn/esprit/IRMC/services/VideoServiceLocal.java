package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.persistence.Video;

@Local
public interface VideoServiceLocal {
	public Video getVideoByID(long id);
	public long saveVideo(Video v);
	public void deleteVideo(long id);
	public int modifyVideo(Video v,long id);
	public List<Video> getAllvideos();
	public List<Tag> getTagsInVideo(Video v);
}
