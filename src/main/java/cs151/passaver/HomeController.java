package cs151.passaver;

import cs151.database.dao.AccountDao;
import cs151.database.models.Account;
import cs151.database.models.User;
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

    public void initialize() {
        getFullAccountList();
        userId.setText(shared.getLoginUser().getUserId());
    }

    @FXML
    public void createPassword(MouseEvent mouseEvent) {
        shared.navigateTo("saveNewPassword-view.fxml");
    }
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
    public Pane createAccountNode(Account account) {
        Pane accountPane = null;
        try {
            accountPane = FXMLLoader.load(getClass().getResource("account.fxml"));
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

    public void resetAccountList(MouseEvent mouseEvent) {
        cancelBtn.setDisable(true);
        searchTextField.setText("");
        accountList.getChildren().clear();
        getFullAccountList();
    }

    public void logout(MouseEvent mouseEvent) {
        shared.setLoginUser(null);
        shared.navigateTo("login-view.fxml");
    }
}
