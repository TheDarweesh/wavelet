import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        ArrayList<String> groceries = new ArrayList<String>();
        
        if (url.getPath().equals("/")) {
            return String.format("Groceries: %s", groceries);
        } 
        else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
            ArrayList<String> results = new ArrayList<String>();
            for(int i = 0; i < groceries.size(); i++){
                if(groceries.get(i).contains(parameters[1])){
                    results.add(groceries.get(i));
                    i++;
                }
                return String.format("Results: %s", results);
            }
            //return String.format("Not found");
        } 
        return String.format("Not found");
    }
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    groceries.add(parameters[1]);
                    //return String.format("%s has been added.", parameters[1]);
                    return groceries.toString();
                }
            }
            return "404 Not Found!";
        }
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
