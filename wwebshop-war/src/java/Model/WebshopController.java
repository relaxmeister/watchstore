/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author admin
 */
@Named(value = "webshopController")
@SessionScoped
public class WebshopController implements Serializable {

    @EJB
    private CartBean cartBean;

    @EJB
    private PersonHandler personHandler;

    private People loginUser;
    private List<People> users;
    private People user;

    private String loginUsername;
    private String loginPassword;
    private String createUsername;
    private String createPassword;

    //watches
    private List<Watches> watches;
    private Watches watch;
    
    private List<Watches> searchResult;
    private String searchString;

    //purchases
    private List<Purchase> purchases;
    private List<Purchase> selectedPurchases;

    private People adminSelectedLogin;

    private List<Watches> shoppingCart = new ArrayList<>();
    private double totalPrice = 0;
    //kanske ett watch-obj för att bli till vid ett klick? 
    //som sedan levereras till list för shopcart
    
    //kopplat till navigation
    private boolean isAdmin;
    private boolean isNormie;
    
     /**
     * Creates a new instance of WebshopController
     */
    public WebshopController() {
    }
    

    public void search(AjaxBehaviorEvent e) {
	searchResult = new ArrayList<>();
	watches.forEach((Watches w) -> {
	    if(w.getName().toLowerCase().contains(searchString.toLowerCase())){
		searchResult.add(w);
	    }
	});
    }
    
    public void clearSearch(AjaxBehaviorEvent e){
	searchResult.clear();
    }

    public List<Watches> getSearchResult() {
	return searchResult;
    }

    public List<Purchase> getSelectedPurchases() {
        return selectedPurchases;
    }

    public void setSelectedPurchases(List<Purchase> selectedPurchases) {
        this.selectedPurchases = selectedPurchases;
    }

    public People getAdminSelectedLogin() {
        return adminSelectedLogin;
    }

    public void setAdminSelectedLogin(People adminSelectedLogin) {
        this.adminSelectedLogin = adminSelectedLogin;
        
        selectedPurchases = new ArrayList<>();
        
        for (Purchase purchase : purchases){
            if(purchase.getId() == adminSelectedLogin.getId()){
                selectedPurchases.add(purchase);
            }
        }
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getCreatePassword() {
        return createPassword;
    }

    public void setCreatePassword(String createPassword) {
        this.createPassword = createPassword;
    }

    public List<Watches> getWatches() {
        return watches;
    }

    public void setWatches(List<Watches> watches) {
        this.watches = watches;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Watches getWatch() {
        return watch;
    }

    public void setWatch(Watches watch) {
        this.watch = watch;
    }

    public List<Watches> getShoppingCart() {
        return shoppingCart;
    }
    
    public void setShoppingCart(List<Watches> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<People> getUsers() {
        return users;
    }

    public void setUsers(List<People> users) {
        this.users = users;
    }

    public People getUser() {
        return user;
    }

    public void setUser(People user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void initUsers() {
        //kicka igång entity-users
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ready User Set Go", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //om det är av intresse av life cycle
        //personHandler.fillDBUsers();

    }

    public String createAccountHandler() {
        //is usernametakenvalidator
        //fetch all usernames
        // if (String) value => any username => problem
        //gör en sökning på username, om inget hit, if null -> throw exception?
        //loginUser = personHandler.findByUsername(loginUsername);
        personHandler.fillDB();

        if (personHandler.findByUsername(createUsername).getUsername() != null) { // name taken
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Username already taken"); //
            facesContext.addMessage("f2:createAccUsername", facesMessage);
            return "createAccount";
        }
        personHandler.createAccount(createUsername, createPassword);
        return "index"; //username fine and unique, account successfully created
    }

    public String loginHandler() {

        personHandler.fillDB();

        loginUser = personHandler.findByUsername(loginUsername);
        if (loginUser.getUsername() == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Incorrect username or password"); //
            facesContext.addMessage("f1:loginUsername", facesMessage);
            return "index.xhtml";
        } else //if (loginUser.getUsername() != null) {
        {
            if (!loginUser.getPassword().equals(loginPassword)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Incorrect password"); //
                facesContext.addMessage("f1:loginPassword", facesMessage);
                return "index.xhtml";
            } else { //password korrekt - inloggning perfekt
                return loginNavigation(loginUser);//"adminpage.xhtml";
            }
        }
    }

    public String loginNavigation(People user) {
        if (user.getTypeOfUser().equals("admin")) {
            purchases = personHandler.getAllPurchases();
            purchases.forEach(e -> System.out.print(e.getPerson()));
            users = personHandler.getAllUsers();
            return "adminpage.xhtml";
        }
        else if(user.getTypeOfUser().equals("premium")) {
            //user is either normal or premium
            watches = personHandler.getAllWatches();
            watches.forEach((w) -> {
                w.setPrice(w.getPrice()*0.9);
            });
            return "webshopPage.xhtml";
        }
        else{
            watches = personHandler.getAllWatches();
	    searchResult = personHandler.getAllWatches();
            return "webshopPage.xhtml";
        }
    }

    //en metod som skickar värdet av produkt-namnet och får tillbaka objektet som adderas till shopcart-listan
    //en metod som tar emot en watch och skickar vidare till productinfo som visar upp objektet
    public String takeMeToProductInfo() {

        return "productInfo";
    }

    //hämtar carten från cartBean och updaterar i denna klass för view
    public void updateCart() {
        this.shoppingCart = cartBean.getCart();
    }

    public void deleteFromCart() {
        cartBean.deleteFromCart(watch);
        updateCart();
    }
    
    public void clearCart(){
	totalPrice = 0;
        this.shoppingCart.clear();
        cartBean.clearCart();
        updateCart();
    }

    //Detta är för när vi ska göra om cart till beställning, lika bra att vi gör tillsammans när models ser är fixade
//    public void checkOutCart(){
//        for(Watch w : cart){
//            order = new Purchase(user, w);
//            c.persist(order);
//        }
//    }
    public void addToCart() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, watch.getName(), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //System.out.println(watch.getName());
        cartBean.addToCart(watch);
        updateCart();
        countTotalPrice();
    }

    public void countTotalPrice() {
        totalPrice = 0;
        shoppingCart.forEach(e -> totalPrice += e.getPrice());
    }
    //en metod som räknar ut totala kostnaden för shopcart, körs i samband med klickmetod ovan

    public void removeFromCart() {
        shoppingCart.remove(watch);
        countTotalPrice();
    }

    public String confirmOrder() {
        shoppingCart.forEach((Watches e) -> {
            Purchase p = new Purchase(loginUser, e, e.getPrice());
            personHandler.persist(p);
        });
        clearCart();
        return "webshopPage.xhtml";
    }

}
