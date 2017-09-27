package com.projects.vimal.santvicharan.data;

/**
 * Created by vimal on 9/25/17.
 *
 * lass to hold the haribhakta's niyams discussed/given during vicharan update
 */

public class Niyams {

    private int puja;
    private int tilakChandlo;
    private int gujaratiReading;
    private int satsangReading;
    private int noOG;
    private int extraMalas;
    private int bowToParents;
    private int gharSabha;

    /**
     * Default constructor
     */
    public Niyams() {

    }

    /**
     * Constructor to set all the niyams using boolean values
     * @param puja
     * @param tilakChandlo
     * @param gujaratiReading
     * @param satsangReading
     * @param noOG
     * @param extraMalas
     * @param bowToParents
     * @param gharSabha
     */
    public Niyams(boolean puja, boolean tilakChandlo,
                  boolean gujaratiReading, boolean satsangReading,
                  boolean noOG, boolean extraMalas,
                  boolean bowToParents, boolean gharSabha) {

        setPujaInBoolean(puja);
        setTilakChandloInBoolean(tilakChandlo);
        setGujaratiReadingInBoolean(gujaratiReading);
        setSatsangReadingInBoolean(satsangReading);
        setNoOGInBoolean(noOG);
        setExtraMalasInBoolean(extraMalas);
        setBowToParentsInBoolean(bowToParents);
        setGharSabhaInBoolean(gharSabha);
    }


    /**
     * Method that returns in string format the niyams that were discussed
     * @return
     */
    public String getNiyamsDicussed() {

        String niyamsInString = "";

        if (getPuja() == 1)
            niyamsInString += " Puja,";

        if (getTilakChandlo() == 1)
            niyamsInString += " Tilak Chandlo,";

        if (getGujaratiReading() == 1)
            niyamsInString += " Gujarati Reading,";

        if (getSatsangReading() == 1)
            niyamsInString += " Satsang reading,";

        if (getNoOG() == 1)
            niyamsInString += " No onion/garlic,";

        if (getExtraMalas() == 1)
            niyamsInString += " 5 Extra malas,";

        if (getBowToParents() == 1)
            niyamsInString += " Bow down to parents,";

        if (getGharSabha() == 1)
            niyamsInString += " Ghar sabha,";


        //Return string indicating no niyams were dicussed if string is still empty
        if ("".equals(niyamsInString))
            niyamsInString = "No niyams were dicussed.";

        //Otherwise, remove the last comma from the string
        else
            niyamsInString = niyamsInString.substring(0, niyamsInString.length() - 1);

        return niyamsInString;
    }

    /**
     * Get the value to determine whether puja niyam was discussed
     * @return
     */
    public int getPuja() {
        return puja;
    }


    /**
     * Set value for whether puja niyam was discussed
     * @param puja
     */
    public void setPuja(int puja) {
        this.puja = puja;
    }

    /**
     * Set value for whether puja niyam was discussed
     * @param puja
     */
    public void setPujaInBoolean(boolean puja) {

        if (puja)
            this.puja = 1;
        else
            this.puja = 0;
    }


    /**
     * Get the value to determine whether Tilak Chandlo niyam was discussed
     * @return
     */
    public int getTilakChandlo() {
        return tilakChandlo;
    }

    /**
     * Set value for whether Tilak Chandlo niyam was discussed
     * @param tilakChandlo
     */
    public void setTilakChandlo(int tilakChandlo) {
        this.tilakChandlo = tilakChandlo;
    }

    /**
     * Set value for whether Tilak Chandlo niyam was discussed
     * @param tilakChandlo
     */
    public void setTilakChandloInBoolean(boolean tilakChandlo) {

        if (tilakChandlo)
            this.tilakChandlo = 1;
        else
            this.tilakChandlo = 0;
    }


    /**
     * Get the value to determine whether gujarati reading niyam was discussed
     * @return
     */
    public int getGujaratiReading() {
        return gujaratiReading;
    }

    /**
     * Set value for whether gujarati reading niyam was dicussed
     * @param gujaratiReading
     */
    public void setGujaratiReading(int gujaratiReading) {
        this.gujaratiReading = gujaratiReading;
    }

    /**
     * Set value for whether gujarati reading niyam was dicussed
     * @param gujaratiReading
     */
    public void setGujaratiReadingInBoolean(boolean gujaratiReading) {

        if (gujaratiReading)
            this.gujaratiReading = 1;
        else
            this.gujaratiReading = 0;
    }


    /**
     * Get the value to determine whether satsang reading niyam was discussed
     * @return
     */
    public int getSatsangReading() {
        return satsangReading;
    }

    /**
     * Set value for whether satsang reading niyam was dicussed
     * @param satsangReading
     */
    public void setSatsangReading(int satsangReading) {
        this.satsangReading = satsangReading;
    }

    /**
     * Set value for whether satsang reading niyam was dicussed
     * @param satsangReading
     */
    public void setSatsangReadingInBoolean(boolean satsangReading) {

        if (satsangReading)
            this.satsangReading = 1;
        else
            this.satsangReading = 0;
    }


    /**
     * Get the value to determine whether no onion/garlic niyam was discussed
     * @return
     */
    public int getNoOG() {
        return noOG;
    }

    /**
     * Set the value for whether no onion/garlic niyam was discussed
     * @param noOG
     */
    public void setNoOG(int noOG) {
        this.noOG = noOG;
    }

    /**
     * Set the value for whether no onion/garlic niyam was discussed
     * @param noOG
     */
    public void setNoOGInBoolean(boolean noOG) {

        if (noOG)
            this.noOG = 1;
        else
            this.noOG = 0;
    }


    /**
     * Get the value to determine whether 5 extra malas niyam was discussed
     * @return
     */
    public int getExtraMalas() {
        return extraMalas;
    }

    /**
     * Set the value for whether 5 extra malas niyam was discussed
     * @param extraMalas
     */
    public void setExtraMalas(int extraMalas) {
        this.extraMalas = extraMalas;
    }

    /**
     * Set the value for whether 5 extra malas niyam was discussed
     * @param extraMalas
     */
    public void setExtraMalasInBoolean(boolean extraMalas) {

        if (extraMalas)
            this.extraMalas = 1;
        else
            this.extraMalas = 0;
    }


    /**
     * Get the value to determine whether bowing down to parents niyam was discussed
     * @return
     */
    public int getBowToParents() {
        return bowToParents;
    }

    /**
     * Set the value for whether bowing down to parents niyam was discussed
     * @param bowToParents
     */
    public void setBowToParents(int bowToParents) {
        this.bowToParents = bowToParents;
    }

    /**
     * Set the value for whether bowing down to parents niyam was discussed
     * @param bowToParents
     */
    public void setBowToParentsInBoolean(boolean bowToParents) {

        if (bowToParents)
            this.bowToParents = 1;
        else
            this.bowToParents = 0;
    }


    /**
     * Get the value to determine whether ghar sabha niyam was discussed
     * @return
     */
    public int getGharSabha() {
        return gharSabha;
    }

    /**
     * Set the value for whether ghar sabha niyam was discussed
     * @param gharSabha
     */
    public void setGharSabha(int gharSabha) {
        this.gharSabha = gharSabha;
    }

    /**
     * Set the value for whether ghar sabha niyam was discussed
     * @param gharSabha
     */
    public void setGharSabhaInBoolean(boolean gharSabha) {

        if (gharSabha)
            this.gharSabha = 1;
        else
            this.gharSabha = 0;
    }
}
