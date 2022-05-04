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
		Pane pane = null;
		try {
			pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource("expiredAccount.fxml"));
			Label appName = (Label) pane.getChildren().get(1);
			Label expirationDate = (Label) pane.getChildren().get(3);
			Label username = (Label) pane.getChildren().get(5);
			Label email = (Label) pane.getChildren().get(7);
			appName.setText(account.getAppName());
			expirationDate.setText(account.getExpirationDate().toString());
			username.setText(account.getUsername());
			email.setText(account.getEmail());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pane;
	}
}