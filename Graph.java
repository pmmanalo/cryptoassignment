
import java.util.*;

public class Graph {

    /* inner class Edge. Do not modify this class. */
    class Edge {
        public final String s;
        public final String t;
        public final Double weight;

        public Edge(String s, String t, Double w) {
            this.s = s;
            this.t = t;
            this.weight = w;
        }

        public String toString() {
            return s + "->" + t + ":" + weight;
        }
    }

    /* instance variable for Graph class */
    private final HashMap<String, List<Edge>> adjacencyList;

    /**
     * Graph constructor that initializes instance variables and builds the adjacencyList
     *
     * @param HashMap<String, Double> "Base Asset, Quote Asset" and total trade counts data
     */
    public Graph(HashMap<String, Double> counts) {
        adjacencyList = new HashMap<>();
        for (String toFrom : counts.keySet()) {
            Double tradeCounts = counts.get(toFrom);
            String to = toFrom.split(",")[0];
            String from = toFrom.split(",")[1];

            if (!adjacencyList.containsKey(to)) {
                adjacencyList.put(to, new ArrayList<>());
            }

            adjacencyList.get(to).add(new Edge(to, from, tradeCounts));

        }
    }

    /**
     * findFlows takes the source cryptocurrency and finds a shortest path from the source
     * to all reachable cryptocurrencies, and calculate the net trades count over the shortest path.
     *
     * @param String the source cryptocurrency name.
     * @returns (other cryptocurrency, the net trades count) pairs.
     */
    public Map<String, Double> findFlows(String source) {

        HashMap<String, Double> netTrades=new HashMap<>();
        Queue<Edge> queue=new LinkedList<>();
        for (Edge e : adjacencyList.get(source)) {
            queue.add(e);
            netTrades.put(e.t,e.weight);
        }

        while (!queue.isEmpty()){
            Edge edge=queue.poll();

            if(adjacencyList.get(edge.t)!=null){
                for (Edge e : adjacencyList.get(edge.t)) {
                    if(!netTrades.containsKey(e.t)){
                        queue.add(e);
                        netTrades.put(e.t,Math.min(e.weight,netTrades.get(edge.t)));
                    }
                }
            }
        }


        return netTrades;
    }

    /**
     * sortFlows takes the (cryptocurrency, net trades count) pairs and sort them
     * in the descending order of the net trades count. Returns the sorted list as
     * List of Strings, where each String is "cryptocurrency: net trades count" format.
     *
     * @param (cryptocurrency,net trades count) pairs
     * @returns List of cryptocurrency: net trades count Strings.
     */
    public List<String> sortFlows(Map<String, Double> flows) {

        List<String> list=new ArrayList<>();
        for (String s : flows.keySet()) {
            list.add(s+": "+String.format("%.0f",flows.get(s)));
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Double w1=Double.parseDouble(o1.split(":")[1].trim());
                Double w2=Double.parseDouble(o2.split(":")[1].trim());
                return Double.compare(w2,w1);
            }
        });

        return list;
    }

}
