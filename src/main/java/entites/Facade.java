package entites;
import entites.bankAccounts.BankAccount;
import entites.exceptions.AccountIsblockedException;
import entites.exceptions.BankNotFoundException;
import entites.exceptions.InsufficientFundsException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Facade {
    MainBank mainBank;
    private HashMap<Integer, User> userHashMap;
    public static final Facade facade = new Facade();

    private Facade() {

    }
    public void startTimer() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            mainBank.interestUpdate();
        }, 0, 10, TimeUnit.SECONDS);
    }
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создание пользователя");
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String lastName = scanner.nextLine();
        System.out.println("Опцианально введите адрес");
        String address = scanner.nextLine();
        System.out.println("Опцианально введите паспорт");
        String passport = scanner.nextLine();
        User user = new User();
        if (!(address.equals("")) && !(passport.equals(""))) { user = new User.Builder(name,lastName).addres(address).numberPassport(passport).build();
        } else if (address.equals("") && !(passport.equals(""))) {
             user = new User.Builder(name,lastName).numberPassport(passport).build();
        } else if (!address.isEmpty() && passport.isEmpty()) {
            user = new User.Builder(name,lastName).addres(address).build();
        } else if (address.isEmpty() && passport.isEmpty()) {
            user = new User.Builder(name,lastName).build();
        }
        userHashMap.put(user.getId(), user);
    }
    public void createAccount(int userId) throws BankNotFoundException {
        Scanner scanner = new Scanner(System.in);
        User user = userHashMap.get(userId);
        System.out.println("Создание счета");
        System.out.println("Введите название банка");
        String bankName = scanner.nextLine();
        System.out.println("Введите 1 - дебитовый счет; 2 - кредитный счет; 3 - депозитный счет");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                user.createDebitAccount(bankName);
                break;
            case 2:
                user.createCreditAccount(bankName);
                break;
            case 3:
                user.createDepositAccount(bankName);
        }
    }
    public void madeTransaction (int userId) throws BankNotFoundException, InsufficientFundsException, AccountIsblockedException {
        Scanner scanner = new Scanner(System.in);
        User user = userHashMap.get(userId);
        System.out.println("Транзакция");
        System.out.println("Введите название вашего банка");
        String bankNameOwner = scanner.nextLine();
        Bank bankOwner = mainBank.getBankByName(bankNameOwner);
        System.out.println("Введите номер вашего счета");
        int accountId = scanner.nextInt();
        System.out.println("Введите тип транзакции 1 - пополнить счет; 2 - снять деньги; 3 - выполнить перевод");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                System.out.println("Введите сумму пополнения");
                float amountAdd = scanner.nextFloat();
                BankAccount accountAdd = bankOwner.getBankAccountById(accountId);
                accountAdd.addMoney(amountAdd);
                break;
            case 2:
                System.out.println("Введите сумму снятия");
                float amountWithdraw = scanner.nextFloat();
                BankAccount accountWithdraw = bankOwner.getBankAccountById(accountId);
                accountWithdraw.withdrawMoney(amountWithdraw);
                break;
            case 3:
                BankAccount bankAccountTransfer = bankOwner.getBankAccountById(accountId);
                System.out.println("Введите название банка получателя/ничего не вводите, если перевод внутри банка");
                String bankNameRecipient = scanner.nextLine();
                System.out.println("Введите сумму перевода");
                float amountTransfer = scanner.nextFloat();
                System.out.println("Введите номер счета получателя");
                int accountIdRecipient = scanner.nextInt();
                if (!bankNameRecipient.isEmpty()) {
                    Bank bankRecipient = mainBank.getBankByName(bankNameRecipient);
                    bankRecipient.getBankAccountById(accountIdRecipient);
                    MainBank.mainBank.interbankTransfer(bankNameOwner, bankNameRecipient, accountId, accountIdRecipient, amountTransfer);
                } else {
                    BankAccount accountRecipient = bankOwner.getBankAccountById(accountIdRecipient);
                    bankAccountTransfer.transferMoney(amountTransfer, accountRecipient);
                }
        }
    }
}
