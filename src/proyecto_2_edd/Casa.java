/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
public class Casa {
    
    public final String HOUSE_TITLE = "House";
    
    public String fullname = null;
    public String name = null;
    
    public ArrayList<Amo> amos;
    
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

        // Separar por dos puntos, para obtener el nombre completo y el resto de la
        // información
        String[] data = json.split(":", 2);
        this.fullname = data[0].replace("\"", "").strip();
        this.name = this.fullname.split(" ", 2)[1];
        this.name = this.name.strip();

        // El resto del json para obtener los lords a partir de los objetos json de cada
        // uno.
        json = data[1].strip();

        // Quitamos los corchetes que abren y cierran.
        if (json.startsWith("[")) {
            json = json.substring(1, json.length());
        }
        if (json.endsWith("]")) {
            json = json.substring(0, json.length() - 1);
        }
        // Quitamos los espacios.
        json = json.strip();

        // Vamos a separar cada objeto json que representa a cada lord,
        // con un carácter especial, para luego poder hacer un split
        // y tener el json de cada lord.
        int numLLaves = 0;
        String jsonTxt = "";
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == '{') {
                numLLaves++;
            }
            if (json.charAt(i) == '}') {
                numLLaves--;
            }
            if (json.charAt(i) == ',' && numLLaves == 0) {
                jsonTxt += "//";
                continue;
            }
            jsonTxt += json.charAt(i);
        }

        // Ahora que tenemos todos los json de cada lord, los vamos a
        // agregar a la lista de lords
        String[] jsonArray = jsonTxt.split("//");
        for (int i = 0; i < jsonArray.length; i++) {
            Amo amo = new Amo(jsonArray[i]);
            if (!amo.isValid) {
                return false;
            }
            if (!this.amos.agregar(amo)) {
                return false;
            }
        }
        // Como la lista ya esta definida, compactamos para no tener espacios
        // vacíos en el array list.
        this.amos.compactar();
        return true;
    }    
    
    public Casa() {
        this.amos = new ArrayList<>(15, 10);
    }
    
    public Casa(String json) {
        this.amos = new ArrayList<>(15, 10);
        this.parse(json);
    }
    
    public void vaciar() {
        this.amos.vaciar();
        this.fullname = null;
        this.name = null;
    }

    /**
     * Devuelve una representación en texto de la casa.
     * 
     * @return una representación en texto de la casa
     */
    public String toString() {
        String txt = this.HOUSE_TITLE + ": " + this.name + "\n";
        for (int i = 0; i < this.amos.size(); i++) {
            String[] lines = this.amos.get(i).DataImplementsToString().split("\n");
            for (int j = 0; j < lines.length; j++) {
                txt += "\t" + lines[j] + "\n";
            }
            if (i < this.amos.size() - 1) {
                txt += "\n";
            }
        }
        return txt;
    }
    
}
