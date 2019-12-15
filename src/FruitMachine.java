import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.util.Random;

public class FruitMachine extends JFrame {
    public int balance = 100;
    JButton newGameButton;
    JButton spinButton;


    public FruitMachine() {

        int unit = 2;
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        Font labelText = new Font("Helvetica", Font.PLAIN, 16);
        Font cardText = new Font ("Arial", Font.PLAIN, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300 * unit,200 * unit);
        setLocation(150 * unit,100 * unit);
        setTitle("Fruitmachine GUI");

        //Initialisation of outer and inner panels (both needed to ensure padding is implemented)
        JPanel outerPanel = new JPanel(new FlowLayout());
        JPanel innerPanel = new JPanel(new GridLayout(2, 2));

//        innerPanel.setPreferredSize(new Dimension (260 * unit, 160 * unit));


        // Below code deals with padding (1 unit padding in North/South/East/West)
        // If it is not padded, borderLayout.CENTER will cover the entire panel area
        JPanel westPadding = new JPanel();
        JPanel northPadding = new JPanel();
        JPanel southPadding = new JPanel();
        JPanel eastPadding = new JPanel();

        westPadding.setPreferredSize(new Dimension (20 * unit, 300 * unit));
        eastPadding.setPreferredSize(new Dimension (20 * unit, 300 * unit));
        northPadding.setPreferredSize(new Dimension (200 * unit, 20 * unit));
        southPadding.setPreferredSize(new Dimension (200 * unit, 20 * unit));


        // Builds the four quadrants into innerPanel
        JPanel topLeftPanel = new JPanel();
        JPanel topRightPanel = new JPanel();
        JPanel bottomLeftPanel = new JPanel();
        JPanel bottomRightPanel = new JPanel();

        JLabel pointsBalance = new JLabel("Balance is " + balance);
        JLabel messageLabel = new JLabel("Welcome!");
        JLabel winLossLabel = new JLabel("");

        pointsBalance.setFont(labelText);
        messageLabel.setFont(labelText);
        winLossLabel.setFont(labelText);


        this.add(outerPanel);
        this.add(northPadding, BorderLayout.NORTH);
        this.add(westPadding, BorderLayout.WEST);
        this.add(eastPadding, BorderLayout.EAST);
        this.add(southPadding, BorderLayout.SOUTH);
        this.add(innerPanel, BorderLayout.CENTER);


        innerPanel.add(topLeftPanel);
        innerPanel.add(topRightPanel);
        innerPanel.add(bottomLeftPanel);
        innerPanel.add(bottomRightPanel);

        // Building top left panel
        topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.PAGE_AXIS));
        topLeftPanel.add(pointsBalance);
        topLeftPanel.add(Box.createRigidArea(new Dimension(0, 10 * unit)));
        topLeftPanel.add(messageLabel);
        topLeftPanel.add(Box.createRigidArea(new Dimension(0, 10 * unit)));
        topLeftPanel.add(winLossLabel);


        // Building bottom left panel (top right panel should be empty)
        bottomLeftPanel.setLayout(new BoxLayout(bottomLeftPanel, BoxLayout.PAGE_AXIS));
        JPanel cardContainer = new JPanel();

        cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.LINE_AXIS));
        bottomLeftPanel.add(Box.createRigidArea(new Dimension(0, 10 * unit)));
        bottomLeftPanel.add(cardContainer);
        bottomLeftPanel.add(Box.createRigidArea(new Dimension(0, 10 * unit)));

        // Build card panels
        JPanel card1 = new JPanel(new BorderLayout());
        card1.setBackground(Color.yellow);
        card1.setBorder(blackline);
        JPanel card2 = new JPanel(new BorderLayout());
        card2.setBackground(Color.yellow);
        card2.setBorder(blackline);
        JPanel card3 = new JPanel(new BorderLayout());
        card3.setBackground(Color.yellow);
        card3.setBorder(blackline);

        // Build card labels
        JLabel cardLabel1 = new JLabel("King", SwingConstants.CENTER);
        JLabel cardLabel2 = new JLabel("Queen", SwingConstants.CENTER);
        JLabel cardLabel3 = new JLabel("Jack", SwingConstants.CENTER);

        // set label sizes
        cardLabel1.setPreferredSize(new Dimension(35 * unit, 60 * unit));
        cardLabel2.setPreferredSize(new Dimension(35 * unit, 60 * unit));
        cardLabel3.setPreferredSize(new Dimension(35 * unit, 60 * unit));

        // set label fonts
        cardLabel1.setFont(cardText);
        cardLabel2.setFont(cardText);
        cardLabel3.setFont(cardText);

        // Add card labels to card panels
        card1.add(cardLabel1, BorderLayout.CENTER);
        card2.add(cardLabel2, BorderLayout.CENTER);
        card3.add(cardLabel3, BorderLayout.CENTER);

        // Build cardContainer with appropriate gaps (using createRigidArea)
        cardContainer.add(card1);
        cardContainer.add(Box.createRigidArea(new Dimension(10 * unit, 0)));
        cardContainer.add(card2);
        cardContainer.add(Box.createRigidArea(new Dimension(10 * unit, 0)));
        cardContainer.add(card3);


        //Building bottom right panel
        bottomRightPanel.setLayout(new BoxLayout(bottomRightPanel, BoxLayout.PAGE_AXIS));
        JPanel buttonContainer = new JPanel();
        JButton spinButton = new JButton("Spin");
        JButton newGameButton = new JButton("New Game");
        spinButton.setMaximumSize(new Dimension (70 * unit, 20 * unit));
        spinButton.setMinimumSize(new Dimension (70 * unit, 20 * unit));



        newGameButton.setMaximumSize(new Dimension (70 * unit, 20 * unit));
        newGameButton.setMinimumSize(new Dimension (70 * unit, 20 * unit));
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.PAGE_AXIS));
        buttonContainer.add(Box.createRigidArea(new Dimension(20 * unit, 0)));
        buttonContainer.add(spinButton);
        buttonContainer.add(newGameButton);
        newGameButton.setEnabled(false);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    balance = 100;
                    pointsBalance.setText("Balance is " + balance);
                    messageLabel.setText("welcome!");
                    winLossLabel.setText("");   //Win/Loss message label is blank by default
                    cardLabel1.setText("Jack");
                    cardLabel2.setText("Queen");
                    cardLabel3.setText("King");
                    newGameButton.setEnabled(false);
                    spinButton.setEnabled(true);

            }
        });

        spinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLabel1.setText(getCard());
                cardLabel2.setText(getCard());
                cardLabel3.setText(getCard());
                String[] cardArray = {cardLabel1.getText(), cardLabel2.getText(), cardLabel3.getText()};
                int newPoints = getPoints(cardArray);
                if (newPoints == 0){
                    messageLabel.setText("Three different cards - no change");
                }
                if (newPoints == 20){
                    messageLabel.setText("Two of a kind - you win 20 points");
                } else if (newPoints == 50) {
                    messageLabel.setText("Three of a kind - you win 50 points");
                }
                else if (newPoints == -25){
                    messageLabel.setText("One Joker - you lose 25 points");
                }
                else if (newPoints == -50){
                    messageLabel.setText("Two Jokers - you lose 50 points");
                }
                else if (newPoints == -75){
                    messageLabel.setText("Three Jokers - you lose 75 points");
                }
                balance += newPoints;
                pointsBalance.setText("Balance is " + balance);

                if (balance < 0){
                    winLossLabel.setText("You lose!");
                    newGameButton.setEnabled(true);
                    spinButton.setEnabled(false);
                }
                else if(balance > 150){
                    winLossLabel.setText("You win!");
                    newGameButton.setEnabled(true);
                    spinButton.setEnabled(false);
                }


            }
        });

        bottomRightPanel.add(Box.createRigidArea(new Dimension(0, 20 * unit)));
        bottomRightPanel.add(buttonContainer);
        bottomRightPanel.add(Box.createRigidArea(new Dimension(0, 20 * unit)));


    }


