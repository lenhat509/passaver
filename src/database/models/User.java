/**
 * Team 4
 * Author: Nhat Le, Karan Partap Virk, Lovepreet Uppal, Wen Luo
 * User class
 */
package database.models;

public class User {
    // userID is equivalent to master username
    private String userId;
    private String password;
    private String securityQuestion;
    private String securityAnswer;

    /**
     * User's constructor
     * @param userId
     * @param password
     * @param securityQuestion
     * @param securityAnswer
     */
    public User(String userId, String password, String securityQuestion, String securityAnswer) {
        this.userId = userId;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    /**
     * get userId aka master username
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * get password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * get security question
     * @return
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * get security answer
     * @return
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * convert User to String
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                '}';
    }
}