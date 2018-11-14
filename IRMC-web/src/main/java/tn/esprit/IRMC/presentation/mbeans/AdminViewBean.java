package tn.esprit.IRMC.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.UserServiceRemote;

@ManagedBean(name="AdminViewBean")
@RequestScoped
public class AdminViewBean {
	
	
	
	public String redirectapprouver() {
		return "/page/user/users?faces-redirect=true";
	}

}
