package ar.edu.unq.desapp.groupj.backend.services;

public class GeneralService {

	private UserService userService;
    private MenuService menuService;

	public UserService getUserService() {
		return userService;
	}
    public MenuService getMenuService() {
        return menuService;
    }

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
    public void setMenuService(final MenuService menuService) {
        this.menuService = menuService;
    }

}
