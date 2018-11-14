package tn.esprit.IRMC.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.IRMC.persistence.Role;
import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.UserServiceRemote;

@ManagedBean(name="loginbean")
@SessionScoped
public class LoginBean {
	@EJB
	UserServiceRemote userServiceRemote;
	
	private String login ;
	private String password;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String connect() {
		User user = null;
		String navigateto = null;
		user = userServiceRemote.connect(login, password);
		if (user==null) {
			System.out.println("thabet nayek");
		}else if (user.getRole().toString().equals("Admin")) {
			navigateto = "/page/user/users?faces-redirect=true";
			System.out.println(user.getRole().toString());
		}else if (user.getRole().toString().equals("Researcher")) {
			System.out.println(user.getRole().toString());
			navigateto = "/page/user/ajoutuser?faces-redirect=true";
		}
		return navigateto;
		
	}
	
	
	
}
