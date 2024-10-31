package run;


import service.Client;
import service.ClientManager;
import service.RoomManager;
import service.Server;
import view.ServerView;

public class ServerRun {

    public static volatile ClientManager clientManager;
    public static volatile RoomManager roomManager;
    public static boolean isShutDown = false;

    public static void main(String[] args) {
        ServerView serverView = new ServerView();
        serverView.setVisible(true);
        serverView.setLocationRelativeTo(null);
        
        // Start the server
        Server server = new Server(2000, 10, 100, 8);
        server.start();
    }
}
