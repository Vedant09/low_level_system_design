/* 
*****Disclaimer*****
The code is not an implementation of consistent hashing. 
The code is just a simulation of a simple load balancing scenario where requests are distributed among a fixed number of nodes, 
and new nodes are created when the existing nodes have served a certain number of requests.
Consistent hashing is a specific hashing technique used in distributed systems for load balancing. 
It involves mapping keys to a circle (often represented as a ring) and assigning nodes to locations on that circle. 
Requests are then directed to the node whose location on the circle is closest to the hash value of the request key.
*/
public class Pair {
    private String name;
    private int requestCount;

    public Pair(String name, int requestCount) {
        this.name = name;
        this.requestCount = requestCount;
    }

    public String getName() {
        return name;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void serveRequest(String request, boolean acceptRequest) {
        if (!acceptRequest) {
            System.out.println("System is not accepting new requests. All nodes are full.");
            return; // or throw an exception, depending on your design
        }

        System.out.println("Request '" + request + "' is being served by " + getName());
        requestCount++;

        if (requestCount == 3) {
            System.out.println(getName() + " has served 3 requests. Resetting request count.");
            requestCount = 0;
        }
    }
}
