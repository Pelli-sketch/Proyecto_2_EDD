/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
public class Amo {
    
    public Amo() {
    this.isValid = false;
    }
    
    public String fullName = null;
    public String name = null;
    public String lastName = null;
    public String nombreUnico = null;
    public String house = null;
    public String numeral = null;
    public String father = null;
    public String mother = null;
    public String alias = null;
    public String title = null;
    public String wedTo = null;
    public String eyesColor = null;
    public String hairColor = null;
    public String[] children = null;
    public String notes = null;
    public String fate = null;
    
    public static final String LORD_TITLE = "Lord";
    public static final String HOUSE_TITLE = "House";
    public static final String NUMERAL_TITLE = "Of his name";
    public static final String UNKNOWN_TITLE = "[Unknown]";
    public static final String FATHER_TITLE = "Born to";
    public static final String MOTHER_TITLE = "Born to";
    public static final String ALIAS_TITLE = "Known as";
    public static final String TITLE_TITLE = "Held title";
    public static final String WED_TO_TITLE = "Wed to";
    public static final String EYES_COLOR_TITLE = "Of eyes";
    public static final String HAIR_COLOR_TITLE = "Of hair";
    public static final String CHILDREN_TITLE = "Father to";
    public static final String NOTES_TITLE = "Notes";
    public static final String FATE_TITLE = "Fate";
    
    public boolean isValid = false;
    
