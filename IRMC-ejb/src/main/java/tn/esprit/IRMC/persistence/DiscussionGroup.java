package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class DiscussionGroup implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGrp;
	private String nomgrp;
    
	
	
	
	
	
	public int getIdGrp() {
		return idGrp;
	}

	public void setIdGrp(int idGrp) {
		this.idGrp = idGrp;
	}

	public String getNomgrp() {
		return nomgrp;
	}

	public void setNomgrp(String nomgrp) {
		this.nomgrp = nomgrp;
	}

	

	
	
	
}
