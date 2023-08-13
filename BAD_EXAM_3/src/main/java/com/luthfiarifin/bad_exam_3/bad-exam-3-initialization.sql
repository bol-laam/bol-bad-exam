/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  luthfiarifin
 * Created: Aug 13, 2023
 */

CREATE DATABASE bad_exam_3;

USE bad_exam_3;

CREATE TABLE Member (
    member_id INT NOT NULL PRIMARY KEY,
    member_name VARCHAR(100) NOT NULL,
    point INT NOT NULL,
    handphone VARCHAR(13) NOT NULL,
    address VARCHAR(255) NOT NULL
);

INSERT INTO Member (member_id, member_name, point, handphone, address) VALUES
(1, 'John Doe', 100, '1234567890', '123 Main St'),
(2, 'Jane Smith', 200, '9876543210', '456 Elm St'),
(3, 'Alice Johnson', 300, '5555555555', '789 Oak St');
