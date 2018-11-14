package tn.esprit.IRMC.presentation.mbeans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.sound.sampled.UnsupportedAudioFileException;

import tn.esprit.IRMC.persistence.Video;
import tn.esprit.IRMC.services.VideoServiceLocal;

@ManagedBean
@RequestScoped
@MultipartConfig(location="h:/",fileSizeThreshold=100000)
public class videoBean {

	
	
	@EJB
	private VideoServiceLocal videoServiceLocal;
	
	
	// attributes 
	private List<Video> videos ;
	private Video video;
	private Part file;
	
	public videoBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		videos = videoServiceLocal.getAllvideos();
		video = new Video();
	}
	
	public String doCreateVideo() throws IOException, UnsupportedAudioFileException {
		String navigateTo = "/videos?faces-redirect=true";
		String fileName = file.getSubmittedFileName();
		String urlFormat = "H:/PIDEV/IRMC/IRMC-web/src/main/webapp/";
		video.setFormat(file.getContentType());
		video.setUrl("video/"+fileName);
		videoServiceLocal.saveVideo(video);
		InputStream is = file.getInputStream();
		OutputStream os = new FileOutputStream(urlFormat+video.getUrl());
		 byte buffer[] = new byte[512 * 1024]; 
	        int nbLecture; 
	        while ((nbLecture = is.read(buffer)) != -1){ 
	            os.write(buffer, 0, nbLecture); 
	        } 
		os.close();
		return navigateTo;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> vids) {
		this.videos = vids;
	}
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video vid) {
		this.video = vid;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
}
