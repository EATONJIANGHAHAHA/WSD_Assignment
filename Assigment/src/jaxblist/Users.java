package jaxblist;

public interface Users{

    /**
     * Check whether the user is registered or not.
     * @param email
     * @return
     */
    boolean isRegistered( String email );

    boolean login(String email, String password);

}
