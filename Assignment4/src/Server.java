import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the main Server class of the TicTacToe game which is operated simultaneously with the client class
 * for the game process.
 */
public class Server
{
    ServerSocket serverSock;
    List<PrintWriter> players = new CopyOnWriteArrayList<PrintWriter>();
    int[] board = new int[9];
    int count_players;
    int turn, total_turns, win;
    String game_result;

    /**
     * This is the main basic constructor class for the Server class which initializes the TicTacToe board and also initializes
     * the variables that will be used during the game process for restrictions and also game results handling process.
     */
    Server()
    {
        initialize();
        turn = 1; //Initialize with player 1 starting the game first
        total_turns = 0;
        count_players = 0;  //Initialize with the number of players as 0
        game_result = "";
        win = 0;
    }

    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        server.go();
    }

    /**
     * This class creates a new ServerSocket in order to accept the sockets that were created from the client class
     * and link it together for the game process. After the clients are connected together through this Server Socket,
     * it outputs the number of the players so that it could be read in the client class, and creates new threads for the
     * players and start the run() process.
     */
    public void go() throws IOException
    {
        serverSock = new ServerSocket(50000);
        while (true)
        {
            try
            {
                Socket sock1 = serverSock.accept();
                System.out.println("First player has connected");
                PrintWriter output1 = new PrintWriter(sock1.getOutputStream(), true);
                output1.println("1");
                count_players++;

                Socket sock2 = serverSock.accept();
                System.out.println("Second player has connected");
                PrintWriter output2 = new PrintWriter(sock2.getOutputStream(), true);
                output2.println("2");

                BufferedReader input1 = new BufferedReader( new InputStreamReader(sock1.getInputStream()));
                BufferedReader input2 = new BufferedReader(new InputStreamReader(sock2.getInputStream()));
                count_players++;
                players.add(output1);
                players.add(output2);

                ClientHandler client_handler1 = new ClientHandler(sock1, input1, output1);
                ClientHandler client_handler2 = new ClientHandler(sock2, input2, output2);
                Thread client1 = new Thread(client_handler1);
                Thread client2 = new Thread(client_handler2);
                client1.start();
                client2.start();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * This is the thread class that implements Runnable so that it handles the players.
     */
    class ClientHandler implements Runnable
    {
        public Socket sock;
        public BufferedReader input;
        public PrintWriter output;

        /**
         * This is the basic constructor for the ClientHandler thread class.
         * @param sock is the parameter that accepts the players' sock and assigns it to its own created sock.
         * @param input is the parameter that accepts the players' buffered reader and assigns it to its own created reader.
         * @param output is the parameter that accepts the players' print writer and assigns it to its own created writer.
         */
        ClientHandler(Socket sock, BufferedReader input, PrintWriter output)
        {
            this.sock = sock;
            this.input = input;
            this.output = output;
        }

        /**
         * This is the runnable function which handles the server in the game process for the players to play the game.
         * It reads the clients' output and stores it in a commandLine, and sends it to the Play() function so that the
         * game could be played. After, the game_result which also contains the process of the game is outputed to all
         * players using the PrintWriter arraylist with iteration.
         */
        @Override
        public void run()
        {
            try
            {
                String commandLine;
                while ((commandLine = input.readLine()) != null)
                {
                    count_players = 2;
                    Play(commandLine);
                    for (PrintWriter broadcast : players)
                        broadcast.println(game_result);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                /**
                 * When the player is disconnected hence the player leaves during the game, it handles this case.
                 */
                if (output != null)
                {
                    players.remove(output);
                    for (PrintWriter broadcast : players)
                        broadcast.println("Game ends. The other player left.");
                }
                count_players--;
                System.out.println("player left");
            }
        }
    }

    /**
     * Function to initialize the TicTacToe grid board with all 0.
     */
    public void initialize()
    {
        for (int i = 0; i < 9; i++)
        {
            board[i] = 0;
        }
    }

    /**
     * This function toggles the other player so that the players could take turns playing the game.
     */
    public void toggle()   //function to toggle the player to switch turns during the game
    {
        if (turn == 1)
            turn = 2;
        else if (turn == 2)
            turn = 1;
    }

    /**
     * This function checks for the winner of the player or if it is a draw between two players, it indicates it is a draw
     * through the win variable.
     */
    public void isWin()
    {
        if (board[0] == board[1] && board[1] == board[2])
        {
            if (board[0] == 1)
            {
                win = 1;
            }
            if (board[0] == 2)
                win = 2;

        }
        if (board[3] == board[4] && board[4] == board[5])
        {
            if (board[3] == 1)
            {
                win = 1;
            }
            if (board[3] == 2)
                win = 2;
        }
        if (board[6] == board[7] && board[7] == board[8])
        {
            if (board[6] == 1)
            {
                win = 1;
            }
            if (board[6] == 2)
                win = 2;
        }
        if (board[0] == board[3] && board[3] == board[6])
        {
            if (board[0] == 1)
            {
                win = 1;
            }
            if (board[0] == 2)
                win = 2;
        }
        if (board[1] == board[4] && board[4] == board[7])
        {
            if (board[1] == 1)
            {
                win = 1;
            }
            if (board[1] == 2)
                win = 2;
        }
        if (board[2] == board[5] && board[5] == board[8])
        {
            if (board[2] == 1)
            {
                win = 1;
            }
            if (board[2] == 2)
                win = 2;
        }
        if (board[0] == board[4] && board[4] == board[8])
        {
            if (board[0] == 1)
            {
                win = 1;
            }
            if (board[0] == 2)
                win = 2;
        }

        if (board[2] == board[4] && board[4] == board[6])
        {
            if (board[2] == 1)
            {
                win = 1;
            }
            if (board[2] == 2)
                win = 2;
        }
        else
        {
            if (win == 0 && total_turns == 9)
            {
                win = 3; //draw
            }
        }
    }

    /**
     * This function is the main game playing function which is handled through the server. It splits the string into
     * parts and also sends the information for the board to be marked by which player to the game_result function.
     * @param players_move is the string that is accepted from the clients.
     */
    public void Play(String players_move)
    {
        String[] parts = players_move.split("-");
        int player_who = Integer.parseInt(parts[0]);        //which player placed it
        int placement = Integer.parseInt(parts[1]);         //which board
        if (player_who == turn && board[placement - 1] == 0)
        {
            board[placement - 1] = player_who;
            total_turns++;          //amount of turns the game has played
            toggle();       //switching player's turn
            game_result = game_result(player_who, placement);
        }
    }

    /**
     * This function accepts which two arguments of which player marks the board and what board the player marks.
     * With the information, it links it into a string with dashes and returns back the created string so that it could
     * be outputed to the clients.
     * @param player_who is the parameter indicating which player
     * @param placement is the parameter indicating which board
     * @return
     */
    public String game_result(int player_who, int placement)
    {
        //(Player who occupied the board)(board number)(whos turn)(winner)
        String result = "";
        result += Integer.toString(player_who) + "-" + Integer.toString(placement) + "-";
        result += Integer.toString(turn);
        isWin();
        result += "-";
        result += Integer.toString(win);

        return result;
    }
}
