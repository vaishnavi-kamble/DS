package webservice;
// Simple REST Web Service Program

import com.sun.net.httpserver.*;   // For creating HTTP server
import java.io.*;                  // For input/output operations
import java.net.*;                 // For networking classes

public class WebServiceCode {

    public static void main(String[] args) throws Exception {

        // Create HTTP server on port 8000
        HttpServer server =
                HttpServer.create(new InetSocketAddress(8000), 0);
        	//It tells: WHERE the server should run
        // Create web service endpoint "/add"
        server.createContext("/add", (exchange) -> {

            // Read query from URL
            // Example: ?a=10&b=20
            String query = exchange.getRequestURI().getQuery();

            // Split parameters using &
            String[] params = query.split("&");

            // Extract value of a
            int a = Integer.parseInt(
                    params[0].split("=")[1]);

            // Extract value of b
            int b = Integer.parseInt(
                    params[1].split("=")[1]);

            // Perform addition
            int result = a + b;

            // Prepare response message
            String response = "Result = " + result;

            // Send HTTP success response (200)
            exchange.sendResponseHeaders(200,
                    response.length());

            // Send response to browser/client
            //line gets the output stream used to send data (response) back to the client/browser.
            OutputStream os =
                    exchange.getResponseBody();
            //sends the response data to the browser
            os.write(response.getBytes());

            // Close output stream
            os.close();
        });

        // Start the web server
        server.start();

        // Display server running message
        System.out.println(
                "Server running at http://localhost:8000");
    }
}
//for output type this in the browser
//http://localhost:8000/add?a=10&b=20