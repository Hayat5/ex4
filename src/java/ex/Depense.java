/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Imadbourji
 */
@Entity
@Table(name = "depense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depense.findAll", query = "SELECT d FROM Depense d"),
    @NamedQuery(name = "Depense.findByIdDepense", query = "SELECT d FROM Depense d WHERE d.idDepense = :idDepense"),
    @NamedQuery(name = "Depense.findByIdUser", query = "SELECT d FROM Depense d WHERE d.idUser = :idUser"),
    @NamedQuery(name = "Depense.findByIdCategory", query = "SELECT d FROM Depense d WHERE d.idCategory = :idCategory"),
    @NamedQuery(name = "Depense.findByDateDepense", query = "SELECT d FROM Depense d WHERE d.dateDepense = :dateDepense")})
public class Depense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_depense")
    private Integer idDepense;
    
  
     @JoinColumn(name = "id_category", referencedColumnName = "categories_id")
    @ManyToOne(optional = false)
    
    private Categories idCategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_depense")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepense;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "depense")
    private Collection<Depense> depenseCollection;
    @JoinColumn(name = "depense", referencedColumnName = "id_depense")
    
    @ManyToOne(optional = false)*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "depense")
    private int depense;
    @JoinColumn(name = "id_user", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User idUser;

    public Depense() {
    }

    public Depense(Integer idDepense) {
        this.idDepense = idDepense;
    }

    public Depense(Integer idDepense, Categories idCategory, Date dateDepense) {
        this.idDepense = idDepense;
        this.idCategory = idCategory;
        this.dateDepense = dateDepense;
    }

    public Integer getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(Integer idDepense) {
        this.idDepense = idDepense;
    }

    public Categories getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Categories idCategory) {
        this.idCategory = idCategory;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

    @XmlTransient
   /* public Collection<Depense> getDepenseCollection() {
        return depenseCollection;
    }

    public void setDepenseCollection(Collection<Depense> depenseCollection) {
        this.depenseCollection = depenseCollection;
    }
*/
    public int getDepense() {
        return depense;
    }

    public void setDepense(int depense) {
        this.depense = depense;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepense != null ? idDepense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depense)) {
            return false;
        }
        Depense other = (Depense) object;
        if ((this.idDepense == null && other.idDepense != null) || (this.idDepense != null && !this.idDepense.equals(other.idDepense))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ex.Depense[ idDepense=" + idDepense + " ]";
    }
    
}
