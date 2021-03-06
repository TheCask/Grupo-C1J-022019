package ar.edu.unq.desapp.groupj.backend.services;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.groupj.backend.model.FoodService;
import ar.edu.unq.desapp.groupj.backend.model.Rate;
import ar.edu.unq.desapp.groupj.backend.model.User;
import ar.edu.unq.desapp.groupj.backend.model.Menu;
import org.springframework.transaction.annotation.Transactional;

public class MenuService extends GenericService<Menu> {

    private static final long serialVersionUID = -3232114422257535943L;

    @Transactional
    public List<Menu> findByUser(User user) {
        List<Menu> results = user.getMenus();
        if( results.size() > 0 )
            return results;
        return null;
    }

    @Transactional
    public List<Menu> findByText(String searchValue) {
        List<String> properties = new ArrayList<String>();
        properties.add("name");
        properties.add("description");
        List<Menu> results = this.findByTextInProperties( properties, searchValue, true );
        if( results.size() > 0 )
            return results;
        return null;
    }

    @Transactional
    public FoodService getFoodServiceByMenuId(int menuId) {
        Menu menu = this.findById(menuId);
        return (menu!=null?menu.getFoodService():null);
    }

    @Transactional
    public void rateMenu( Menu menu, User user, Integer value ) {
        menu.addRate( new Rate( menu, user, value ) );
        update(menu);
    }
}

