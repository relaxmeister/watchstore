/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;
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

    public void fillDB() {
        People user1 = new People();
        People user2 = new People();
        People user3 = new People();
        People user4 = new People();
        Watches watch1 = new Watches();
        Watches watch2 = new Watches();
        Watches watch3 = new Watches();
        Watches watch4 = new Watches();
        //containern hanterar sina egna transaktioner
        //vi har ju transaction-type="JTA" i vår persistence.xml
        //och detta är ju en EJB
        //så vi får inte själva hantera transaktioner:
        //¨ em.getTransaction().begin();  
        Query q = em.createQuery("select o from People o");
        int size = q.getResultList().size();
        if (size < 1) {

            user1.setUsername("admin");
            user1.setPassword("123");
            user1.setTypeOfUser("admin"); //enum i framtiden?
            persist(user1);

            user2.setUsername("user1");
            user2.setPassword("1234");
            user2.setTypeOfUser("normal");
            persist(user2);

            user3.setUsername("user15");
            user3.setPassword("1234");
            user3.setTypeOfUser("normal");
            persist(user3);

            user4.setUsername("user2");
            user4.setPassword("12345");
            user4.setTypeOfUser("premium");
            persist(user4);
        }
        
        q = em.createQuery("select o from Watches o");
        size = q.getResultList().size();
        if (size < 1) {

            watch1.setName("Rolex Yacht-Master");
            watch1.setPrice(359995);
            watch1.setImage("rolexYacht");
            watch1.setModel("116688");
            watch1.setType("Herrklocka, Seglarmoell");
            watch1.setDiameter("44 mm");
            watch1.setThickness("14 mm");
            watch1.setBoett("Polerat 18 karat gult guld");
            watch1.setBacksideBoett("Solid");
            watch1.setArmband("Borstat och polerat 18 karat gult guld. Oysterlock");
            watch1.setUrtavla("Vit med självlysande indexmarkeringar, självlysande visare");
            watch1.setSpänne("Viklås i 18 karat gult guld. Oysterclasp");
            watch1.setGlas("Repskyddat safirglas");
            watch1.setBezel("Keramisk, blå, vridbar medsols/motsols");
            watch1.setUrverkstyp("Automatisk, självuppdragande");
            watch1.setSekundvisare("Svepande");
            watch1.setUrverk("Rolex-in-house 4160 med 42 juveler, 28800 vph");
            watch1.setGångreserv("72 timmar");
            watch1.setDatumangivelse("");
            watch1.setVattenskydd("100 meter");
            watch1.setFunktioner("Kronograf (regatta)");
            watch1.setÖvrigt("Kronometer (COSC-certifierad), skruvkrona");
            persist(watch1);

            watch2.setName("Cartier Ballon Blue");
            watch2.setPrice(440210);
            watch2.setImage("cartierBlue");
            watch2.setModel("WJBB0032");
            watch2.setType("Unisexklocka, dressmodell");
            watch2.setDiameter("42 mm");
            watch2.setThickness("13 mm");
            watch2.setBoett("Borstat/polerat 18 karat vitt guld");
            watch2.setBacksideBoett("Solid");
            watch2.setArmband("Rosa läder, krokodil");
            watch2.setUrtavla("Silverfärgad med romerska siffror och indexmarkeringar");
            watch2.setSpänne("Viklås i 18 karat vitt guld");
            watch2.setGlas("Repskyddat safirglas");
            watch2.setBezel("18 karat vitt guld, diamantinfattat");
            watch2.setUrverkstyp("Automatisk, självuppdragande");
            watch2.setSekundvisare("Svepande");
            watch2.setUrverk("");
            watch2.setGångreserv("");
            watch2.setDatumangivelse("Datum");
            watch2.setVattenskydd("3 ATM");
            watch2.setFunktioner("");
            watch2.setÖvrigt("Skruvkrona");
            persist(watch2);

            watch3.setName("Patek Philippe Aquanaut");
            watch3.setPrice(461695);
            watch3.setImage("patek");
            watch3.setModel("5164R-001");
            watch3.setType("Herrklocka");
            watch3.setDiameter("40.8 mm");
            watch3.setThickness("");
            watch3.setBoett("Roséguld");
            watch3.setBacksideBoett("");
            watch3.setArmband("Gummi");
            watch3.setUrtavla("Brun");
            watch3.setSpänne("Viklås i stål");
            watch3.setGlas("Repskyddat safirglas");
            watch3.setBezel("");
            watch3.setUrverkstyp("Automatisk");
            watch3.setSekundvisare("Svepande");
            watch3.setUrverk("Caliber 324 S C FUS");
            watch3.setGångreserv("");
            watch3.setDatumangivelse("Datum");
            watch3.setVattenskydd("100 meter");
            watch3.setFunktioner("");
            watch3.setÖvrigt("");
            persist(watch3);

            watch4.setName("Omega Constellation Day-Date");
            watch4.setPrice(121260);
            watch4.setImage("OmegaConstellation");
            watch4.setModel("123.25.38.22.02.002");
            watch4.setType("Herrklocka, dressmodell");
            watch4.setDiameter("38 mm");
            watch4.setThickness("");
            watch4.setBoett("18 karat gult stål");
            watch4.setBacksideBoett("Repskyddat safirglas");
            watch4.setArmband("18 karat gult stål");
            watch4.setUrtavla("Silverfärgad med självlysande markeringar indexmarkeringar, självlysande visare");
            watch4.setSpänne("Viklås i stål");
            watch4.setGlas("Repskyddat safirglas");
            watch4.setBezel("18 karat gult guld, diamantinfattat");
            watch4.setUrverkstyp("Automatisk, självuppdragande");
            watch4.setSekundvisare("Svepande");
            watch4.setUrverk("Omega in-house urverk Co-Axial 8602, Si 14 balance spring");
            watch4.setGångreserv("55 mm");
            watch4.setDatumangivelse("Veckodag och datum");
            watch4.setVattenskydd("100 meter");
            watch4.setFunktioner("");
            watch4.setÖvrigt("Kronometercertifiering (COSC)");
            persist(watch4);
        }
            
        q = em.createQuery("select o from Purchase o");
        size = q.getResultList().size();
        if(size < 1){
            
            Purchase p1 = new Purchase();
            p1.setWatch(watch1);
            p1.setPerson(user1);
            p1.setTotal(watch1.getPrice());
            p1.setDate(LocalDateTime.now());
            persist(p1);
            
            Purchase p2 = new Purchase();
            p2.setWatch(watch2);
            p2.setPerson(user2);
            p2.setTotal(watch2.getPrice());
            p2.setDate(LocalDateTime.now());
            persist(p2);
            
            Purchase p3 = new Purchase();
            p3.setWatch(watch3);
            p3.setPerson(user3);
            p3.setTotal(watch3.getPrice());
            p3.setDate(LocalDateTime.now());
            persist(p3);
            
            Purchase p4 = new Purchase();
            p4.setWatch(watch2);
            p4.setPerson(user3);
            p4.setTotal(watch2.getPrice());
            p4.setDate(LocalDateTime.now());
            persist(p4);
            
            Purchase p5 = new Purchase();
            p5.setWatch(watch4);
            p5.setPerson(user4);
            p5.setTotal(watch4.getPrice());
            p5.setDate(LocalDateTime.now());
            persist(p5);
            
            Purchase p6 = new Purchase();
            p6.setWatch(watch1);
            p6.setPerson(user1);
            p6.setTotal(watch1.getPrice());
            p6.setDate(LocalDateTime.now());
            persist(p6);
        }

        //containern hanterar sina egna transaktioner, så
        //vi får inte själva hantera dem:
        //¨  em.getTransaction().commit();

        // em.close();app-servern stänger resurserna, så vi ska inte stänga dem
    }
    
