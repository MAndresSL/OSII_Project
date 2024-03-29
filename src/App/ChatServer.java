/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author xioma
 */
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

public class ChatServer {

    static Middleware server;

    public static void main(String[] argv) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Nombre: ");
            String name = s.nextLine().trim();

            server = new Middleware();
            Registry registry = LocateRegistry.createRegistry(8888);
            registry.bind("FileSystem", server);

            while (true) {
                String msg = s.nextLine().trim();
                if (server.getClient() != null) {

                    ArrayList<FileSystemInterface> client = server.getClient();
                    Iterator<FileSystemInterface> ic = client.iterator();
                    while (ic.hasNext()) {
                        ic.next().send("[" + server.getName() + "] " + msg);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}
