/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.luthfiarifin.bad_exam_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luthfiarifin
 */
public class BAD_EXAM_1 {

    final private static List<Member> members = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Masukkan nama member (atau 'selesai' untuk keluar): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("selesai")) {
                break;
            }

            System.out.print("Masukkan level member (basic/gold/platinum): ");
            String level = scanner.nextLine();

            Member member;
            if (level.equalsIgnoreCase("basic") || level.equalsIgnoreCase("gold") || level.equalsIgnoreCase("platinum")) {
                member = new Member(name, level);
            } else {
                System.out.println("Invalid member level.");
                continue;
            }

            System.out.print("Masukan total transaksi: ");
            int transactionAmount = Integer.parseInt(scanner.nextLine());
            member.purchase(transactionAmount);

            members.add(member);
            System.out.println("Member berhasil ditambahkan.\n");
        }

        System.out.println("\nDaftar member:");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getMemberInfo());
        }
    }

}
