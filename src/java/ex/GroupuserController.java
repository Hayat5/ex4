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

@ManagedBean(name = "groupuserController")
@SessionScoped
public class GroupuserController implements Serializable {

    @EJB
    private ex.GroupuserFacade ejbFacade;
    private List<Groupuser> items = null;
    private Groupuser selected;

    public GroupuserController() {
    }

    public Groupuser getSelected() {
        return selected;
    }

    public void setSelected(Groupuser selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setGroupuserPK(new ex.GroupuserPK());
    }

    private GroupuserFacade getFacade() {
        return ejbFacade;
    }

    public Groupuser prepareCreate() {
        selected = new Groupuser();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GroupuserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GroupuserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GroupuserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Groupuser> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public List<Groupuser> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Groupuser> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Groupuser.class)
    public static class GroupuserControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GroupuserController controller = (GroupuserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "groupuserController");
            return controller.getFacade().find(getKey(value));
        }

        ex.GroupuserPK getKey(String value) {
            ex.GroupuserPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ex.GroupuserPK();
            key.setGroupName(values[0]);
            key.setUserId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(ex.GroupuserPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGroupName());
            sb.append(SEPARATOR);
            sb.append(value.getUserId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Groupuser) {
                Groupuser o = (Groupuser) object;
                return getStringKey(o.getGroupuserPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Groupuser.class.getName()});
                return null;
            }
        }

    }

}
