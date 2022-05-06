/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * Reminder class
 */
package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import database.dao.AccountDao;
import database.models.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import database.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class ReminderController {
	@FXML
	public Label todayDate;
	
	@FXML
	public FlowPane expiredAccounts;
	
	public SharedProperty shared = SharedProperty.getSharedProperty();
	
	/**
	 * navigate to home page
	 * @param mouseEvent
	 */
	@FXML
	public void goToHome(MouseEvent mouseEvent) {
		shared.navigateTo("home-view.fxml");
		
	}
	
	/**
	 * initialize the page with expired passwords
	 */
	public void initialize() {
		AccountDao accountDao = shared.getAccountDao();
		User loginUser = shared.getLoginUser();
		LocalDate today = LocalDate.now();
		todayDate.setText(today.toString());
		ArrayList<Account> list = accountDao.checkExpiredPassword(loginUser.getUserId(), today);
		for(Account expiredAccount: list) {
			Pane accountPane = createExpiredAccountNode(expiredAccount);
			expiredAccounts.getChildren().add(accountPane);
		}
		
	/**
	 * generate expired account UI node	
	 */
	}
	public Pane createExpiredAccountNode(Account account) {
		Pane accountPane = null;
		try {
			accountPane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource("expiredAccount.fxml"));
			Label appName = (Label) accountPane.getChildren().get(2);
            Label email = (Label) accountPane.getChildren().get(4);
            Label userName = (Label) accountPane.getChildren().get(5);
            Label password = (Label) accountPane.getChildren().get(7);
            Label expirationDate = (Label) accountPane.getChildren().get(11);
            Label creationDate = (Label) accountPane.getChildren().get(10);
           
            appName.setText(account.getAppName());
            email.setText(account.getEmail());
            userName.setText(account.getUsername());
            
            PassUtil passUtil = new PassUtil();
            String decryptedPw = passUtil.decrypt(account.getPassword());
            password.setText(decryptedPw);
            
            expirationDate.setText(account.getExpirationDate().toString());
            creationDate.setText(account.getCreationDate().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accountPane;
	}
}