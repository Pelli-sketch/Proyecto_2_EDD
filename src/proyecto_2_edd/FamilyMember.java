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
    String name;
    String bornTo;
    String[] fatherOf;
    String known_As;
    String heldTitle;
    String wedTo;
    String eyeColor;
    String hairColor;
    String house;
    String notes;
    String fate;

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

    public String toString(){
        return ("FM: " + name);
    }    
}