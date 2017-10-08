package service.rest;

import dao.UserDAO;
import dao.UserDAOImpl;
import jaxblist.Users;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static dao.UserDAOImpl.TUTOR_SERVICE;
import static dao.UserDAOImpl.WEB_INF_TUTORS_XML;


/**
 * A restful web service for tutors.
 */
@Path("/tutorService")
public class TutorService {
    @Context
    private ServletContext application;

    /**
     * Get tutor dao.
     * @return
     */
    private UserDAO getTutorDAO(){
        synchronized (application){
            UserDAO tutorDAO = (UserDAOImpl)
                    application.getAttribute(TUTOR_SERVICE);
            if(tutorDAO == null){
                tutorDAO = new UserDAOImpl(application.getRealPath(WEB_INF_TUTORS_XML));
                application.setAttribute(TUTOR_SERVICE, tutorDAO);
            }
            return tutorDAO;
        }
    }



    /**
     *  Return the tutor information according to the query param
     * @param status tutor's status
     * @param email tutor's email
     * @return
     */
    @Path("tutors")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Users getTutors(@QueryParam("status") String status, @QueryParam("email")
            String email){
        if(status != null && email != null) return getTutorDAO().searchUsersByEmail(email).findTutorByStatus(status);
        if(status != null) return getTutorDAO().searchByStatus(status);
        if(email != null) return getTutorDAO().searchUsersByEmail(email);
        return getTutorDAO().read();
    }
}
