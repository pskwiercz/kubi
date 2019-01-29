package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serwer {

    private static Game game = new Game();
    private ServerSocket serverSocket;

    public void start(int port) {
        int clients = 0;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ServerSocket error");
        }
        while (clients != 5) {
            try {
                new ClientHandler(serverSocket.accept(), clients++).start();
                System.out.println("Clients: " + clients);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ClientHandler error");
            }
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server stop error");
        }
    }


    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String line;
        private String login;
        private int id;


        public ClientHandler(Socket socket, int id) {
            this.clientSocket = socket;
            this.id = id;
        }

        public void run() {

            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println("POLACZONO");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO Thread error");
            }


            try {
                line = in.readLine();
                if (line.startsWith("LOGIN")) {
                    String[] message = line.split(" ");
                    login = message[1];
                    game.addPlayer(login, id);
                    out.println("Added login " + login);
                } else {
                    out.println("ERROR");
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Login error");
            }

            while(true) {
                if (game.ifGameReady()) {
                    try {
                        out.println("START " + game.getIdByLogin(login) + " 1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            game.setBoard();
            game.giveInfo();


            for (int turn = 1; turn <= 10; turn++) {
                for (int round = 1; round <= 100; round++) {

                    //waiting for turn

                    if (!game.players.get(game.getIdByLogin(login)).isEliminated()) {
                        try {
                            out.println("TWOJ RUCH");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            line = in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Read attack/pass error");
                        }

                        while (!line.equals("PASS")) {
                            if (line.startsWith("ATAK")) {
                                String[] msg = line.split(" ");
                                if (game.isAttackable(msg[1], msg[2], msg[3], msg[4])) {
                                    out.println("OK");
                                    String result;
                                    result = game.attack(msg[1], msg[2], msg[3], msg[4]);
                                    out.println(result);
                                } else {
                                    out.println("ERROR");
                                }
                            }
                        }
                    }
                }
            }

            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
