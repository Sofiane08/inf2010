package PolyNet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes) {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength() {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();

        // À compléter
        
        //
        // ajoute le premier node si il existe
        //
        if(nodes.length != 0)
        {
        	visitedNodes.add(nodes[0]);
        	ArrayList<PolyNetConnection> voisins = nodes[0].getConnections();
        	for(int i = 0; i < voisins.size(); i++)
        	{
        		knownConnections.add(voisins.get(i));
        	}
        }
        
        
        
        
        //
        // pour le nombre restant de node, ajouter celui au bout de la connection
        // la plus courte s'il n'est pas déjà visité.
        //
        for(int i = 0; i < nodes.length -1;)
        {
        	PolyNetConnection least = knownConnections.poll();
        	if (!visitedNodes.contains(least.getConnectedNode()))
        	{
        		visitedNodes.add(least.getConnectedNode());
        		totalCableLength += least.getDistance();
        		ArrayList<PolyNetConnection> voisins = least.getConnectedNode().getConnections();
        		for(int j = 0; j < voisins.size(); j++)
        		{
        		knownConnections.add(voisins.get(j));
        		}
        		i++;
        	}
        	
        }

        return totalCableLength;
    }
}
