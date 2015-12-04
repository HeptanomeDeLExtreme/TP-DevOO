package modele;

import java.util.*;

/**
 * 
 */
public class Client {

    /**
     * Constructeur de base
     * @param int idClient
     */
    public Client(int idClient) {
		this.id = idClient;
	}

    /**
     * Identifiant du client
     */
    protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}