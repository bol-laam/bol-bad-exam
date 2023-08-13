/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.luthfiarifin.bad_exam_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author luthfiarifin
 */
public class BAD_EXAM_2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BAD_EXAM_2::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Form Registrasi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Confirm Password:"));
        JPasswordField confirmPasswordField = new JPasswordField();
        formPanel.add(confirmPasswordField);

        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleRadio = new JRadioButton("Male");
        JRadioButton femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Member Level:"));
        String[] memberLevels = {"Basic", "Gold", "Platinum"};
        JComboBox<String> levelComboBox = new JComboBox<>(memberLevels);
        formPanel.add(levelComboBox);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String memberLevel = (String) levelComboBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Registration successful:\nUsername: " + username + "\nGender: " + gender + "\nMember Level: " + memberLevel, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