    public boolean parse(String json) {
        if (json == null) {
            return false;
        }
        if (json == "") {
            return false;
        }
        json = json.strip();
        if (!json.startsWith("{")) {
            return false;
        }
        if (!json.endsWith("}")) {
            return false;
        }
        // Quitar los espacios
        json = json.strip();
        // Quitamos las llaves al comienzo y al final
        if (json.startsWith("{")) {
            json = json.substring(1, json.length());
        }
        if (json.endsWith("}")) {
            json = json.substring(0, json.length() - 1);
        }
        // Quitar los espacios
        json = json.strip();
        // Quitar los espacios iniciales y finales
        json = json.strip();
        // Quitar las comillas
        json = json.replace("\"", "");
        // Quitar las llaves inicial y final
        if (json.startsWith("{")) {
            json = json.substring(1, json.length());
        }
        if (json.endsWith("}")) {
            json = json.substring(0, json.length() - 1);
        }
        // volvemos a quitar los espacios iniciales y finales
        json = json.strip();
        // Separar por dos puntos, para obtener el nombre completo y el resto de la
        // información
        String[] data = json.split(":", 2);
        fullName = data[0].strip();
        name = fullName.split(" ", 2)[0];
        lastName = fullName.split(" ", 2)[1];
        house = lastName;

        // Nos quedamos con el resto de la información
        json = data[1];
        // Volvemos a quitar los espacios iniciales y finales
        json = json.strip();
        if (json.startsWith("[")) {
            json = json.substring(1, json.length());
        } else {
            return false;
        }
        if (json.endsWith("]")) {
            json = json.substring(0, json.length() - 1);
        } else {
            return false;
        }
        // volvemos a quitar los espacios iniciales y finales
        json = json.strip();

        // Quitamos los \n y los \r ,
        json = json.replace("\n", "");
        json = json.replace("\r", "");

        // Las comas que están fuera de [] y fuera de {} , se reemplazan por \n
        int numCorchetes = 0;
        int numLlaves = 0;
        String jsonTxt = "";
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == '[') {
                numCorchetes++;
            }
            if (json.charAt(i) == ']') {
                numCorchetes--;
            }
            if (json.charAt(i) == '{') {
                numLlaves++;
            }
            if (json.charAt(i) == '}') {
                numLlaves--;
            }
            if (json.charAt(i) == ',' && numCorchetes == 0 && numLlaves == 0) {
                jsonTxt += "\n";
                continue;
            }
            jsonTxt += json.charAt(i);
        }
        // ahora, cada linea va a ser un campo que quiero recuperar
        String[] fields = jsonTxt.split("\n");

        // ahora vamos a ir campo por campo viendo como obtenemos los datos
        for (String field : fields) {
            // Quitar los espacios iniciales y finales
            field = field.strip();
            // Quitar las llaves inicial y final
            if (field.startsWith("{")) {
                field = field.substring(1, field.length());
            } else {
                return false;
            }
            if (field.endsWith("}")) {
                field = field.substring(0, field.length() - 1);
            } else {
                return false;
            }

            // obtenemos el nombre del campo y el valor del campo
            String[] fieldData = field.split(":", 2);
            // le quitamos espacios iniciales y finales
            String fieldTitle = fieldData[0].strip();
            String fieldValue = fieldData[1].strip();

            // ahora, vamos a ir viendo los campos y sus valores
            if (fieldTitle.equals(NUMERAL_TITLE)) {
                // si tenemos un numeral, tenemos un nombreUnico
                this.numeral = fieldValue;
                this.nombreUnico = this.fullName + ", " + this.numeral + " " + Amo.NUMERAL_TITLE.toLowerCase();
                continue;
            }
            if (fieldTitle.equals(FATHER_TITLE)) {
                if (fieldValue.equals(UNKNOWN_TITLE)) {
                    continue;
                }
                if (this.father == null) {
                    this.father = fieldValue;
                } else {
                    this.mother = fieldValue;
                }
                continue;
            }
            if (fieldTitle.equals(ALIAS_TITLE)) {
                this.alias = fieldValue;
                continue;
            }
            if (fieldTitle.equals(TITLE_TITLE)) {
                this.title = fieldValue;
                continue;
            }
            if (fieldTitle.equals(WED_TO_TITLE)) {
                this.wedTo = fieldValue;
                continue;
            }
            if (fieldTitle.equals(EYES_COLOR_TITLE)) {
                this.eyesColor = fieldValue;
                continue;
            }
            if (fieldTitle.equals(HAIR_COLOR_TITLE)) {
                this.hairColor = fieldValue;
                continue;
            }
            if (fieldTitle.equals(CHILDREN_TITLE)) {
                // el campo children es un array, por lo que necesitamos crear un arreglo con
                // los valores
                if (fieldValue.startsWith("[")) {
                    fieldValue = fieldValue.substring(1, fieldValue.length());
                }
                if (fieldValue.endsWith("]")) {
                    fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
                }
                fieldValue = fieldValue.strip();
                String[] childrenData = fieldValue.split(",");
                this.children = new String[childrenData.length];
                for (int i = 0; i < childrenData.length; i++) {
                    this.children[i] = childrenData[i].strip();
                }
                continue;
            }
            if (fieldTitle.equals(NOTES_TITLE)) {
                this.notes = fieldValue;
                continue;
            }
            if (fieldTitle.equals(FATE_TITLE)) {
                this.fate = fieldValue;
                continue;
            }
            return false;
        }
        this.isValid = true;
        return true;
    }    
    
    public Amo(String json) {
        this.isValid = false;
        if (!this.parse(json)) {
            this.isValid = false;
            return;
        }
        this.isValid = true;
    }
    
    public boolean equals(Amo amo) {
        if (amo == null) {
            return false;
        }
        if (this.alias != null && amo.alias != null) {
            if (Amo.comparadorAlias.compareTo(this, amo) == 0) {
                return true;
            }
        }
        if (Amo.comparadorNombreUnico.compareTo(this, amo) == 0) {
            return true;
        }
        return false;
    }
    public String DataImplementsToString() {
        String txt = "";
        if (!this.isValid) {
            txt = Amo.LORD_TITLE + ": null\n";
            return txt;
        }
        txt = Amo.LORD_TITLE + ": " + this.fullName + "\n";
        if (this.numeral != null) {
            txt += "\t" + Amo.NUMERAL_TITLE + ": " + this.numeral + "\n";
        }
        txt += "\t" + Amo.HOUSE_TITLE + ": " + this.house + "\n";
        if (this.father != null) {
            txt += "\t" + Amo.FATHER_TITLE + ": " + this.father + "\n";
        } else {
            txt += "\t" + Amo.FATHER_TITLE + ": " + UNKNOWN_TITLE.substring(1, UNKNOWN_TITLE.length() - 1) + "\n";
        }
        if (this.mother != null) {
            txt += "\t" + Amo.MOTHER_TITLE + ": " + this.mother + "\n";
        }
        if (this.alias != null) {
            txt += "\t" + Amo.ALIAS_TITLE + ": " + this.alias + "\n";
        }
        if (this.title != null) {
            txt += "\t" + Amo.TITLE_TITLE + ": " + this.title + "\n";
        }
        if (this.wedTo != null) {
            txt += "\t" + Amo.WED_TO_TITLE + ": " + this.wedTo + "\n";
        }
        txt += "\t" + Amo.EYES_COLOR_TITLE + ": " + this.eyesColor + "\n";
        txt += "\t" + Amo.HAIR_COLOR_TITLE + ": " + this.hairColor + "\n";
        if (this.children != null && this.children.length > 0) {
            txt += "\t" + Amo.CHILDREN_TITLE + ": \n";
            for (int i = 0; i < this.children.length; i++) {
                txt += "\t" + "\t" + (i + 1) + ". ";
                txt += this.children[i] + "\n";
            }
        }
        if (this.notes != null) {
            txt += "\t" + Amo.NOTES_TITLE + ": " + this.notes + "\n";
        }
        if (this.fate != null) {
            txt += "\t" + Amo.FATE_TITLE + ": " + this.fate + "\n";
        }
        return txt;
    }

    public String toString() {
        return this.nombreUnico;
    }
    
    
    public static AComparador<Amo> comparadorNombre = (Amo a, Amo b) -> a.name.toLowerCase()
            .compareTo(b.name.toLowerCase());
    public static AComparador<Amo> comparadorNombreUnico = (Amo a, Amo b) -> a.nombreUnico.toLowerCase()
            .compareTo(b.nombreUnico.toLowerCase());
    public static AComparador<Amo> comparadorNombreFull = (Amo a, Amo b) -> a.fullName.toLowerCase()
            .compareTo(b.fullName.toLowerCase());    
    public static AComparador<Amo> comparadorAlias = (Amo a, Amo b) -> {
        if (a.alias == null && b.alias == null) {
            return -1;
        }
        String alias1 = (a.alias == null) ? "" : a.alias.toLowerCase();
        String alias2 = (b.alias == null) ? "" : b.alias.toLowerCase();
        return alias1.compareTo(alias2);
    };
    public static AComparador<Amo> comparadorTitle = (Amo a, Amo b) -> {
        if (a.title == null && b.title == null) {
            return -1;
        }
        String title1 = (a.title == null) ? "" : a.title.toLowerCase();
        String title2 = (b.title == null) ? "" : b.title.toLowerCase();
        return title1.compareTo(title2);
    };
    public static AComparador<Amo> comparadorNombreUnicoComienzaPor = (Amo a, Amo b) -> {
        if (a.nombreUnico.toLowerCase().startsWith(b.nombreUnico.toLowerCase())) {
            return 0;
        }
        return -1;
    };
    public static AComparador<Amo> comparadorAliasComienzaPor = (Amo a, Amo b) -> {
        if (a.alias == null && b.alias == null) {
            return -1;
        }
        String alias1 = (a.alias == null) ? "" : a.alias.toLowerCase();
        String alias2 = (b.alias == null) ? "" : b.alias.toLowerCase();
        if (alias1.startsWith(alias2)) {
            return 0;
        }
        return -1;
    };
    
    public static AComparador<Amo> comparadorTitleComienzaPor = (Amo a, Amo b) -> {
        if (a.title == null && b.title == null) {
            return -1;
        }
        String title1 = (a.title == null) ? "" : a.title.toLowerCase();
        String title2 = (b.title == null) ? "" : b.title.toLowerCase();
        if (title1.startsWith(title2)) {
            return 0;
        }
        return -1;
    };
    
    
}
