package ar.edu.unq.desapp.groupj.backend.repositories;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ar.edu.unq.desapp.groupj.backend.model.Menu;

public class MenuRepository extends HibernateGenericDAO<Menu> implements GenericRepository<Menu> {

    private static final long serialVersionUID = -1543956946324000004L;

    @Override
    protected Class<Menu> getDomainClass() {
        return Menu.class;
    }

}
