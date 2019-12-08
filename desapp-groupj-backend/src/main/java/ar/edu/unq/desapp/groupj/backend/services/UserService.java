package ar.edu.unq.desapp.groupj.backend.services;

import java.util.List;

import ar.edu.unq.desapp.groupj.backend.model.User;
import org.springframework.transaction.annotation.Transactional;


public class UserService extends GenericService<User> {

	private static final long serialVersionUID = -2932116622242535843L;
	private User loggedUser;

	@Transactional
	public User findBySocialId(String socialId) {
		List<User> results = this.findByPropertyLikeValue("mail", socialId);
		if( results.size() > 0 )
			return results.get(0);
		return null;
	}

	@Transactional
	public Integer findUserId(String mail){
		return (Integer) this.getProperty(mail,"mail","id").get(0);
	}

	/*public User getLoggedUser() {
		return this.loggedUser;
	}*/

	@Transactional
	public void setLoggedUser( User user ) {
		this.loggedUser = user;
	}

}