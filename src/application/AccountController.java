/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * AccountController class
 */
package application;

import database.dao.AccountDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AccountController {

    @FXML
    public Pane accountPane;

    private SharedProperty shared = SharedProperty.getSharedProperty();

    /**
     * navigate to edit account page
     * @param mouseEvent
     */
    @FXML
    public void editAccount(MouseEvent mouseEvent) {
        String id = accountPane.getAccessibleText();
        shared.setAccountEditedId(id);
        shared.navigateTo("edit-view.fxml");
    }
    /**
     * delete an account
     * @param mouseEvent
     */
    @FXML
    public void deleteAccount(MouseEvent mouseEvent) {
    	String id = accountPane.getAccessibleText();
    	AccountDao accountDao = shared.getAccountDao();
    	accountDao.deleteAccount(id);
    	shared.navigateTo("home-view.fxml");
    }
}
