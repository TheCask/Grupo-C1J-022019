package ar.edu.unq.desapp.groupj.backend.repositories;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ar.edu.unq.desapp.groupj.backend.model.Menu;

import java.io.Serializable;

public class MenuRepository extends HibernateGenericDAO<Menu> implements GenericRepository<Menu> {

    private static final long serialVersionUID = -1543956946324000004L;

    @Override
    protected Class<Menu> getDomainClass() {
        return Menu.class;
    }

    @Override
    public void deleteById(Serializable id) {
        Menu menu = this.findById(id);
        if( menu != null ) {
            menu.getFoodService().getMenus().remove(menu);
        }
        super.deleteById(id);
    }

}
