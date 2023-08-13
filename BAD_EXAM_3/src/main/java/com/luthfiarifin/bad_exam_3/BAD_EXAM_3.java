/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.luthfiarifin.bad_exam_3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luthfiarifin
 */
public class BAD_EXAM_3 {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bad_exam_3?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private JFrame frame;
    private JTable memberTable;
    private JTextField memberIdTextField;
    private JTextField phoneTextField;
    private JTextField addressTextField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new BAD_EXAM_3()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        frame = new JFrame("Member Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        memberTable = new JTable();
        updateTable();

        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        editPanel.add(new JLabel("ID Member:"));
        memberIdTextField = new JTextField();
        editPanel.add(memberIdTextField);

        editPanel.add(new JLabel("Nomor handphone baru:"));
        phoneTextField = new JTextField();
        editPanel.add(phoneTextField);

        editPanel.add(new JLabel("Alamat baru:"));
        addressTextField = new JTextField();
        editPanel.add(addressTextField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> {
            saveChanges();
        });
        editPanel.add(saveButton);

        JScrollPane tableScrollPane = new JScrollPane(memberTable);
        tableScrollPane.setPreferredSize(new Dimension(memberTable.getPreferredSize().width, 200));
        frame.add(tableScrollPane, BorderLayout.CENTER);

        frame.add(editPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private void updateTable() {
        try (Connection connection = getConnection()) {
            String query = "SELECT member_id, member_name, point, handphone, address FROM Member";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            List<String> columnNames = new ArrayList<>();
            columnNames.add("ID");
            columnNames.add("Nama");
            columnNames.add("Poin");
            columnNames.add("Handphone");
            columnNames.add("Alamat");

            List<List<Object>> data = new ArrayList<>();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                row.add(resultSet.getInt("member_id"));
                row.add(resultSet.getString("member_name"));
                row.add(resultSet.getInt("point"));
                row.add(resultSet.getString("handphone"));
                row.add(resultSet.getString("address"));
                data.add(row);
            }

            DefaultTableModel model = new DefaultTableModel(data.stream()
                    .map(list -> list.toArray(new Object[0]))
                    .toArray(Object[][]::new), columnNames.toArray());
            memberTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveChanges() {
        String memberIdStr = memberIdTextField.getText().trim();
        String newPhone = phoneTextField.getText().trim();
        String newAddress = addressTextField.getText().trim();

        if (memberIdStr.isEmpty() || newPhone.isEmpty() || newAddress.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua fields harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int memberId;
        try {
            memberId = Integer.parseInt(memberIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "ID member tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM Member WHERE member_id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(query);
            selectStatement.setInt(1, memberId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(frame, "ID member tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String updateQuery = "UPDATE Member SET handphone = ?, address = ? WHERE member_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, newPhone);
            updateStatement.setString(2, newAddress);
            updateStatement.setInt(3, memberId);
            updateStatement.executeUpdate();

            updateTable();
            clearFields();
            JOptionPane.showMessageDialog(frame, "Berhasil merubah data", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error -.-", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        memberIdTextField.setText("");
        phoneTextField.setText("");
        addressTextField.setText("");
    }
}
