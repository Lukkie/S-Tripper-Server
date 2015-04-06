package view;

import Core.Foto;
import DAO.FotoFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author JelmerMAC
 */
@ManagedBean
@RequestScoped
public class FotoView {

    @EJB
    private FotoFacade fotoFacade;
    private Foto foto;
    
    /**
     * Creates a new instance of FotoView
     */
    public FotoView(){
    }
    
    //EXTEND -- Try to retrieve files from DB instead of servlets.
    
}

