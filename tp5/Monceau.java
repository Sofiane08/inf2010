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
		

		int j = 0;
		int maxordre = 0;
		if(this.arbres.size() != 0) {maxordre = this.arbres.get(this.arbres.size()-1).ordre;}
		else if(autre.arbres.size() != 0 && autre.arbres.get(autre.arbres.size()-1).ordre > maxordre) 
			{ maxordre = autre.arbres.get(autre.arbres.size()-1).ordre;}
		
		//tant qu<il ny a pu rien
		//pour tout j
		for(; j <= maxordre +1; j++)
		{

			int n = 0;
			if(retenue != null && retenue.ordre == j) n++;
			for(int i = 0; i < autre.arbres.size(); i++)
				if (autre.arbres.get(i).ordre == j)
				{
					n+=2;
					arbreautre = autre.arbres.get(i);
					autre.arbres.remove(i);
				}
			for(int i = 0; i < this.arbres.size(); i++)
				if (this.arbres.get(i).ordre == j)
					{
						n+=4;
						arbrethis = this.arbres.get(i);
						this.arbres.remove(i);
					}
			try {
				switch(n) {
				case 0:
					break;
				case 1:
					this.arbres.add(retenue);
					enordre.add(retenue);
					break;
				case 2:
					this.arbres.add(arbreautre);
					enordre.add(arbreautre);
					break;
				case 3:
					retenue = retenue.fusion(arbreautre);
					break;
				case 4:
					this.arbres.add(arbrethis);
					enordre.add(arbrethis);
					break;
				case 5:
					retenue = retenue.fusion(arbrethis);
					break;
				case 6:
					retenue = arbrethis.fusion(arbreautre);
					break;
				case 7:
					retenue = retenue.fusion(arbreautre);
					this.arbres.add(arbrethis);
					enordre.add(arbrethis);
					break;

				default: break;
				}
			}
			catch(DifferentOrderTrees e){}
			
		}
		this.arbres = enordre;
		return;
	}

	public void insert(int val) {
		// A completer
		Monceau n = new Monceau();
		Node nnode = new Node(val);
		n.arbres.add(nnode);
		this.fusion(n);
	}

	public boolean delete(int val) {
		// A completer
		boolean removeEffectuee = false;
		for(int i = 0; i < this.arbres.size();i++)
		{
			if(this.arbres.get(i).findValue(val) != null)
			{
			 ArrayList<Node> arr = this.arbres.get(i).findValue(val).delete();
			 this.arbres.remove(i);
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
