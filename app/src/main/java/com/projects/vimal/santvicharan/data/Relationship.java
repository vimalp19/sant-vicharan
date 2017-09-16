package com.projects.vimal.santvicharan.data;

/**
 * Created by vimal on 9/16/17.
 *
 * Class to hold the Haribhakta's relationship
 */

public class Relationship {

    //TODO - Consider implementing an enum for allowable relationship types
    private String type;

    private int relatedHaribhaktaId;
    private int id;

    /**
     * Get the relationship type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set the relationship type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the related haribhakta's system ID
     * @return
     */
    public int getRelatedHaribhaktaId() {
        return relatedHaribhaktaId;
    }

    /**
     * Set the related haribhakta's system ID
     * @param relatedHaribhaktaId
     */
    public void setRelatedHaribhaktaId(int relatedHaribhaktaId) {
        this.relatedHaribhaktaId = relatedHaribhaktaId;
    }

    /**
     * Get the system generated ID for the relationship
     * @return
     */
    public int getId() {
        return id;
    }

    //TODO - Is this even really needed?
    /**
     * Set the system generated ID for the relationship
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
