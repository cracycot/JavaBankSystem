package org.example;

import entites.Facade;
import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.BankNotFoundException;
import entites.exceptions.InsufficientFundsException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BankNotFoundException, InsufficientFundsException, AccountIsBlockedException {

        Runnable timeMachine = () -> {
            Facade.facade.startTimer();
        };
        Runnable checkInterface = () -> {
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
                        try {
                            Facade.facade.createAccount();
                        } catch (BankNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:
                        try {
                            Facade.facade.createTransaction();
                        } catch (BankNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (InsufficientFundsException e) {
                            throw new RuntimeException(e);
                        } catch (AccountIsBlockedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 4:
                        try {
                            Facade.facade.cancelTransaction();
                        } catch (BankNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 5:
                        Facade.facade.createBank();
                        break;
                    case 6:
                        Facade.facade.printBanks();
                        break;
                    case 7:
                        try {
                            Facade.facade.printTransactions();
                        } catch (BankNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 8:
                        Facade.facade.printUsers();
                        break;
                }
            }
        };
        Thread thread0 = new Thread(timeMachine);
        Thread thread1 = new Thread(checkInterface);
        thread0.start();
        thread1.start();

    }
}