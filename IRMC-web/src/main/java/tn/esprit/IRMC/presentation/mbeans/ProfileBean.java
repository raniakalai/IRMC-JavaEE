package tn.esprit.IRMC.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.UserServiceRemote;

@ManagedBean(name="ProfileBean")
@RequestScoped
public class ProfileBean {

	@EJB
	UserServiceRemote userServiceRemote;
	private User user;
	
	@PostConstruct
	public void init() {
		User user2 = new User();
		user2.setId(10);
		user = userServiceRemote.FindById(10);
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
