package com.projects.vimal.santvicharan.data;

import java.util.Date;

/**
 * Created by vimal on 9/16/17.
 *
 * Class to hold the haribhakta's vicharan update
 */

public class Update extends Haribhakta {

    private Date date;
    private String note;
    private String id;
    private Niyams niyams;

    public Update() {

    }

    public Update (String systemId, Date date, String note, String id, Niyams niyams) {

        super.setSystemId(systemId);
        this.date = date;
        this.note = note;
        this.id = id;
        this.niyams = niyams;
    }


    /**
     * Get the date for the update
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date for the update
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the notes for the update
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * Set the notes for this update
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Set the update's system generated ID
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Get the update's system generated ID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the update's niyams discussed
      * @return
     */
    public Niyams getNiyams() {
        return niyams;
    }

    /**
     * Get the update's niyams discussed
     * @param niyams
     */
    public void setNiyams(Niyams niyams) {
        this.niyams = niyams;
    }
}
