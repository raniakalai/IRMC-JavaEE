package tn.esprit.IRMC.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import tn.esprit.IRMC.persistence.Etat;
import tn.esprit.IRMC.persistence.Role;
import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.UserServiceLocal;

@ManagedBean(name="userbean")
@RequestScoped
public class UserBean {
	@EJB
	private UserServiceLocal userService;
	private List<User> users;
	private User usertoapprove;
	private User user;
	public UserBean() {
	
	}
	@PostConstruct
	public void init() {
		users = userService.findAll();
		usertoapprove = new User();
		user = new User();
	}
	
	public String create() {    
		String navigateTo = "/page/user/users?faces-redirect=true";
		user.setRole(Role.Admin);
		user.setEtat(Etat.NotApprouved);
		System.out.println(user.getEmail());
		userService.create(user);
		return navigateTo;
	}
	
	public List<User> toapprouve() {
		List<User> userstoapprouve = new ArrayList<>();
		userstoapprouve = userService.usertoapprouve();
		return userstoapprouve;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public UserServiceLocal getUserService() {
		return userService;
	}
	public void setUserService(UserServiceLocal userService) {
		this.userService = userService;
	}
	public User getUsertoapprove() {
		return usertoapprove;
	}
	public void setUsertoapprove(User usertoapprove) {
		this.usertoapprove = usertoapprove;
	}
	public void approuver(User user) {
		userService.approuver(user);
	}
	
}