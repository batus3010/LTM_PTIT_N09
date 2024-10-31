package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import run.ServerRun;

public class Server {

    private final int port;
    private final ThreadPoolExecutor executor;
    private ServerSocket serverSocket;
    private boolean isShutDown = false;
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    
    public Server(int port, int corePoolSize, int maxPoolSize, int queueCapacity) {
        this.port = port;

        // Initialize thread pool
        this.executor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity)
        );
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started at port " + port);

            ServerRun.clientManager = new ClientManager();
            ServerRun.roomManager = new RoomManager();

            // Main server loop
            while (!isShutDown) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("+ New Client connected: " + clientSocket);

                    Client client = new Client(clientSocket);
                    ServerRun.clientManager.add(client);
                    System.out.println("Count of client online: " + ServerRun.clientManager.getSize());

                    // Handle client connection
                    executor.execute(client);
                } catch (IOException e) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Client connection error", e);
                    isShutDown = true;
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Server start failed", e);
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        System.out.println("Shutting down server...");
        isShutDown = true;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Failed to close server socket", e);
        }
        executor.shutdownNow();
        System.out.println("Server shutdown complete.");
    }
}
