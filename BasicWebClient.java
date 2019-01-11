import java.io.*;
import java.lang.String;
import java.net.*;

public class BasicWebClient {

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 8000;

        // Step 1: Create a socket that connects to the above host and port number
        // Details: Write the data to the output stream. Read the input stream to receive data.
        try (
                Socket socket = new Socket(hostName, portNumber);

                // Step 2: Create a PrintWriter from the socket's output stream
                //         Use the autoFlush options
                OutputStream output = socket.getOutputStream();
                PrintWriter pr = new PrintWriter(output, true);

                // Step 3: Create a BufferedReader from the socket's input stream

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        ) {
            // Step 4: Send an HTTP GET request via the PrintWriter.
            //         Remember to print the necessary blank line
            System.out.println("Please input a file: ");
            String infile = stdIn.readLine();
            pr.println("GET " + infile + " HTTP/1.1\r\n");
//            pr.println("HOST: " + hostName + " " + portNumber + "\n");

            // Step 5a: Read the status line of the response
            // Step 5b: Read the HTTP response via the BufferedReader until
            //         you get a blank line

            String words;
            words = reader.readLine();
            while ((!words.equals(""))) {
                //pr.println(words);
                System.out.println(words);
                words = reader.readLine();
            }

            // Step 6a: Create a FileOutputStream for storing the payload
            // Step 6b: Wrap the FileOutputStream in another PrintWriter

            //File target = new File("/payload.txt");
            FileOutputStream FOS = new FileOutputStream("payload.txt");
            PrintWriter fps = new PrintWriter(FOS, false);

            // Step 7: Read the rest of the input from BufferedReader and write
            //         it to the second PrintWriter.
            //         Hint: readLine() returns null when there is no more data
            //         to read

            words = reader.readLine();
            while (words != null) {
                System.out.println(words);
                fps.append(words + "\n");
                words = reader.readLine();
                //fps.println(words); //reader
            }

            // Step 8: Remember to close the writer
            pr.close();
            fps.close();
            reader.close();


    }catch(IOException I) {
        System.out.println("Caught. Error creating socket");
    }
 }
}