// Get random
    public String getCard(){
        String[] cardArray = {"Jack", "Queen", "King", "Ace", "Joker"};
        Random random = new Random();
        int selectCard = random.nextInt(cardArray.length);
        return cardArray[selectCard];
    }

// Assign points
    public int getPoints(String[] cards){
        int jokercount = 0;
        int[] countArray = {0,0,0,0}; // this array represents the count of {Jack, Queen, King, Ace} respectively

        int points = 0;
        for(int i=0; i<cards.length; i++){
            if (cards[i] == "Joker"){
                jokercount++;
            }
            else if (cards[i] == "Jack"){
                countArray[0]++;
            }
            else if (cards[i] == "Queen"){
                countArray[1]++;
            }
            else if (cards[i] == "King"){
                countArray[2]++;
            }
            else if (cards[i] == "Ace"){
                countArray[3]++;
            }
        }
        if (jokercount > 0){
            points = jokercount * -25;
            return points;
        }
        for(int i=0; i<countArray.length; i++){
            if (countArray[i] == 2){
                points += 20;
                return points;
            }
            else if (countArray[i] == 3){
                points += 50;
                return points;
            }
        }

        return points;



    }


    public static void main(String[] args)
    {
        FruitMachine frame = new FruitMachine();
        frame.setVisible(true);
    }


}
