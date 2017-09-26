package jaxblist;

public interface Users<T>{

    /**
     * Check whether the user is registered or not.
     * @param email
     * @return
     */
    boolean isRegistered( String email );

    T login(String email, String password);

}
