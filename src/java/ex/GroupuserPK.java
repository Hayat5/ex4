/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Imadbourji
 */
@Embeddable
public class GroupuserPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "group_name")
    private String groupName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public GroupuserPK() {
    }

    public GroupuserPK(String groupName, int userId) {
        this.groupName = groupName;
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupName != null ? groupName.hashCode() : 0);
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupuserPK)) {
            return false;
        }
        GroupuserPK other = (GroupuserPK) object;
        if ((this.groupName == null && other.groupName != null) || (this.groupName != null && !this.groupName.equals(other.groupName))) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ex.GroupuserPK[ groupName=" + groupName + ", userId=" + userId + " ]";
    }
    
}
