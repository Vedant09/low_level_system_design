import java.util.*;

/* 
*****Disclaimer*****
The code is not an implementation of consistent hashing. 
The code is just a simulation of a simple load balancing scenario where requests are distributed among a fixed number of nodes, 
and new nodes are created when the existing nodes have served a certain number of requests.
Consistent hashing is a specific hashing technique used in distributed systems for load balancing. 
It involves mapping keys to a circle (often represented as a ring) and assigning nodes to locations on that circle. 
Requests are then directed to the node whose location on the circle is closest to the hash value of the request key.
*/
public class Request {

    private List<Pair> nodes = new ArrayList<>();
    private int requestCounter = 0;
    private boolean acceptRequest = true;

    public Request() {
        for (int i = 1; i <= 3; i++) {
            nodes.add(new Pair("Node " + i, 0));
        }
    }

    public void handleRequest(String request) {
        int nodeIndex = requestCounter % nodes.size();
        Pair selectedNode = nodes.get(nodeIndex);
        selectedNode.serveRequest(request, acceptRequest);

        requestCounter++;

        if (requestCounter % (nodes.size() * 3) == 0 && nodes.size() < 5) {
            System.out.println("All nodes have served 3 requests. Creating a new node.");
            createNewNode();
        }
    }

    public static void main(String[] args) {
        Request requestManager = new Request();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter requests (type 'exit' to end):");
        int input = 0;
        String userInput;
        do {
            System.out.print("Request: ");
            userInput = scanner.nextLine();

            if (!userInput.equalsIgnoreCase("exit")) {
                input++;
                requestManager.handleRequest(userInput);
            }
            //limiting to 60 requests.
            if(input>60){
                System.out.print("Cannot take anymore requests");
                System.exit(0);
            }

        } while (!userInput.equalsIgnoreCase("exit"));

        scanner.close();
    }

    private void createNewNode() {
        Pair newNode = new Pair("Node " + (nodes.size() + 1), 0);
        nodes.add(newNode);
        System.out.println("New node created: " + newNode.getName());
    }
}

