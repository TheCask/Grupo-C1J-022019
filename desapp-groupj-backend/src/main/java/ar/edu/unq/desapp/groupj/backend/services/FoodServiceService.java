package ar.edu.unq.desapp.groupj.backend.services;

import ar.edu.unq.desapp.groupj.backend.model.FoodService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FoodServiceService extends GenericService<FoodService> {

    private static final long serialVersionUID = -2938236622242535843L;

    @Transactional
    public List<FoodService> findByUserId(Integer id) {
        return this.findByPropertyColumn("user_id",id,"=" );
    }
}
