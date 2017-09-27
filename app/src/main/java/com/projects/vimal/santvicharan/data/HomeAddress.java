package com.projects.vimal.santvicharan.data;

/**
 * Created by vimal on 9/16/17.
 *
 * Class to hold the haribhatka's home address info
 */

public class HomeAddress extends Haribhakta {

    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;


    public HomeAddress (String systemId, String streetAddress, String city,
                        String state, String zipCode, String country) {

        super.setSystemId(systemId);
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Get the home street address
     * @return
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Set the home street address
     * @param streetAddress
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Get the city
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the state
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Set the state
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get the zip code
     * @return
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Set the zip code
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get the country
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
