
package wad.logger.domain;

/**
 * @author axido
 * Class to use for cardiovascular exercise objects.
 */
public class Cardio {
    
    private Integer id;
    private String name;
    private Integer distance;
    private Integer duration;
    private String notes;
    
    public Cardio(String name, Integer duration) {
        //this.id = id;
        this.name = name;
        this.duration = duration;
    }
    
    public Cardio(Integer id, String name, Integer duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    
}
