package cs151.passaver;

import cs151.database.DatabaseConnection;
import cs151.database.dao.AccountDao;
import cs151.database.dao.UserDao;
import cs151.database.models.Account;
import cs151.database.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class Passaver extends Application {

    private SharedProperty shared = SharedProperty.getSharedProperty();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Passaver.class.getResource("main-view.fxml"));
        AnchorPane pane = (AnchorPane) fxmlLoader.load();
        shared.setMainPane(pane);
        Scene scene = new Scene(pane, 800, 600);
        stage.setTitle("Passaver");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}

/*

        //test database connectivity
        DatabaseConnection dbconnect = DatabaseConnection.getDBConnection();
        Connection conn = dbconnect.getConnection();
        UserDao userDb = new UserDao(conn);

        User user = userDb.search("nhatle");
        System.out.println(user);

//        userDb.resetPassword("nhatle", "987654321");
//
//        User user2 = userDb.search("nhatle");
//        System.out.println(user2);

        AccountDao accountDao = new AccountDao(conn);
        //Account newAccount = new Account("Google", "nhatle@gmail.com", "lenhat509", "123456nhat");
        //accountDao.saveAccount(user.getUserId(), newAccount);
//        ArrayList<Account> list = accountDao.getAccounts(user.getUserId());
//        for(Account account : list)
//            System.out.println(account);
//        Account acc = list.get(0);
//
//        acc.setEmail("nhat@gmail.com");
//        acc.setUsername("lenhat");
//        acc.setAppName("Google Photo");
//        acc.setPassword("nhat1234567");
//        accountDao.editAccount(acc);
//        System.out.println(acc);
//        ArrayList<Account> list2 = accountDao.getAccounts(user.getUserId());
//        for(Account account : list2)
//            System.out.println(account);
//        accountDao.deleteAccount(acc.getAccountId());
//        ArrayList<Account> list2 = accountDao.getAccounts(user.getUserId());
//        System.out.println(list2.size());
//        Account account1 = new Account("Google Lens", "nhatle@gmail.com", "nhat509", "123456789");
//        Account account2 = new Account("Meta", "nhatle@gmail.com", "amazonle", "123456789");
//        Account account3 = new Account("Amazon", "nhatle@gmail.com", "nhat509", "123456789");
//
//        accountDao.saveAccount(user.getUserId(), account1);
//        accountDao.saveAccount(user.getUserId(), account2);
//        accountDao.saveAccount(user.getUserId(), account3);

//        ArrayList<Account> list = accountDao.getAccounts(user.getUserId());
//        for(Account account : list)
//            System.out.println(account);
//        System.out.println();
//        ArrayList<Account> list2 = accountDao.searchAccounts(user.getUserId(), "google");
//        for(Account account : list2)
//            System.out.println(account);
//        System.out.println();
//        ArrayList<Account> list3 = accountDao.searchAccounts(user.getUserId(), "nhat");
//        for(Account account : list3)
//            System.out.println(account);
//        System.out.println();
//        ArrayList<Account> list4 = accountDao.searchAccounts(user.getUserId(), "amazon");
//        for(Account account : list4)
//            System.out.println(account);
        LocalDate expiredDate = LocalDate.parse("2022-07-27");
        ArrayList<Account> list = accountDao.checkExpiredPassword(user.getUserId(),expiredDate);
        for(Account account : list)
            System.out.println(account);


 */