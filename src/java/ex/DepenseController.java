package ex;

import ex.util.JsfUtil;
import ex.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.*;
@ManagedBean(name = "depenseController")
@SessionScoped
public class DepenseController implements Serializable {

    @EJB
    private ex.DepenseFacade ejbFacade;
    private List<Depense> items = null;
    private Depense selected;
    private List<Depense> depense = null;
    private List<Object[]> listbyuser;
    private List<Object[]> listbyc;
    private int depenses;

     
    public DepenseController() {
    }
 public List<Object[]> getListbyc() {
        listbyc = getFacade().bycat();
        return listbyc;
    }

    public int getDepenses() {
        return depenses;
    }

    public void setDepenses(int depenses) {
        this.depenses = depenses;
    }
   
    public void setListbyc(List<Object[]> listbyc) {
        this.listbyc = listbyc;
    }
    public Depense getSelected() {
        return selected;
    }

    public void setSelected(Depense selected) {
        this.selected = selected;
    }
    
     public List<Object[]> getListbyuser() {
       
       listbyuser = getFacade().byu();
       
       return listbyuser;
    }


    public void setListbyuser(List<Object[]> listbyuser) {
        this.listbyuser = listbyuser;
    }
    
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DepenseFacade getFacade() {
        return ejbFacade;
    }

    public Depense prepareCreate() {
        selected = new Depense();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DepenseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DepenseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DepenseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Depense> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

     public List<Depense> getDepense() {
         
           FacesContext context = FacesContext.getCurrentInstance();
             ExternalContext ectx = context.getExternalContext();
              HttpServletRequest request = (HttpServletRequest)ectx.getRequest();
              
              HttpSession session = request.getSession(true);
                  //set a string session attribute
                  int id = (int) session.getAttribute("MySessionVariable");
                  System.out.println(id);
          
            depense = getFacade().byiduser(id);
                 // depense = getFacade().findAll();
          return depense;  
       
    }
    
  public void setDepense(List<Depense> depense) {
      depense = this.depense;
     
  }
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Depense> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Depense> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Depense.class)
    public static class DepenseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DepenseController controller = (DepenseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "depenseController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Depense) {
                Depense o = (Depense) object;
                return getStringKey(o.getIdDepense());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Depense.class.getName()});
                return null;
            }
        }

    }

}
