/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
/**
 * Representa una casa con una lista dinámica de amos y funcionalidad para 
 * procesar datos desde una cadena JSON.
 */
public class Casa {
    
    public final String HOUSE_TITLE = "House";
    
    public String fullname = null;
    public String name = null;
    
    public ListaDinamica<Amo> amos;
    /**
     * Procesa una cadena JSON para inicializar los datos de la casa y sus amos.
     * 
     * @param json la cadena JSON a procesar.
     * @return true si el JSON fue procesado exitosamente, false en caso contrario.
     */    
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

        // El resto del json para obtener los amos a partir de los objetos json de cada
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

        // Vamos a separar cada objeto json que representa a cada amo,
        // con un carácter especial, para luego poder hacer un split
        // y tener el json de cada amo.
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

        // Ahora que tenemos todos los json de cada amo, los vamos a
        // agregar a la lista de amos
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
        this.amos.compactarArreglo();
        return true;
    }    
    
    public Casa() {
        this.amos = new ListaDinamica<>(15, 10);
    }
    /**
     * Constructor que inicializa la lista de amos y procesa una cadena JSON.
     * 
     * @param json la cadena JSON que describe la casa y sus amos.
     */    
    public Casa(String json) {
        this.amos = new ListaDinamica<>(15, 10);
        this.parse(json);
    }
    
    public void vaciarLista() {
        this.amos.vaciarLista();
        this.fullname = null;
        this.name = null;
    }

    /**
     * Devuelve una representación textual de la casa y sus amos.
     * 
     * @return una cadena que representa la casa y su lista de amos.
     */
    public String toString() {
        String txt = this.HOUSE_TITLE + ": " + this.name + "\n";
        for (int i = 0; i < this.amos.size(); i++) {
            String[] lines = this.amos.obtener(i).DataImplementsToString().split("\n");
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
