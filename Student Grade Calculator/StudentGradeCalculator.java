import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {
    private JTextField[] subjectFields;
    private JLabel totalLabel, percentageLabel, gradeLabel;

    public StudentGradeCalculator(int numSubjects) {
        setTitle("Student Grade Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 44, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Student Grade Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 173, 181)); 
        panel.add(titleLabel, gbc);

        gbc.gridy++; 

        subjectFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            gbc.gridwidth = 1;
            JLabel label = createLabel("Subject " + (i + 1) + " Marks:");
            panel.add(label, gbc);
            
            gbc.gridx = 1;
            subjectFields[i] = new JTextField(10);
            subjectFields[i].setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(subjectFields[i], gbc);
            
            gbc.gridx = 0;
            gbc.gridy++;
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(0, 173, 181)); 
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(calculateButton, gbc);

        totalLabel = createLabel("Total Marks: ");
        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(totalLabel, gbc);
        
        percentageLabel = createLabel("Percentage: ");
        gbc.gridy++;
        panel.add(percentageLabel, gbc);
        
        gradeLabel = createLabel("Grade: ");
        gbc.gridy++;
        panel.add(gradeLabel, gbc);

        add(panel);
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void calculateResults() {
        int totalMarks = 0;
        int numSubjects = subjectFields.length;

        for (JTextField field : subjectFields) {
            try {
                int marks = Integer.parseInt(field.getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Enter marks between 0 and 100.");
                    return;
                }
                totalMarks += marks;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
                return;
            }
        }

        double percentage = (double) totalMarks / numSubjects;
        String grade = calculateGrade(percentage);

        totalLabel.setText("Total Marks: " + totalMarks);
        percentageLabel.setText("Percentage: " + String.format("%.2f", percentage) + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }

    public static void main(String[] args) {
        int numSubjects;
        try {
            numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter number of subjects:"));
            if (numSubjects <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number of subjects.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Enter a number.");
            return;
        }

        SwingUtilities.invokeLater(() -> new StudentGradeCalculator(numSubjects).setVisible(true));
    }
}
