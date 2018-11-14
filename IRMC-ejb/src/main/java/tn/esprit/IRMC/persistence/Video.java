package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.lang.Double;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Video
 *
 */
@Entity
@Table(name ="Video")
public class Video implements Serializable {

	// class attributes
	
	
	private long idVideo;  
	private String title;
	private Double length_Sec;
	private String url;
	private String description;
	private String format;
	private boolean enabled;

	// Association Attributes
	private List<VideoTags> videoTags; 
	
	private static final long serialVersionUID = 1L;

	public Video() {
		super();
		this.title = "";
		this.length_Sec = 0d;
		this.url = "";
		this.description = "";
		this.format = "";
		this.enabled = false;
	} 
	
	
	
	
	public Video(String title, Double length_Sec, String url, String description, String format, boolean enabled) {
		super();
		this.title = title;
		this.length_Sec = length_Sec;
		this.url = url;
		this.description = description;
		this.format = format;
		this.enabled = enabled;
	}
	@Id    
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public long getIdVideo() {
		return idVideo;
	}

	public String getTitle() {
		return title;
	}

	public Double getLength_Sec() {
		return length_Sec;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public String getFormat() {
		return format;
	}

	public boolean isEnabled() {
		return enabled;
	}
	@OneToMany(mappedBy="video" , cascade = {CascadeType.PERSIST , CascadeType.REMOVE })
	public List<VideoTags> getVideoTags() {
		return videoTags;
	}

	public void setIdVideo(long idVideo) {
		this.idVideo = idVideo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength_Sec(Double length_Sec) {
		this.length_Sec = length_Sec;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setVideoTags(List<VideoTags> videoTags) {
		this.videoTags = videoTags;
	}

	
   
}
