package view;

import java.io.IOException;

public interface ConsoleInterface {

    /**
     * Invokes the cmd instance the program is running in and
     * tells it to execute the "/c cls" command, using <code>ProcessBuilder</code> to directly
     * connect the its output channel to the Java process’ output channel,
     * which works starting with Java 7, using <code>inheritIO()</code>.
     *
     * @throws IOException If an input or output exception occurred.
     * @throws InterruptedException if the process thread is interrupted while sleeping, waiting or otherwise occupied.
     */
     static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();// clears screen, will not clear it inside an IDE
     }

     static void printOnConsole(String message){
        System.out.println(message);
     }

}
