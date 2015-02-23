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
public class DepenseFacade extends AbstractFacade<Depense> {
    @PersistenceContext(unitName = "exPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepenseFacade() {
        super(Depense.class);
    }
     public List<Depense> name(String name) {  
           List<User> Out;  
           Out = em.createNamedQuery("User.findByUserName").setParameter("userName", name).getResultList();  
          List<Depense> outdepense = em.createNamedQuery("User.findByUserId").setParameter("userId", Out).getResultList();  
           
           return outdepense;  
      }  
       public List<Depense> byiduser(int x) {
           System.out.print("Je suis x");
           System.out.println(x);
             return em.createQuery(
    "SELECT d FROM Depense d WHERE d.idUser IN (SELECT u FROM User u WHERE u.userId = :userId) ")
    .setParameter("userId", x).getResultList();
        

}
       
         public List<Object[]> byu(){
             
              return (List<Object[]>) em.createQuery("SELECT SUM(d.depense), d.idUser  FROM Depense d GROUP BY d.idUser").getResultList();

          //   String qlString = "SELECT SUM(d.depense),d.idUser  FROM Depense d GROUP BY d.idUser";
          //   System.out.println(qlString);
          //  return (List<Object[]>) em.createQuery(qlString).getResultList();

             }
         
           public List<Object[]> bycat(){
               System.out.println("hi");
              return (List<Object[]>) em.createQuery("SELECT SUM(d.depense),d.idCategory  FROM Depense d GROUP BY d.idCategory").getResultList();

             }

}
