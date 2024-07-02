package entites;
import entites.bankAccounts.BankAccount;
import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.BankNotFoundException;
import entites.exceptions.InsufficientFundsException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Фасад
 */
public class Facade {
    MainBank mainBank;
    private HashMap<Integer, User> userHashMap = new HashMap<>();
    public static final Facade facade = new Facade();

    private Facade() {

    }

    /**
     * Механизм ускорения время
     */
    public void startTimer() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            MainBank.mainBank.interestUpdate();
        }, 0, 10, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            MainBank.mainBank.addCommission();
        }, 0, 70, TimeUnit.SECONDS);
    }

    public int createUser() {
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
        return user.getId();
    }

    public void createAccount() throws BankNotFoundException {
        System.out.println("Создание счета");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите Id владельца");
        int userId = scanner.nextInt();
        scanner.nextLine();
        User user = userHashMap.get(userId);
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

    public void createTransaction () throws BankNotFoundException, InsufficientFundsException, AccountIsBlockedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Транзакция");
        System.out.println("Введите название вашего банка");
        String bankNameOwner = scanner.nextLine();
        Bank bankOwner = MainBank.mainBank.getBankByName(bankNameOwner);
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
                System.out.println(accountAdd.getInterestBalance());
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

    public void cancelTransaction() throws BankNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Отмена транзакции");
        System.out.println("Введите название банка");
        String bankNameOwner = scanner.nextLine();
        System.out.println("Введите номер вашего счета");
        int accountIdOwner = scanner.nextInt();
        System.out.println("Введите номер транзакции");
        int transactionIdOwner = scanner.nextInt();
        Bank bankOwner = mainBank.getBankByName(bankNameOwner);
        System.out.println("Введите название банка получателя/ничего не вводите, если перевод внутри банка");
        String bankNameRecipient = scanner.nextLine();
        if (bankNameRecipient.isEmpty()) {
            bankOwner.cancelTransaction(accountIdOwner, transactionIdOwner);
        } else {
            System.out.println("Введите номер счета получателя");
            int accountIdRecipient = scanner.nextInt();
            System.out.println("Введите номер транзакции получателя");
            int transactionIdRecipient = scanner.nextInt();
            MainBank.mainBank.cancelInterbankTransfer(bankNameOwner, bankNameRecipient, accountIdOwner,accountIdRecipient,transactionIdOwner, transactionIdRecipient );
        }

    }

    public void createBank() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создание банка");
        System.out.println("Введите название");
        String bankName = scanner.nextLine();
        System.out.println("Введите коммисию");
        float commission = scanner.nextFloat();
        System.out.println("Введите процент на остаток");
        float interestOnDeposit = scanner.nextFloat();
        System.out.println("Введите максимальную сумму переводов/снятия для не авторизированного пользователя");
        float maxAmountBlocked = scanner.nextFloat();
        System.out.println("Введите максимальную кредитный лимит");
        float creditLimit = scanner.nextFloat();
        MainBank.mainBank.createBank(bankName, commission, interestOnDeposit, maxAmountBlocked, creditLimit);
    }

    public void printBanks() {
        System.out.println("название коммисия процент");
        MainBank.mainBank.printBanks();
    }

    public void printTransactions() throws BankNotFoundException {
        System.out.println("Вывод транзакций");
        System.out.println("Введите название банка");
        Scanner scanner = new Scanner(System.in);
        String bankName = scanner.nextLine();
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.printAccounts();
    }
    public void printUsers() {
        System.out.println("Имя фамилия адрес паспорт Id");
        for (int key : userHashMap.keySet()) {
            User user = userHashMap.get(key);
            System.out.println(user.getName() + " " + user.getLastName() + " " + user.getAddress() + " " + user.getNumberPassport() + " " + user.getId());
        }
    }
}
