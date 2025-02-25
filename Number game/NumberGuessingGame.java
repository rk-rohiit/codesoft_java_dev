import javax.swing.*;
import java.awt.*;


class RandNo {
    public int generate(int max, int min) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}

public class NumberGuessingGame extends JFrame {
    private JTextField minField, maxField, guessField;
    private JLabel titleLabel, messageLabel, attemptsLabel, rangeLabel;
    private JButton startButton, guessButton, resetButton;

    private RandNo rnum;
    private int cNum, attempt, min, max;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(44, 62, 80)); 

        rnum = new RandNo();
        attempt = 0;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        titleLabel = createLabel("Number Guessing Game", new Font("Arial", Font.BOLD, 22), new Color(241, 196, 15));
        add(titleLabel, gbc);

        gbc.gridy++;
        add(createLabel("Enter Minimum Number:", new Font("Arial", Font.PLAIN, 14), Color.WHITE), gbc);

        gbc.gridy++;
        minField = createTextField();
        add(minField, gbc);

        gbc.gridy++;
        add(createLabel("Enter Maximum Number:", new Font("Arial", Font.PLAIN, 14), Color.WHITE), gbc);

        gbc.gridy++;
        maxField = createTextField();
        add(maxField, gbc);

        gbc.gridy++;
        startButton = createButton("Start Game", new Color(46, 204, 113));
        add(startButton, gbc);
        gbc.gridy++;
        rangeLabel = createLabel("", new Font("Arial", Font.BOLD, 14), Color.WHITE);
        add(rangeLabel, gbc);
        gbc.gridy++;
        messageLabel = createLabel("", new Font("Arial", Font.PLAIN, 14), Color.ORANGE);
        add(messageLabel, gbc);
        gbc.gridy++;
        add(createLabel("Enter Your Guess:", new Font("Arial", Font.PLAIN, 14), Color.WHITE), gbc);
        gbc.gridy++;
        guessField = createTextField();
        guessField.setEnabled(false);
        add(guessField, gbc);
        gbc.gridy++;
        guessButton = createButton("Guess", new Color(52, 152, 219));
        guessButton.setEnabled(false);
        add(guessButton, gbc);
        gbc.gridy++;
        resetButton = createButton("Reset Game", new Color(231, 76, 60));
        resetButton.setEnabled(false);
        add(resetButton, gbc);
        gbc.gridy++;
        attemptsLabel = createLabel("Attempts: 0", new Font("Arial", Font.BOLD, 14), Color.WHITE);
        add(attemptsLabel, gbc);
        startButton.addActionListener(e -> startGame());
        guessButton.addActionListener(e -> makeGuess());
        resetButton.addActionListener(e -> resetGame());
    }
    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(10);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void startGame() {
        try {
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());

            if (min >= max) {
                messageLabel.setText("❌ Minimum must be less than Maximum!");
                return;
            }

            cNum = rnum.generate(max, min);
            attempt = 0;
            rangeLabel.setText("✅ Guess a number between " + min + " and " + max);
            messageLabel.setText("🎯 Game Started! Enter your guess.");
            attemptsLabel.setText("Attempts: 0");

            guessField.setEnabled(true);
            guessButton.setEnabled(true);
            resetButton.setEnabled(true);
            startButton.setEnabled(false);

        } catch (NumberFormatException e) {
            messageLabel.setText("❌ Please enter valid numbers!");
        }
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempt++;

            if (guess > cNum) {
                messageLabel.setText("📈 Too high! Try again.");
            } else if (guess < cNum) {
                messageLabel.setText("📉 Too low! Try again.");
            } else {
                messageLabel.setText("🎉 Congratulations! You guessed it right in " + attempt + " attempts!");
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            }

            attemptsLabel.setText("Attempts: " + attempt);

        } catch (NumberFormatException e) {
            messageLabel.setText("❌ Enter a valid number!");
        }
    }

    private void resetGame() {
        minField.setText("");
        maxField.setText("");
        guessField.setText("");

        messageLabel.setText("");
        rangeLabel.setText("");
        attemptsLabel.setText("Attempts: 0");

        guessField.setEnabled(false);
        guessButton.setEnabled(false);
        resetButton.setEnabled(false);
        startButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame().setVisible(true));
    }
}
