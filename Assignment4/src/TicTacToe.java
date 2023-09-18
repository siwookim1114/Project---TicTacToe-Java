import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

/**
 * The client class of TicTacToe
 * Java Assignment 4
 * @version 1.0
 */
public class TicTacToe
{
    JFrame game;
    JPanel game_panel;
    JTextField enter_name;
    JButton submit, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    JLabel information;
    JMenuBar menubar;
    JMenu control, help;
    JMenuItem exit, instructions;
    int player_turn, whichboard, playerorder, whichplayer,whowon;
    String player_name, player_move;

    /**
     * The basic constructor class for the TicTacToe class
     * It sets up the JFrame and the GUI components including the panels, buttons, menubars, and more.
     * The variables that are used inside the game for restrictions and processing are also initialized in the constructor.
     */
    TicTacToe()
    {
        game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menubar = new JMenuBar();
        control = new JMenu("Control");
        help = new JMenu("help");

        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            /**
             * This is the action listener function for the exit button.
             * When pressed, the system exits.
             * @param e is the action event handler that handles this function.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        instructions = new JMenuItem("Instructions");
        instructions.addActionListener(new ActionListener() {
            /**
             * This is the instructions function for the instructions button.
             * When pressed, the information dialogue pops out explaining the game rules and some information
             * about the game.
             * @param e is the action event handler that handles this function.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(game, "Some information about the game:\nCriteria for a valid move:\n-The move is not occupied by any mark.\n-The move is made in the player's turn.\nThe move is made within the 3 x 3 board.\nThe game would continue and switch among the opposite player until it reaches either one of the following conditions:\n-Player 1 wins.\n-Player 2 wins.\n-Draw.");
            }
        });
        control.add(exit);
        help.add(instructions);
        menubar.add(control);
        menubar.add(help);

        JPanel main_panel = new JPanel();

        //top panel
        information = new JLabel("Enter your player name...");
        information.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        JPanel top_panel = new JPanel();
        top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.X_AXIS));
        top_panel.add(information);

        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.add(top_panel);

        //middle panel
        game_panel = new JPanel();
        game_panel.setLayout(new GridLayout(3, 3));
        btn_1 = new JButton();
        btn_2 = new JButton();
        btn_3 = new JButton();
        btn_4 = new JButton();
        btn_5 = new JButton();
        btn_6 = new JButton();
        btn_7 = new JButton();
        btn_8 = new JButton();
        btn_9 = new JButton();

        btn_1.setEnabled(false);
        btn_2.setEnabled(false);
        btn_3.setEnabled(false);
        btn_4.setEnabled(false);
        btn_5.setEnabled(false);
        btn_6.setEnabled(false);
        btn_7.setEnabled(false);
        btn_8.setEnabled(false);
        btn_9.setEnabled(false);

        game_panel.add(btn_1);
        game_panel.add(btn_2);
        game_panel.add(btn_3);
        game_panel.add(btn_4);
        game_panel.add(btn_5);
        game_panel.add(btn_6);
        game_panel.add(btn_7);
        game_panel.add(btn_8);
        game_panel.add(btn_9);
        main_panel.add(game_panel);

        //bottom panel
        JPanel bottom_panel = new JPanel();
        enter_name = new JTextField(20);
        submit = new JButton("Submit");
        bottom_panel.add(enter_name);
        bottom_panel.add(submit);
        main_panel.add(bottom_panel);

        //frame
        game.setVisible(true);
        game.setJMenuBar(menubar);
        game.setTitle("Tic Tac Toe");
        game.setSize(500,500);
        game.setResizable(false);
        game.getContentPane().add(main_panel);

        playerorder = 1;
        whowon = 0;
    }

    /**
     * This function gets the buffered line from the server hence marks the player 1 and player 2's valid moves
     * on the board. The line that is received from the server is divided into 4 parts using the split() method.
     * The first part indicates which player marked the board,
     * The second part indicates which board is marked by the player,
     * The third part indicates the order of the player hence the next player who should mark the board
     * The last part indicates who won the game
     * @param line
     */
    void markBoard(String line)
    {
        System.out.println("this is the line received: " + line);
        String[] parts = line.split("-");
        whichplayer = Integer.parseInt(parts[0]);
        whichboard = Integer.parseInt(parts[1]);
        playerorder = Integer.parseInt(parts[2]);
        whowon = Integer.parseInt(parts[3]);

        if (whichplayer == 1)
        {
            if (whichboard == 1)
            {
                btn_1.setFont(new Font("Arial", Font.BOLD, 30));
                btn_1.setText("X");
                btn_1.setForeground(Color.RED);
                btn_1.setEnabled(false);
            }
            else if (whichboard == 2)
            {
                btn_2.setFont(new Font("Arial", Font.BOLD, 30));
                btn_2.setText("X");
                btn_2.setForeground(Color.RED);
                btn_2.setEnabled(false);
            }
            else if (whichboard == 3)
            {
                btn_3.setFont(new Font("Arial", Font.BOLD, 30));
                btn_3.setText("X");
                btn_3.setForeground(Color.RED);
                btn_3.setEnabled(false);
            }
            else if (whichboard == 4)
            {
                btn_4.setFont(new Font("Arial", Font.BOLD, 30));
                btn_4.setText("X");
                btn_4.setForeground(Color.RED);
                btn_4.setEnabled(false);
            }
            else if (whichboard == 5)
            {
                btn_5.setFont(new Font("Arial", Font.BOLD, 30));
                btn_5.setText("X");
                btn_5.setForeground(Color.RED);
                btn_5.setEnabled(false);
            }
            else if (whichboard == 6)
            {
                btn_6.setFont(new Font("Arial", Font.BOLD, 30));
                btn_6.setText("X");
                btn_6.setForeground(Color.RED);
                btn_6.setEnabled(false);
            }
            else if (whichboard == 7)
            {
                btn_7.setFont(new Font("Arial", Font.BOLD, 30));
                btn_7.setText("X");
                btn_7.setForeground(Color.RED);
                btn_7.setEnabled(false);
            }
            else if (whichboard == 8)
            {
                btn_8.setFont(new Font("Arial", Font.BOLD, 30));
                btn_8.setText("X");
                btn_8.setForeground(Color.RED);
                btn_8.setEnabled(false);
            }
            else if (whichboard == 9)
            {
                btn_9.setFont(new Font("Arial", Font.BOLD, 30));
                btn_9.setText("X");
                btn_9.setForeground(Color.RED);
                btn_9.setEnabled(false);
            }
        }
        else
        {
            if (whichboard == 1) {
                btn_1.setFont(new Font("Arial", Font.BOLD, 30));
                btn_1.setText("O");
                btn_1.setForeground(Color.GREEN);
                btn_1.setEnabled(false);
            } else if (whichboard == 2) {
                btn_2.setFont(new Font("Arial", Font.BOLD, 30));
                btn_2.setText("O");
                btn_2.setForeground(Color.GREEN);
                btn_2.setEnabled(false);
            } else if (whichboard == 3) {
                btn_3.setFont(new Font("Arial", Font.BOLD, 30));
                btn_3.setText("O");
                btn_3.setForeground(Color.GREEN);
                btn_3.setEnabled(false);
            } else if (whichboard == 4) {
                btn_4.setFont(new Font("Arial", Font.BOLD, 30));
                btn_4.setText("O");
                btn_4.setForeground(Color.GREEN);
                btn_4.setEnabled(false);
            } else if (whichboard == 5) {
                btn_5.setFont(new Font("Arial", Font.BOLD, 30));
                btn_5.setText("O");
                btn_5.setForeground(Color.GREEN);
                btn_5.setEnabled(false);
            } else if (whichboard == 6) {
                btn_6.setFont(new Font("Arial", Font.BOLD, 30));
                btn_6.setText("O");
                btn_6.setForeground(Color.GREEN);
                btn_6.setEnabled(false);
            } else if (whichboard == 7) {
                btn_7.setFont(new Font("Arial", Font.BOLD, 30));
                btn_7.setText("O");
                btn_7.setForeground(Color.GREEN);
                btn_7.setEnabled(false);
            } else if (whichboard == 8) {
                btn_8.setFont(new Font("Arial", Font.BOLD, 30));
                btn_8.setText("O");
                btn_8.setForeground(Color.GREEN);
                btn_8.setEnabled(false);
            } else if (whichboard == 9) {
                btn_9.setFont(new Font("Arial", Font.BOLD, 30));
                btn_9.setText("O");
                btn_9.setForeground(Color.GREEN);
                btn_9.setEnabled(false);
            }
        }

    }


    public static void main(String[] args)
    {
        TicTacToe TTT= new TicTacToe();
        Players players = new Players(TTT);
        players.start();
    }

    /**
     * This is the Players class that handles the players of the game so that it connects to the server.
     */
    public static class Players
    {
        public Socket socket;
        public BufferedReader reader_in;
        public PrintWriter writer_out;
        public TicTacToe t;

        /**
         * This is the basic constructor for the Player class. It takes the TicTacToe class as the parameter
         * for doing operations inside the server.
         * @param game
         */
        Players(TicTacToe game)
        {
            t = game;
        }

        /**
         * This is the start() method which makes sockets of the players to connect to the Server. The reader and the
         * output writer for the players are also initialized and created here. The reader and the writer are passed on
         * to the client handler thread for communication.
         */
        public void start()
        {
            try
            {
                socket = new Socket("127.0.0.1", 50000);
                reader_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer_out = new PrintWriter(socket.getOutputStream(), true);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            Client_Handler player = new Client_Handler(socket);
            Thread player_handler = new Thread(player);

            player_handler.start();
        }

        /**
         * This is the client handler thread which implements runnable to do threading.
         */
        class Client_Handler implements Runnable
        {
            public Socket socket;

            /**
             * This is the basic constructor for the Client_Handler thread class. It accepts the socket that was created
             * and passed on as the parameter with the thread socket together connected.
             * @param socket
             */
            Client_Handler(Socket socket)
            {
                this.socket = socket;
            }

            /**
             * This run method accepts the number order of Player 1 and Player 2 by the server hence assigning
             * the clients (players) the numbers. It then calls the functions of ServerInfoSet() and readServer() which
             * acts as the main functions of writing and reading from the server.
             */
            @Override
            public void run()
            {
                try
                {
                    t.player_turn = Integer.parseInt(reader_in.readLine());
                    ServerInfoSet();
                    readServer();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        /**
         * This function is the main writing function that sends the information to the server.
         */
        public void ServerInfoSet() throws Exception
        {
            try
            {
                /**
                 * The submit button's action listener is created here so that when the submit button is clicked,
                 * the frame's title and also the label's title as well as the buttons will be enabled.
                 */
                t.submit.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (!t.enter_name.getText().equals(""))
                        {
                            t.player_move = "";
                            t.player_name = t.enter_name.getText();
                            t.information.setText("Welcome, " + t.player_name + "!");
                            t.game.setTitle("Tic Tac Toe - Player: " + t.player_name);
                            t.enter_name.setEditable(false);
                            t.submit.setEnabled(false);

                            t.btn_1.setEnabled(true);
                            t.btn_2.setEnabled(true);
                            t.btn_3.setEnabled(true);
                            t.btn_4.setEnabled(true);
                            t.btn_5.setEnabled(true);
                            t.btn_6.setEnabled(true);
                            t.btn_7.setEnabled(true);
                            t.btn_8.setEnabled(true);
                            t.btn_9.setEnabled(true);
                        }
                    }
                });
                ServerMakeMove();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        /**
         * This function is the main writing function that sends the information of which player marked which board
         * to the server. If it is the appropriate player's order, then the player will be able to
         * press one of the grid buttons and send the information to the server. In order to send it to the server,
         * the operation is handled with the data type of String.
         */
        public void ServerMakeMove() throws Exception
        {
            try
            {
                t.player_move = "";
                if (t.playerorder == t.player_turn)
                {
                    t.btn_1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-1";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-2";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-3";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-4";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_5.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-5";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_6.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-6";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_7.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-7";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_8.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-8";
                            writer_out.println(t.player_move);
                        }
                    });
                    t.btn_9.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t.player_move += Integer.toString(t.player_turn);
                            t.player_move += "-9";
                            writer_out.println(t.player_move);
                        }
                    });
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        /**
         * This is the main read function that reads all the information which is sent from the Server. It uses the
         * commandline which is the string that will be accepted by the server and stored into the commandLine variable.
         * Through the server output, the game results will be displayed as long as cases where the player leaves
         * during the middle of the game, and also the case when the player is able to mark the board or need to wait.
         *
         * If the commandLine sent from the server is series of string numbers in between with dashes, it is the command
         * or the coordinates for the board to be marked which is simultaneously passed on as a parameter to the markboard
         * function for mark, and ServerMakeMove() function is called again for the opponent to mark a board after receiving
         * the results of one board being marked.
         */
        public void readServer() throws Exception
        {
            try
            {
                String commandLine;
                while ((commandLine = reader_in.readLine()) != null)
                {
                    if (commandLine.equals("Game ends. The other player left."))
                    {
                        JOptionPane.showMessageDialog(t.game, "Game Ends. One of the players left.");
                        System.exit(0);
                    }
                    else
                    {
                        t.markBoard(commandLine);
                        if (t.player_turn == t.playerorder)
                        {
                            t.information.setText("Your opponent has moved, now is your turn");
                            ServerMakeMove();
                        }
                        else
                            t.information.setText("Valid move, wait for your opponent.");
                    }
                    if (t.whowon != 0)
                    {
                       if (t.whowon == 3)
                           JOptionPane.showMessageDialog(t.game, "Draw.");
                       if (t.whowon == t.player_turn)
                           JOptionPane.showMessageDialog(t.game, "Congratulations. You Win.");
                       if (t.whowon != t.player_turn && t.whowon != 3)
                           JOptionPane.showMessageDialog(t.game, "You lose.");

                       System.exit(0);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                socket.close();
            }
        }
    }
}