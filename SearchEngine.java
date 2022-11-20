import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> groceries = new ArrayList<String>();
    
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("List: %s", groceries);
        } else if (url.getPath().equals("/search")) {
            String[] parameters1 = url.getQuery().split("=");
            if(parameters1[0].equals("a")){
                for(int i = 0; i<groceries.size(); i++){
                    if(groceries.get(i).contains(parameters1[1])){
                        
                        return String.format("Result: %s", groceries.get(i));
                    }
                    else{
                        return String.format("Not found");
                    }
                }
                }
            
            //num += 1;
            //return String.format("Searched");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters2 = url.getQuery().split("=");
                //arraylist contains for loop 
                if (parameters2[0].equals("s")) {
                    
                    groceries.add(parameters2[1]);
                    return String.format("%s has been added", parameters2[1]);
        
                }
            }
//            return "404 Not Found!";
        }
        return "404 Not Found!";
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
}