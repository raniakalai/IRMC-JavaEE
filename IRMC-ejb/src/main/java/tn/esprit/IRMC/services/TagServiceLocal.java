package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Tag;
import tn.esprit.IRMC.persistence.Video;

@Local
public interface TagServiceLocal {
	public Tag getTagByID(String id);
	public void saveTag(Tag t);
	public void deleteTag(String id);
	public int modifyTag(Tag v,String id);
	public List<Tag> getAllTags();
	public List<Video> getVideosWithTag(Tag t);
}
