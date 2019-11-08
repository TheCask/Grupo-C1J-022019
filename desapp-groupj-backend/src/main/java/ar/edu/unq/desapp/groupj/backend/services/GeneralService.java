package ar.edu.unq.desapp.groupj.backend.services;

public class GeneralService {

	private UserService userService;
    private MenuService menuService;
	//private AnotherService anotherService;
	//private AnotherOneService anotherOneService;

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

	/*public AnotherService getAnotherService() {
		return anotherService;
	}*/
	
	/*public void setAnotherService(final AnotherService anotherService) {
		this.anotherService = anotherService;
	}*/
	
	/*public AnotherOneService getAnotherOneService() {
		return this.anotherOneService;
	}*/
	
	/*public void setAnotherOneService(final AnotherOneService anotherOneService) {
		this.anotherOneService = anotherOneService;
	}*/

}
