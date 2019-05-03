/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless
@LocalBean
public class PersonHandler {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "wwebshop-ejbPU")
    private EntityManager em;

    public void fillDBUsers() {
        //containern hanterar sina egna transaktioner
        //vi har ju transaction-type="JTA" i vår persistence.xml
        //och detta är ju en EJB
        //så vi får inte själva hantera transaktioner:
        //¨ em.getTransaction().begin();  
        Query q = em.createQuery("select o from People o");
        int size = q.getResultList().size();
        if (size < 1) {

            People user = new People();
            user.setUsername("admin");
            user.setPassword("123");
            user.setTypeOfUser("admin"); //enum i framtiden?
            persist(user);

            user = new People();
            user.setUsername("user1");
            user.setPassword("1234");
            user.setTypeOfUser("normal");
            persist(user);

            user = new People();
            user.setUsername("user15");
            user.setPassword("1234");
            user.setTypeOfUser("normal");
            persist(user);

            user = new People();
            user.setUsername("user2");
            user.setPassword("12345");
            user.setTypeOfUser("premium");
            persist(user);
        }
        //containern hanterar sina egna transaktioner, så
        //vi får inte själva hantera dem:
        //¨  em.getTransaction().commit();

        // em.close();app-servern stänger resurserna, så vi ska inte stänga dem
    }
    
    public void fillDBProducts() {
        //containern hanterar sina egna transaktioner
        //vi har ju transaction-type="JTA" i vår persistence.xml
        //och detta är ju en EJB
        //så vi får inte själva hantera transaktioner:
        //¨ em.getTransaction().begin();  
        Query q = em.createQuery("select o from Watches o");
        int size = q.getResultList().size();
        if (size < 1) {

            Watches watch = new Watches();
            watch.setName("Rolex Yacht-Master");
            watch.setPrice(359995);
            watch.setImage("rolexYacht");
            watch.setModel("116688");
            watch.setType("Herrklocka, Seglarmoell");
            watch.setDiameter("44 mm");
            watch.setThickness("14 mm");
            watch.setBoett("Polerat 18 karat gult guld");
            watch.setBacksideBoett("Solid");
            watch.setArmband("Borstat och polerat 18 karat gult guld. Oysterlock");
            watch.setUrtavla("Vit med självlysande indexmarkeringar, självlysande visare");
            watch.setSpänne("Viklås i 18 karat gult guld. Oysterclasp");
            watch.setGlas("Repskyddat safirglas");
            watch.setBezel("Keramisk, blå, vridbar medsols/motsols");
            watch.setUrverkstyp("Automatisk, självuppdragande");
            watch.setSekundvisare("Svepande");
            watch.setUrverk("Rolex-in-house 4160 med 42 juveler, 28800 vph");
            watch.setGångreserv("72 timmar");
            watch.setDatumangivelse("");
            watch.setVattenskydd("100 meter");
            watch.setFunktioner("Kronograf (regatta)");
            watch.setÖvrigt("Kronometer (COSC-certifierad), skruvkrona");
            persist(watch);
            
            
            
            

            watch = new Watches();
            watch.setName("Cartier Ballon Blue");
            watch.setPrice(440210);
            watch.setImage("cartierBlue");
            watch.setModel("116688");
            watch.setType("5");
            persist(watch);


            watch = new Watches();
            watch.setName("Patek Philippe Aquanaut");
            watch.setPrice(461695);
            watch.setImage("patek");
            watch.setModel("116688");
            watch.setType("5");
            persist(watch);


            watch = new Watches();
            watch.setName("Omega Constellation Day-Date");
            watch.setPrice(121260);
            watch.setImage("OmegaSeamaster");
            watch.setModel("116688");
            watch.setType("5");
            persist(watch);

        }
        //containern hanterar sina egna transaktioner, så
        //vi får inte själva hantera dem:
        //¨  em.getTransaction().commit();

        // em.close();app-servern stänger resurserna, så vi ska inte stänga dem
    }
    
    public List<Watches> getAllWatches() {
        List<Watches> watches = new ArrayList();
        
        try {
            Query q = em
                    .createQuery("SELECT o FROM Watches o");
            watches = (List<Watches>) q.getResultList();

        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return watches;
    }

//automatgenererad metod
    public void persist(Object object) {
        em.persist(object);
    }

    public People findByUsername(String username) {
        People user = new People();
        try {
            Query q = em
                    .createQuery("SELECT p FROM People p WHERE p.username =:username");
            q.setParameter("username", username);
            user = (People) q.getSingleResult();

        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void createAccount(String username, String password) {
        People user = new People();
        user.setUsername(username);
        user.setPassword(password);
        user.setTypeOfUser("normal");
        persist(user);
    }

    public void placeholder() {

    }
}
