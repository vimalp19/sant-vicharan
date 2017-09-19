package com.projects.vimal.santvicharan.data;

/**
 * Created by vimal on 9/16/17.
 *
 * Class to hold the haribhakta's primary information:
 * name, center, email, phone
 */

public class ProfileInfo extends Haribhakta {

    private String firstName;
    private String lastName;

    //TODO - Consider implementing an enum for allowable centers
    private String center;

    private String emailAddress;
    private String phoneNumber;

    private int centerNum;
    private int regionNum;
    private int countryNum;

    public ProfileInfo () {

    }

    public ProfileInfo (String systemId, String firstName, String lastName,
                        String center, String emailAddress, String phoneNumber,
                        int centerNum, int regionNum, int countryNum) {

        super.setSystemId(systemId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.center = center;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.centerNum = centerNum;
        this.regionNum = regionNum;
        this.countryNum = countryNum;
    }

    /**
     * Get the Haribhakta's first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the Haribhakta's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the Haribhatka's last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the Haribhakta's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the Haribhakta's center
     * @return
     */
    public String getCenter() {
        return center;
    }

    /**
     * Set the Haribhakta's center
     * @param center
     */
    public void setCenter(String center) {
        this.center = center;
    }

    /**
     * Get the Haribhakta's email address
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the Haribhakta's email address
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get the Haribhakta's phone number
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the Haribhakta's phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = formatPhoneNumForDBInsert(phoneNumber);
    }

    /**
     * Get the Haribhakta's center number
     * @return
     */
    public int getCenterNum() {
        return centerNum;
    }

    /**
     * Set the Haribhakta's center number
     * @param centerNum
     */
    public void setCenterNum(int centerNum) {
        this.centerNum = centerNum;
    }

    /**
     * Get the Haribhakta's region number
     * @return
     */
    public int getRegionNum() {
        return regionNum;
    }

    /**
     * Set the Haribhakta's region number
     * @param regionNum
     */
    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    /**
     * Get the Haribhakta's country number
     * @return
     */
    public int getCountryNum() {
        return countryNum;
    }

    /**
     * Set the Haribhakta's country number
     * @param countryNum
     */
    public void setCountryNum(int countryNum) {
        this.countryNum = countryNum;
    }

    /**
     * Helper method that removes all non-digits from inputted
     * phone number before proceeding with DB insert
     * @param phoneNumber
     * @return
     */
    private String formatPhoneNumForDBInsert (String phoneNumber) {

        return phoneNumber.replaceAll("[^0-9]", "");
    }


    /**
     * Helper method that formats the phone number into:
     * xxx-xxx-xxxx
     * for displaying in app activity
     * @param phoneNumber
     * @return
     */
    private String formatPhoneNumForUIDisplay (String phoneNumber) {

        String formattedNum = null;

        //TODO - Need to implement functionality for formatting the phone number

        return formattedNum;
    }
}
