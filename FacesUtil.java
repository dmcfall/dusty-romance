package dusty;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {
	

    public static Object getApplicationMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
    }

    public static void setApplicationMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
    }

    public static Object getSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
    public static void removeSessionMapValue(String key){
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }
    
    public static Object getViewBean(String beanName){
    	return FacesContext.getCurrentInstance().getViewRoot().getViewMap().get(beanName);
    }
    
    public static Object getSessionBean(String beanName){
    	return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(beanName);
    }

    public static Object getRequestParam(String paramName){		 
    	return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(paramName);
    }
    
    /**
     * Returns an HttpServletRequest for the adn pages.
     * @return
     */
    public static HttpServletRequest getServletRequest() {
    	return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
