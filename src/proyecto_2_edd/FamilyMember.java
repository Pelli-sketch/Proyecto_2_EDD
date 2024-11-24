/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author sebma
 */
public class FamilyMember {

    private String name;
    private String bornTo;
    private String[] fatherOf;
    private String known_As;
    private String heldTitle;
    private String wedTo;
    private String eyeColor;
    private String hairColor;
    private String house;
    private String notes;
    private String fate;

    public FamilyMember(String name, String bornTo, String[] fatherOf, String known_As, String heldTitle, String wedTo, String eyeColor, String hairColor, String house, String notes, String fate) {
        this.name = name;
        this.bornTo = bornTo;
        this.fatherOf = fatherOf;
        this.known_As = known_As;
        this.wedTo = wedTo;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.heldTitle = heldTitle;
        this.house = house;
        this.notes = notes;
        this.fate = fate;
    }

    public FamilyMember(String name) {
        this.name = name;
    }

    public String toString() {
        return ("FM: " + getName());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the bornTo
     */
    public String getBornTo() {
        return bornTo;
    }

    /**
     * @param bornTo the bornTo to set
     */
    public void setBornTo(String bornTo) {
        this.bornTo = bornTo;
    }

    /**
     * @return the fatherOf
     */
    public String[] getFatherOf() {
        return fatherOf;
    }

    /**
     * @param fatherOf the fatherOf to set
     */
    public void setFatherOf(String[] fatherOf) {
        this.fatherOf = fatherOf;
    }

    /**
     * @return the known_As
     */
    public String getKnown_As() {
        return known_As;
    }

    /**
     * @param known_As the known_As to set
     */
    public void setKnown_As(String known_As) {
        this.known_As = known_As;
    }

    /**
     * @return the heldTitle
     */
    public String getHeldTitle() {
        return heldTitle;
    }

    /**
     * @param heldTitle the heldTitle to set
     */
    public void setHeldTitle(String heldTitle) {
        this.heldTitle = heldTitle;
    }

    /**
     * @return the wedTo
     */
    public String getWedTo() {
        return wedTo;
    }

    /**
     * @param wedTo the wedTo to set
     */
    public void setWedTo(String wedTo) {
        this.wedTo = wedTo;
    }

    /**
     * @return the eyeColor
     */
    public String getEyeColor() {
        return eyeColor;
    }

    /**
     * @param eyeColor the eyeColor to set
     */
    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * @return the hairColor
     */
    public String getHairColor() {
        return hairColor;
    }

    /**
     * @param hairColor the hairColor to set
     */
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * @return the house
     */
    public String getHouse() {
        return house;
    }

    /**
     * @param house the house to set
     */
    public void setHouse(String house) {
        this.house = house;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the fate
     */
    public String getFate() {
        return fate;
    }

    /**
     * @param fate the fate to set
     */
    public void setFate(String fate) {
        this.fate = fate;
    }

    //tengo que terminar los casos con toda la información necesaria para mostrar una persona con toda su informacion, de forma que se muestre toda la informacion en la interfaz
    public String ToString() {
        String strFamilyData = "familyMemberToString";
        strFamilyData = strFamilyData + "name: " + this.name + "\n";
        strFamilyData = strFamilyData + "Father to: (";
        if (fatherOf != null) {
            for (int i = 0; i < fatherOf.length - 1; i++) {
                strFamilyData = strFamilyData + fatherOf[i] + ",";
            }
            if (fatherOf.length >= 1) {
                strFamilyData = strFamilyData + fatherOf[fatherOf.length] + ") \n";
            }
        }
        return strFamilyData;
    }
}
