package tn.esprit.IRMC.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.IRMC.persistence.Etat;
import tn.esprit.IRMC.persistence.User;

@Stateless
public class UserService implements UserServiceLocal ,UserServiceRemote{
	@PersistenceContext
	private EntityManager em;

	private User user;
	@Override
	public Boolean updateUser(User usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeUser(User usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		

		return  em.createQuery("select d from User d", User.class).getResultList();
				//em.createQuery("select d from user d",User.class).getResultList();
	}

	@Override
	public User FindById(int id) {
		User user;
		Query q=em.createQuery("select u from User u where u.id=:p1 ");
		q.setParameter("p1", id);
		user = (User) q.getSingleResult();
		
		return user;
	}
	@Override
	public void create(User usr) {
		System.out.println("from service ");
		em.persist(usr);
	}

	@Override
	public User connect(String login, String password) {
		User u=null;
		Query q=em.createQuery("select u from User u where u.login=:p1 and u.password=:p2");
		q.setParameter("p1", login);
		q.setParameter("p2",password);
		try {
			u = (User) q.getSingleResult();

		} catch (Exception e) {

			System.out.println("error " + e.getMessage());
		}
		user =u ;
		return u;
	}

	@Override
	public List<User> usertoapprouve() {
		Query q=em.createQuery("select u from User u where u.etat=:p1");
		q.setParameter("p1", Etat.NotApprouved);
		List<User> users = new ArrayList<>();
		users = q.getResultList();
		return users;
	}

	@Override
	public void approuver(User user) {
		System.out.println("from service "+user.getId());
		Query q=em.createQuery("UPDATE User u SET u.etat=:p1 where u.id=:p2");
		q.setParameter("p1", Etat.Approved);
		q.setParameter("p2", user.getId());
		q.executeUpdate();


	}
}
