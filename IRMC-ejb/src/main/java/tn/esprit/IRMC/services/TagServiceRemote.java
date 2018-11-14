package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Video;
import tn.esprit.IRMC.persistence.Tag;

@Remote
public interface TagServiceRemote {
	public Tag getTagByID(String id);
	public void saveTag(Tag t);
	public void deleteTag(String id);
	public int modifyTag(Tag v,String id);
	public List<Tag> getAllTags();
	public List<Video> getVideosWithTag(Tag t);
}
