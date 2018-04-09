package PolyNet;

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

        return totalCableLength;
    }
}
