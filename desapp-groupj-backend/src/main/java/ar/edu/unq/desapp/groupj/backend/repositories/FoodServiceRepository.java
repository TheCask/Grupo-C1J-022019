package ar.edu.unq.desapp.groupj.backend.repositories;

import ar.edu.unq.desapp.groupj.backend.model.FoodService;

import java.io.Serializable;

public class FoodServiceRepository extends HibernateGenericDAO<FoodService> implements GenericRepository<FoodService> {

    private static final long serialVersionUID = -8543996946214099004L;

    @Override
    protected Class<FoodService> getDomainClass() {
        return FoodService.class;
	}

	@Override
    public void deleteById(Serializable id) {
        FoodService foodService = this.findById(id);
        if( foodService != null ) {
            foodService.getProvider().getFoodServices().remove(foodService);
        }
        super.deleteById(id);
    }

}
