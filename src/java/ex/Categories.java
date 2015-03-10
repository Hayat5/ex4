/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Imadbourji
 */
@Entity
@Table(name = "categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
    @NamedQuery(name = "Categories.findByCategoriesId", query = "SELECT c FROM Categories c WHERE c.categoriesId = :categoriesId"),
    @NamedQuery(name = "Categories.findByCategoriesName", query = "SELECT c FROM Categories c WHERE c.categoriesName = :categoriesName")})
public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categories_id")
    private Integer categoriesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categories_name")
    private String categoriesName;

    public Categories() {
    }

    public Categories(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Categories(Integer categoriesId, String categoriesName) {
        this.categoriesId = categoriesId;
        this.categoriesName = categoriesName;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoriesId != null ? categoriesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.categoriesId == null && other.categoriesId != null) || (this.categoriesId != null && !this.categoriesId.equals(other.categoriesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ex.Categories[ categoriesId=" + categoriesId + " ]";
    }
    
}
