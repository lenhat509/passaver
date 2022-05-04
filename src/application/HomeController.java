/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * HomeController class
 */
package application;

import database.dao.AccountDao;
import database.models.Account;
import database.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;


public class HomeController {

    private SharedProperty shared = SharedProperty.getSharedProperty();

    @FXML
    public FlowPane accountList;

    @FXML
    public TextField searchTextField;

    @FXML
    public Button cancelBtn;

    @FXML
    public Label userId;

    /**
     * Initialize the page by getting all accounts of login user and show on the UI
     */
    public void initialize() {
        getFullAccountList();
        userId.setText(shared.getLoginUser().getUserId());
    }

    /**
     * navigate to saveNewPassword page
     * @param mouseEvent
     */
    @FXML
    public void createPassword(MouseEvent mouseEvent) {
        shared.navigateTo("saveNewPassword-view.fxml");
    }

    /**
     * get all accounts that match with user's search input and
     * show on UI
     * @param mouseEvent
     */
    @FXML
    public void searchAccount(MouseEvent mouseEvent) {
        String keyword = searchTextField.getText().trim();
        AccountDao accountDao = shared.getAccountDao();
        User loginUser = shared.getLoginUser();
        ArrayList<Account> accounts;
        if(keyword != ""){
            cancelBtn.setDisable(false);
            accounts = accountDao.searchAccounts(loginUser.getUserId(), keyword);
            accountList.getChildren().clear();
            for(Account account: accounts) {
                Pane accountPane = createAccountNode(account);
                accountList.getChildren().add(accountPane);
            }
        }
    }

    /**
     * generate a Pane that contain an account
     * @param account
     * @return
     */
    public Pane createAccountNode(Account account) {
        Pane accountPane = null;
        try {
            accountPane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource("account.fxml"));
            Label appName = (Label) accountPane.getChildren().get(4);
            Label email = (Label) accountPane.getChildren().get(6);
            Label userName = (Label) accountPane.getChildren().get(7);
            appName.setText(account.getAppName());
            email.setText(account.getEmail());
            userName.setText(account.getUsername());
            accountPane.setAccessibleText(account.getAccountId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountPane;
    }

    /**
     * get all the accounts and show them on the UI
     */
    public void getFullAccountList() {
        AccountDao accountDao = shared.getAccountDao();
        User loginUser = shared.getLoginUser();
        ArrayList<Account> list = accountDao.getAccounts(loginUser.getUserId());
        for(Account account: list) {
            Pane accountPane = createAccountNode(account);
            if(accountPane != null)
                accountList.getChildren().add(accountPane);
        }
    }

    /**
     * clear search functionality, reset home page to normal
     * @param mouseEvent
     */
    public void resetAccountList(MouseEvent mouseEvent) {
        cancelBtn.setDisable(true);
        searchTextField.setText("");
        accountList.getChildren().clear();
        getFullAccountList();
    }

    /**
     * logout the user
     * @param mouseEvent
     */
    public void logout(MouseEvent mouseEvent) {
        shared.setLoginUser(null);
        shared.navigateTo("login-view.fxml");
    }
}
