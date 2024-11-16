package proyecto_2_edd;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.JOptionPane;


class FamilyMember {
    String name;
    String[] bornTo;
    String[] fatherOf;
    String known_As;
    String heldTitle;
    String wedTo;
    String eyeColor;
    String hairColor;
    String house;
    String notes;
    String fate;

    public FamilyMember(String name, String[] bornTo, String[] fatherOf, String known_As, String heldTitle, String wedTo, String eyeColor, String hairColor,  String house, String notes, String fate) {
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
}


