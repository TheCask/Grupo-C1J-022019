package ar.edu.unq.desapp.groupj.backend.services;

public class GeneralService {

	private UserService userService;
	private FoodServiceService foodServiceService;
    private MenuService menuService;

	public UserService getUserService() {
		return userService;
	}
	public FoodServiceService getFoodServiceService() { return foodServiceService; }
    public MenuService getMenuService() {
        return menuService;
    }

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
	public void setFoodServiceService(final FoodServiceService foodServiceService) { this.foodServiceService = foodServiceService; }
    public void setMenuService(final MenuService menuService) {
        this.menuService = menuService;
    }

}
