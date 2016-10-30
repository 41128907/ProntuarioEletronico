/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelBean;

/**
 *
 * @author carlosantonio
 */
public class BeanUsuario {
    private Integer userCode;
    private String userName;
    private String userPass;
    private String userType;
    private String userSearch;

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the userSearch
     */
    public String getUserSearch() {
        return userSearch;
    }

    /**
     * @param userSearch the userSearch to set
     */
    public void setUserSearch(String userSearch) {
        this.userSearch = userSearch;
    }

    

}
