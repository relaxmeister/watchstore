/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author albin
 */
@Entity
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private People person;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "watch_id", referencedColumnName = "id")
    private Watches watch;
    private double total;
    private LocalDateTime purchaseDate;

    
    public Purchase(People person, Watches watch, double total) {
        this.person = person;
        this.watch = watch;
        this.total = total;
        this.purchaseDate = LocalDateTime.now();
    }
    
    public Purchase(){
	
    }

    public People getPerson() {
	return person;
    }

    public void setPerson(People person) {
	this.person = person;
    }

    public Watches getWatch() {
	return watch;
    }

    public void setWatch(Watches watch) {
	this.watch = watch;
    }

    public double getTotal() {
	return total;
    }

    public void setTotal(double total) {
	this.total = total;
    }

    public LocalDateTime getDate() {
	return purchaseDate;
    }

    public void setDate(LocalDateTime date) {
	this.purchaseDate = date;
    }
    
    
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Purchase)) {
	    return false;
	}
	Purchase other = (Purchase) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.Purchase[ id=" + id + " ]";
    }
    
    public String getInfo(){
        return "Date: " + purchaseDate.toString() + " Buyer: " + person.getId().toString() + " Watch: " + watch.getName() + total;
    }
    
}
