import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

	public int ordre;
	public Node parent;

	private int valeur;
	private ArrayList<Node> enfants;

	public Node(int valeur) {
		this.valeur = valeur;
		ordre = 0;
		this.parent = null;
		enfants = new ArrayList<Node>();
	}

	public Node(int valeur, Node parent) {
		ordre = 0;
		this.valeur = valeur;
		this.parent = parent;
		enfants = new ArrayList<Node>();
	}

	public int getVal() {
		return valeur;
	}

	public ArrayList<Node> getEnfants() {
		return enfants;
	}

	public void addEnfant(Node enfant) {
		enfants.add(enfant);
	}

	public boolean removeEnfant(Node enfant) {
		if (enfants.contains(enfant)) {
			enfants.remove(enfant);
			return true;
		}
		return false;
	}

	public void removeEnfants(ArrayList<Node> enfants) {
		this.enfants.removeAll(enfants);
	}

	public Node fusion(Node autre) throws DifferentOrderTrees {
		// A completer
		
		if( this.ordre != autre.ordre ) {
			//Si les deux noeuds n'ont pas le m�me ordre
			throw new DifferentOrderTrees(); 
		}
		
		if(this.parent == null && autre.parent == null) {
			//Si les deux noeuds sont des racines, on les fusionne
			
			if ( this.valeur < autre.valeur ) {
				//Dans le cas ou le noeud this a une valeur plus petite que autre,
				//on ajoute this comme enfant de autre
				autre.addEnfant(this);
				autre.ordre++;
				this.parent = autre;
				return autre;
			}
			
			else {
				//Dans le cas ou le noeud this a une valeur plus grande que autre,
				//on ajoute autre comme enfant de this
				this.addEnfant(autre);
				this.ordre++;
				autre.parent = this;
				return this;
			}
		}
		return null;
	}

	private void moveUp() {
		// A completer
		
		Node parent = this.parent;
		ArrayList<Node> enfants = this.enfants;
		int ordre = this.ordre;
		
		//On �change la position du noeud this et de son parent
		this.parent = parent.parent;
		this.enfants = parent.enfants;
		this.enfants.add(parent);
		this.enfants.remove(this);
		this.ordre = parent.ordre;
		
		//Les enfants du parent deviennent les enfants du noeud this
		//et le parent du noeud this devient son enfant
		parent.enfants = enfants;
		parent.parent = this;
		parent.ordre = ordre;
		if (this.parent != null) {
			this.parent.enfants.add(this);
			this.parent.enfants.remove(parent);
		}
	}

	public ArrayList<Node> delete() {
		// A completer
		
		while(this.parent != null) {
			//Tant que le noeud n'est pas la racine, on le fait monter (moveUp)
			this.moveUp();
		}
			
		for(int i = 0; i < this.enfants.size(); i++) {
			//On supprime la r�f�rence au noeud de ses enfants
			this.enfants.get(i).parent = null;
		}
		
		return this.enfants;
	}

	public Node findValue(int valeur) {
		// A completer
		
		//Renvoie le premier noeud ayant comme valeur "valeur" rencontr�
		if (this.valeur == valeur)
			return this;
		
		//On effectue une recherche r�cursive parmis ses enfants
		else if ( this.valeur > valeur) {
			for ( int i = 0; i < this.enfants.size(); i++ ) {
				Node temp = this.enfants.get(i).findValue(valeur);
				if ( temp != null ) return temp;
			}
		}
		
		return null;
	}

	public ArrayList<Integer> getElementsSorted() {
		// A completer
		
		ArrayList<Node> S = new ArrayList<Node>();
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		
		//Tri les �l�ments de l'arbre en ordre d�croissant et retourne 
		//les �l�ments tri�s
		
		//Algorithme ressemblant dijkstra, on place les noeuds visit�s dans S,
		//puis on les met en ordre dans l'arrayList sorted
		S.add(this); //la racine
		
		while (S.size() != 0) {
			int indexmax = 0;	//index du noeud ayant la plus grande valeur � date
			
			for(int n = 1; n < S.size(); n++) {
				//On trouve l'index du noeud ayant la plus grande valeur dans S
				if(S.get(n).valeur > S.get(indexmax).valeur)
					indexmax = n;
			}
			
			Node nodemax = S.get(indexmax);
			//On ajoute le noeud ayant la plus grande valeur de S dans  
			//l'ArrayList sorted, puis on le retire de S
			sorted.add(nodemax.valeur);
			S.remove(indexmax);
			for(int n = 0; n < nodemax.enfants.size(); n++) {
				//On ajoute les enfants du noeud dans S (noeuds visit�s)
				S.add(nodemax.enfants.get(n));
			}
		}
		
		return sorted;
	}

	public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + valeur);
        for (int i = 0; i < enfants.size() - 1; i++) {
            enfants.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (enfants.size() > 0) {
            enfants.get(enfants.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }
}
