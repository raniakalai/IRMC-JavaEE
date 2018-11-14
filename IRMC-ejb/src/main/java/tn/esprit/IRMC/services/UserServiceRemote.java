package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.User;


@Remote
public interface UserServiceRemote {
	void create(User user);
	Boolean updateUser(User usr);
    Boolean removeUser(User usr);
    List<User> findAll();
    User FindById(int id);
    User connect (String login , String password);
    List<User> usertoapprouve();
    void approuver(User user);

}
