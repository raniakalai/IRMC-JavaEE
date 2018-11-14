package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: VideoTags
 *
 */
@Entity

public class VideoTags implements Serializable {

	// class attributes
	private double startAt;
	private double endAt;
	private VideoTagPK id;

	// Association attributes
	private Video video;
	private Tag tag;

	private static final long serialVersionUID = 1L;

	public VideoTags() {
		super();
	}

	@ManyToOne
	@JoinColumn(name = "idVideo", referencedColumnName = "idVideo", insertable = false, updatable = false)
	public Video getVideo() {
		return video;
	}

	@ManyToOne
	@JoinColumn(name = "idTag", referencedColumnName = "title", insertable = false, updatable = false)
	public Tag getTag() {
		return tag;
	}

	public double getStartAt() {
		return startAt;
	}

	public double getEndAt() {
		return endAt;
	}

	@EmbeddedId
	public VideoTagPK getId() {
		return id;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void setStartAt(double startAt) {
		this.startAt = startAt;
	}

	public void setEndAt(double endAt) {
		this.endAt = endAt;
	}

	public void setId(VideoTagPK id) {
		this.id = id;
	}

}
