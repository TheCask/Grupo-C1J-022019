package ar.edu.unq.desapp.groupj.backend.model;

import java.util.ArrayList;

public class Provider extends Client {

    private ArrayList<Service> services = new ArrayList<Service>();
    private ArrayList<Menu> menus = new ArrayList<Menu>();

    public Provider(String firstName, String lastName, String mail, String phone, String city, String address, int credit) {
        super(firstName, lastName, mail, phone, city, address, credit);
    }

    public Provider(String firstName, String lastName, String mail, String phone, String city, String address, int credit, ArrayList<Service> services, ArrayList<Menu> menus) {
        super(firstName, lastName, mail, phone, city, address, credit);
        this.menus = menus;
        this.services = services;
    }

    public void addMenu(Menu aMenu) { this.menus.add(aMenu); }

    public void updateMenu(Menu aMenuToUpdate, Menu updatedMenu) {
        this.menus.remove(aMenuToUpdate);
        this.menus.add(updatedMenu);
    }

    public void deleteMenu(Menu aMenu) { this.menus.remove(aMenu); }

    public int withdrawCredit(int credit) {
        int newCredit = this.getCredit() - credit;
        if (newCredit >= 0) { this.setCredit(newCredit); }
        return this.getCredit();
    }

    //GETTERS
    public ArrayList<Service> getServices() { return services; }
    public ArrayList<Menu> getMenus() { return menus; }

    //SETTERS
    public void setServices(ArrayList<Service> services) { this.services = services; }
    public void setMenus(ArrayList<Menu> menus) { this.menus = menus; }
}
