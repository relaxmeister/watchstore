/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author admin
 */
@Entity
public class Watches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private double price;
    private String image;
    //Material och utförande
    private String model;
    private String type;
    private String diameter;
    private String thickness;
    private String boett;
    private String backsideBoett;
    private String armband;
    private String urtavla;
    private String spänne;
    private String glas;
    private String bezel; //ring
    
    //Urverk och Funktioner
    private String urverkstyp;
    private String sekundvisare;
    private String urverk;
    private String gångreserv;
    private String datumangivelse;
    private String vattenskydd;
    private String funktioner;
    private String övrigt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getBoett() {
        return boett;
    }

    public void setBoett(String boett) {
        this.boett = boett;
    }

    public String getBacksideBoett() {
        return backsideBoett;
    }

    public void setBacksideBoett(String backsideBoett) {
        this.backsideBoett = backsideBoett;
    }

    public String getArmband() {
        return armband;
    }

    public void setArmband(String armband) {
        this.armband = armband;
    }

    public String getUrtavla() {
        return urtavla;
    }

    public void setUrtavla(String urtavla) {
        this.urtavla = urtavla;
    }

    public String getSpänne() {
        return spänne;
    }

    public void setSpänne(String spänne) {
        this.spänne = spänne;
    }

    public String getGlas() {
        return glas;
    }

    public void setGlas(String glas) {
        this.glas = glas;
    }

    public String getBezel() {
        return bezel;
    }

    public void setBezel(String bezel) {
        this.bezel = bezel;
    }

    public String getUrverkstyp() {
        return urverkstyp;
    }

    public void setUrverkstyp(String urverkstyp) {
        this.urverkstyp = urverkstyp;
    }

    public String getSekundvisare() {
        return sekundvisare;
    }

    public void setSekundvisare(String sekundvisare) {
        this.sekundvisare = sekundvisare;
    }

    public String getUrverk() {
        return urverk;
    }

    public void setUrverk(String urverk) {
        this.urverk = urverk;
    }

    public String getGångreserv() {
        return gångreserv;
    }

    public void setGångreserv(String gångreserv) {
        this.gångreserv = gångreserv;
    }

    public String getDatumangivelse() {
        return datumangivelse;
    }

    public void setDatumangivelse(String datumangivelse) {
        this.datumangivelse = datumangivelse;
    }

    public String getVattenskydd() {
        return vattenskydd;
    }

    public void setVattenskydd(String vattenskydd) {
        this.vattenskydd = vattenskydd;
    }

    public String getFunktioner() {
        return funktioner;
    }

    public void setFunktioner(String funktioner) {
        this.funktioner = funktioner;
    }

    public String getÖvrigt() {
        return övrigt;
    }

    public void setÖvrigt(String övrigt) {
        this.övrigt = övrigt;
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
        if (!(object instanceof Watches)) {
            return false;
        }
        Watches other = (Watches) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Product[ id=" + id + " ]";
    }
    
}
