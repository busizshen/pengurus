package com.pengurus.crm.daos;

public interface GenericDAO <T> {

	    /** Persist the newInstance object into database */
	    void create(T newInstance);

	    /** Retrieve an object that was previously persisted to the database using
	     *   the indicated id as primary key
	     */
	    T read(long id);

	    /** Save changes made to a persistent object.  */
	    boolean update(T transientObject);

	    /** Remove an object from persistent storage in the database */
	    boolean delete(T persistentObject);
	    
}
