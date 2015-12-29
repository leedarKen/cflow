/*SMC1.0 auto generator java code*/
package com.csoft.api.teamstructure.vo;

import java.io.Serializable;

/**
 * version : 1.0.0
 */
public class UserVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userTypeId;
    public int getUserTypeId() {
        return this.userTypeId;
    }
    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    private int depId;
    public int getDepId() {
        return this.depId;
    }
    public void setDepId(int depId) {
        this.depId = depId;
    }

    private String userName;
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String primaryEmail;
    public String getPrimaryEmail() {
        return this.primaryEmail;
    }
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

}
