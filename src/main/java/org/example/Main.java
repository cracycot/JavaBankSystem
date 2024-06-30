package org.example;

import entites.Facade;
import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.BankNotFoundException;
import entites.exceptions.InsufficientFundsException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BankNotFoundException, InsufficientFundsException, AccountIsBlockedException {
        int userId = -1;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("нажмите 1 чтобы создать пользователя 2 чтобы создать счет 3 чтобы создать транзакцию 4 для отмены транзакции 5 чтобы создать банк 6 для вывода банков 7 для вывода транзакций 8 для вывода пользователей");
            int flag = scanner.nextInt();
            switch (flag) {
                case 1:
                    userId = Facade.facade.createUser();
                    break;
                case 2:
                    Facade.facade.createAccount();
                    break;
                case 3:
                    Facade.facade.createTransaction();
                    break;
                case 4:
                    Facade.facade.cancelTransaction();
                    break;
                case 5:
                    Facade.facade.createBank();
                    break;
                case 6:
                    Facade.facade.printBanks();
                    break;
                case 7:
                    Facade.facade.printTransactions();
                    break;
                case 8:
                    Facade.facade.printUsers();
                    break;
            }
        }
    }
}