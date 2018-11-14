package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: VideoTagPK
 *
 */
@Embeddable

public class VideoTagPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long idVideo;
	private String idTag;

	public VideoTagPK() {
		super();
	}

	public long getVideoId() {
		return idVideo;
	}

	public String getTagId() {
		return idTag;
	}

	public void setVideoId(long videoId) {
		this.idVideo = videoId;
	}

	public void setTagId(String tagId) {
		this.idTag = tagId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTag == null) ? 0 : idTag.hashCode());
		result = prime * result + (int) (idVideo ^ (idVideo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoTagPK other = (VideoTagPK) obj;
		if (idTag == null) {
			if (other.idTag != null)
				return false;
		} else if (!idTag.equals(other.idTag))
			return false;
		if (idVideo != other.idVideo)
			return false;
		return true;
	}
   
}
