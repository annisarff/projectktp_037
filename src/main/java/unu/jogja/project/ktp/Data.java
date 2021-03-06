/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unu.jogja.project.ktp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d"),
    @NamedQuery(name = "Data.findById", query = "SELECT d FROM Data d WHERE d.id = :id"),
    @NamedQuery(name = "Data.findByNama", query = "SELECT d FROM Data d WHERE d.nama = :nama"),
    @NamedQuery(name = "Data.findByTgllahir", query = "SELECT d FROM Data d WHERE d.tgllahir = :tgllahir"),
    @NamedQuery(name = "Data.findByNik", query = "SELECT d FROM Data d WHERE d.nik = :nik"),
    @NamedQuery(name = "Data.findByBerlaku", query = "SELECT d FROM Data d WHERE d.berlaku = :berlaku")})
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "tgllahir")
    @Temporal(TemporalType.DATE)
    private Date tgllahir;
    @Basic(optional = false)
    @Column(name = "nik")
    private int nik;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "berlaku")
    private String berlaku;

    public Data() {
    }

    public Data(Integer id) {
        this.id = id;
    }

    public Data(Integer id, String nama, Date tgllahir, int nik) {
        this.id = id;
        this.nama = nama;
        this.tgllahir = tgllahir;
        this.nik = nik;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(Date tgllahir) {
        this.tgllahir = tgllahir;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getBerlaku() {
        return berlaku;
    }

    public void setBerlaku(String berlaku) {
        this.berlaku = berlaku;
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
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unu.jogja.project.ktp.Data[ id=" + id + " ]";
    }
    
}
