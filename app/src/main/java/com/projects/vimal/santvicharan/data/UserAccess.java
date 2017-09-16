package com.projects.vimal.santvicharan.data;

/**
 * Created by vimal on 9/16/17.
 *
 * Class to hold the user's information and access level
 */

public class UserAccess {

    private String firebaseId;
    private int centerNum;
    private int regionNum;
    private int countryNum;
    private int accessLevel;
    private int systemId;

    /**
     * Get the user's firebase ID
     * @return
     */
    public String getFirebaseId() {
        return firebaseId;
    }

    /**
     * Set the user's firebase ID
     * @param firebaseId
     */
    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    /**
     * Get the user's center number (xxx)
     * @return
     */
    public int getCenterNum() {
        return centerNum;
    }

    /**
     * Set the user's center number (xxx)
     * @param centerNum
     */
    public void setCenterNum(int centerNum) {
        this.centerNum = centerNum;
    }

    /**
     * Get the user's region number (xxx)
     * @return
     */
    public int getRegionNum() {
        return regionNum;
    }

    /**
     * Set the user's region number (xxx)
     * @param regionNum
     */
    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    /**
     * Get the user's country number (xxxx)
     * @return
     */
    public int getCountryNum() {
        return countryNum;
    }

    /**
     * Set the user's country number (xxxx)
     * @param countryNum
     */
    public void setCountryNum(int countryNum) {
        this.countryNum = countryNum;
    }

    /**
     * Get the user's access level
     * @return
     */
    public int getAccessLevel() {
        return accessLevel;
    }

    /**
     * Set the user's access level
     * @param accessLevel
     */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * Get the user's system generated ID
     * @return
     */
    public int getSystemId() {
        return systemId;
    }

    /**
     * Set the user's system generated ID
     * @param systemId
     */
    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
