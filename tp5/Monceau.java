import java.util.ArrayList;

public class Monceau {
	ArrayList<Node> arbres;

	public Monceau() {
		arbres = new ArrayList<Node>();
	}

	public void fusion(Monceau autre) {
		// A completer
		Node retenue = null;
		Node arbreautre = null;
		Node arbrethis = null;
		ArrayList<Node> enordre = new ArrayList<Node>();
		
		//Pour tout j, en commençant par 0, on vérifie le nombre d'arbres 
		//d'ordre j dans les 2 monceaux plus la retenue.
		int j = 0;
		int maxordre = 0;
		if(this.arbres.size() != 0) {maxordre = this.arbres.get(this.arbres.size()-1).ordre;}
		else if(autre.arbres.size() != 0 && autre.arbres.get(autre.arbres.size()-1).ordre > maxordre) 
			{ maxordre = autre.arbres.get(autre.arbres.size()-1).ordre;}
		
		//tant qu'il n'y a plus rien, pour tout j
		for(; j <= maxordre +1; j++) {
			int n = 0;
			
			//Dans le cas où la retenue est d'ordre j, 
			//n est incrémenté de 1.
			if(retenue != null && retenue.ordre == j) n++;
			
			//Dans le cas où autre est d'ordre j, 
			//n est incrémenté de 2.
			for(int i = 0; i < autre.arbres.size(); i++)
				if (autre.arbres.get(i).ordre == j) {
					n+=2;
					arbreautre = autre.arbres.get(i);
					autre.arbres.remove(i);
				}
			
			//Dans le cas où this est d'ordre j,
			//n est incrémenté de 4.
			for(int i = 0; i < this.arbres.size(); i++)
				if (this.arbres.get(i).ordre == j) {
						n+=4;
						arbrethis = this.arbres.get(i);
						this.arbres.remove(i);
					}
			try {
				switch(n) {
				case 0:
					//Il y a aucun arbre d'ordre j
					break;
				case 1:
					//Il y a un seul arbre d'ordre j (retenue)
					this.arbres.add(retenue);
					enordre.add(retenue);
					break;
				case 2:
					//Il y a un seul arbre d'ordre j (autre)
					this.arbres.add(arbreautre);
					enordre.add(arbreautre);
					break;
				case 3:
					//Il y a deux arbres d'ordre j (retenue et autre)
					retenue = retenue.fusion(arbreautre);
					break;
				case 4:
					//Il y a un seul arbre d'ordre j (l'arbre this)
					this.arbres.add(arbrethis);
					enordre.add(arbrethis);
					break;
				case 5:
					//Il y a deux arbres d'ordre j (retenue et this)
					retenue = retenue.fusion(arbrethis);
					break;
				case 6:
					//Il y a deux arbres d'ordre j (this et autre)
					retenue = arbrethis.fusion(arbreautre);
					break;
				case 7:
					//Il y a trois arbres d'ordre j (retenue, this et autre)
					retenue = retenue.fusion(arbreautre);
					this.arbres.add(arbrethis);
					enordre.add(arbrethis);
					break;

				default: break;
				}
			} catch(DifferentOrderTrees e){}
		}
		
		this.arbres = enordre;
		return;
	}

	public void insert(int val) {
		// A completer
		Monceau n = new Monceau();
		Node nnode = new Node(val);
		
		//On ajoute le Node nnode dans le monceau n
		n.arbres.add(nnode);
		
		//On fusionne ce monceau avec this
		this.fusion(n);
	}

	public boolean delete(int val) {
		// A completer
		boolean removeEffectuee = false;
		
		//On supprime tous les noeuds ayant la valeur val
		for( int i = 0; i < this.arbres.size(); i++ ) {
			if ( this.arbres.get(i).findValue(val) != null ) { 
				//si on trouve un noeud de valeur val:
				ArrayList<Node> arr = this.arbres.get(i).findValue(val).delete();
				this.arbres.remove(i);
				
				//arr contient les enfants (arbres binomiaux) du noeud ayant comme valeur val
				Monceau n = new Monceau();
				n.arbres = arr;
				this.fusion(n);
	
				removeEffectuee = true;
				this.delete(val);
				break;
			}
		}

		return removeEffectuee;
	}

	public void print() {
		for (Node node : arbres) {
			System.out.println("\n\nordre : " + node.ordre);
			node.print();
		}
	}

}
