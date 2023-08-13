/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luthfiarifin.bad_exam_1;

/**
 *
 * @author luthfiarifin
 */
class Member {

    protected String name;
    protected String level;
    protected int points;

    public Member(String name, String level) {
        this.name = name;
        this.level = level;
        this.points = 0;
    }

    public void purchase(int transactionAmount) {
        if (level.equalsIgnoreCase("basic")) {
            points += (transactionAmount / 10000) * 2;
        } else if (level.equalsIgnoreCase("gold")) {
            points += (int) (transactionAmount * 0.001);
        } else if (level.equalsIgnoreCase("platinum")) {
            points += ((transactionAmount / 5000) * 2) + (int) (transactionAmount * 0.001);
        }
    }

    public String getMemberInfo() {
        return name + " - " + level + " - " + points + " points";
    }
}
