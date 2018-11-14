package tn.esprit.IRMC.services;

import java.util.List; 

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.User;


@Local
public interface UserServiceLocal {
	void create(User user);
	Boolean updateUser(User usr);
    Boolean removeUser(User usr);
    List<User> findAll();
    User FindById(int id);
    User connect (String login , String password);
    List<User> usertoapprouve();
    void approuver(User user);
}