//    public void fillDBProducts() {
//        //containern hanterar sina egna transaktioner
//        //vi har ju transaction-type="JTA" i vår persistence.xml
//        //och detta är ju en EJB
//        //så vi får inte själva hantera transaktioner:
//        //¨ em.getTransaction().begin();  
//        Query q = em.createQuery("select o from Watches o");
//        int size = q.getResultList().size();
//        if (size < 1) {
//
//            Watches watch = new Watches();
//            watch.setName("Rolex Yacht-Master");
//            watch.setPrice(359995);
//            watch.setImage("rolexYacht");
////            watch.setModel("116688");
////            watch.setType("Herrklocka, Seglarmoell");
////            watch.setDiameter("44 mm");
////            watch.setThickness("14 mm");
////            watch.setBoett("Polerat 18 karat gult guld");
////            watch.setBacksideBoett("Solid");
////            watch.setArmband("Borstat och polerat 18 karat gult guld. Oysterlock");
////            watch.setUrtavla("Vit med självlysande indexmarkeringar, självlysande visare");
////            watch.setSpänne("Viklås i 18 karat gult guld. Oysterclasp");
////            watch.setGlas("Repskyddat safirglas");
////            watch.setBezel("Keramisk, blå, vridbar medsols/motsols");
////            watch.setUrverkstyp("Automatisk, självuppdragande");
////            watch.setSekundvisare("Svepande");
////            watch.setUrverk("Rolex-in-house 4160 med 42 juveler, 28800 vph");
////            watch.setGångreserv("72 timmar");
////            watch.setDatumangivelse("");
////            watch.setVattenskydd("100 meter");
////            watch.setFunktioner("Kronograf (regatta)");
////            watch.setÖvrigt("Kronometer (COSC-certifierad), skruvkrona");
//            persist(watch);
//            
//            
//            
//            
//
//            watch = new Watches();
//            watch.setName("Cartier Ballon Blue");
//            watch.setPrice(440210);
//            watch.setImage("cartierBlue");
////            watch.setModel("116688");
////            watch.setType("5");
//            persist(watch);
//
//
//            watch = new Watches();
//            watch.setName("Patek Philippe Aquanaut");
//            watch.setPrice(461695);
//            watch.setImage("patek");
////            watch.setModel("116688");
////            watch.setType("5");
//            persist(watch);
//
//
//            watch = new Watches();
//            watch.setName("Omega Constellation Day-Date");
//            watch.setPrice(121260);
//            watch.setImage("OmegaSeamaster");
////            watch.setModel("116688");
////            watch.setType("5");
//            persist(watch);
//
//        }
//        //containern hanterar sina egna transaktioner, så
//        //vi får inte själva hantera dem:
//        //¨  em.getTransaction().commit();
//
//        // em.close();app-servern stänger resurserna, så vi ska inte stänga dem
//    }
//    
//    public void fillDBPurchases(){
//        Purchase p = new Purchase();
//        
//        People user = new People();
//        user.setUsername("user1");
//        user.setPassword("1234");
//        user.setTypeOfUser("normal");
//        p.setPerson(user);
//        
//        Watches watch = new Watches();
//        watch.setName("Cartier Ballon Blue");
//        watch.setPrice(440210);
//        watch.setImage("cartierBlue");
////        watch.setModel("116688");
////        watch.setType("5");
//        p.setWatch(watch);
//        p.setTotal(watch.getPrice());
//        p.setDate(LocalDateTime.now());
//        
//        persist(p);
//        
//        Purchase p2 = new Purchase();
//        
//        People user2 = new People();
//        user2.setUsername("user5");
//        user2.setPassword("12345678");
//        user2.setTypeOfUser("normal");
//        p2.setPerson(user2);
//        
//        Watches watch2 = new Watches();
//        watch2.setName("Patek Philippe Aquanaut");
//        watch2.setPrice(461695);
//        watch2.setImage("patek");
////        watch.setModel("116688");
////        watch.setType("5");
//        p2.setWatch(watch2);
//        p2.setTotal(watch2.getPrice());
//        p2.setDate(LocalDateTime.now());
//        
//        persist(p2);
//    }
    
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

    public List<People> getAllUsers() {
        List<People> users = new ArrayList();

        try {
            Query q = em
                    .createQuery("SELECT o FROM People o");
            users = (List<People>) q.getResultList();

        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();

        try {
            Query q = em.createQuery("SELECT o FROM Purchase o");
            purchases = (List<Purchase>) q.getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return purchases;
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

    public void updateUser(People loginUser) {
        Query q = em.createQuery("select p from People p where p.id = :userid ");
        q.setParameter("userid", loginUser.getId());
        
        People p = (People) q.getSingleResult();
        p.setTypeOfUser(loginUser.getTypeOfUser());
        em.merge(p);
    }

    public double getTotalPurchaseSum(People user) {
         double a = 0;
        try {

            Query q = em.createQuery("select sum(p.total) from Purchase p WHERE p.person.id=:user group by p.person.id");
            q.setParameter("user", user.getId());
            a = (double) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }
}
