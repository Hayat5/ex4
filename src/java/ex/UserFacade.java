/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author Imadbourji
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "exPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
     public List<User> name(String name) {  
           List<User> Out;  
           Out = em.createNamedQuery("User.findByUserName").setParameter("userName", name).getResultList();  
           return Out;  
      }  
      public List<User> findWithName(String name) {
       
        Query x = em.createNativeQuery("SELECT u FROM User u ", User.class);
     return  x.getResultList();
     
 }
      public List<User> findusers(int x) {
       
          return em.createQuery(
    "SELECT u FROM User u WHERE u.userId = :userId ")
    .setParameter("userId", x).getResultList();
     
 }
       public Object byname(String name) {
return em.createQuery(
    "SELECT u FROM User u WHERE u.userName = :userName")
    .setParameter("userName", name).getSingleResult();

}

}