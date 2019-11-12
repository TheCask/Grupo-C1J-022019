package ar.edu.unq.desapp.groupj.backend.services;

import java.util.List;

import ar.edu.unq.desapp.groupj.backend.model.User;
import ar.edu.unq.desapp.groupj.backend.model.Menu;

public class MenuService extends GenericService<Menu> {

    private static final long serialVersionUID = -3232114422257535943L;

    //@Transactional
    public List<Menu> findByUser(User user) {
        List<Menu> results = user.getMenus();
        if( results.size() > 0 )
            return results;
        return null;
    }

    //@Transactional
    public List<Menu> findByName(String name) {
        List<Menu> results = this.findByPropertyLikeValue("name", name);
        if( results.size() > 0 )
            return results;
        return null;
    }
}

