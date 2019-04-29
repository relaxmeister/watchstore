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
    private PersonHandler personHandler;

    private People loginUser;

    private String loginUsername;
    private String loginPassword;
    private String createUsername;
    private String createPassword;
    
    //watches
    private List<Watches> watches;
    //kanske ett watch-obj för att bli till vid ett klick? 
    //som sedan levereras till list för shopcart
    
    

    //kopplat till navigation
    private boolean isAdmin;
    private boolean isNormie;
    

    //@ViewScope för searchbar? ny bean?
    private Map<String, String> exampleData = new HashMap<String, String>() {
        {
            put("dune", "The Dune Book");
            put("lotr", "The Lord of the Rings Book");
        }
    };

    private String searchString;
    private String book;

    public void updateBook(AjaxBehaviorEvent event) {
        book = exampleData.get(searchString);
        setSearchString("");
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getBook() {
        return book;
    }

    //
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
        personHandler.fillDBUsers();
        personHandler.fillDBProducts();
        
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

        personHandler.fillDBUsers();
        personHandler.fillDBProducts();
        
        loginUser = personHandler.findByUsername(loginUsername);
        if (loginUser.getUsername() == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Incorrect username or password"); //
            facesContext.addMessage("f1:loginUsername", facesMessage);
            return "index.xhtml";
        } 
        else //if (loginUser.getUsername() != null) {
        {
            if (!loginUser.getPassword().equals(loginPassword)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Incorrect password"); //
                facesContext.addMessage("f1:loginPassword", facesMessage);
                return "index.xhtml";
            }
            else { //password korrekt - inloggning perfekt
                return loginNavigation(loginUser);//"adminpage.xhtml";
            }
        }
    }

    public String loginNavigation(People user) {
        if (user.getTypeOfUser().equals("admin")){
            return "adminpage.xhtml";
        }
        else {
            //user is either normal or premium
            watches = personHandler.getAllWatches();
            return "webshopPage.xhtml";
        }
    }
    
    //en metod som skickar värdet av produkt-namnet och får tillbaka objektet som adderas till shopcart-listan
    public void placeholder() {
        
    }
    //en metod som räknar ut totala kostnaden för shopcart, körs i samband med klickmetod ovan
    
    /**
     * Creates a new instance of WebshopController
     */
    public WebshopController() {
        
    }

}
