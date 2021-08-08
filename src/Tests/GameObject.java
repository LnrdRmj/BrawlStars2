package Tests;

import java.io.Serializable;

public class GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3309988482809264713L;

	private String nome;
	
	public GameObject(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		
		return this.getClass().getSimpleName() + "[" + this.nome + "]";
		
	}
	
}
