package proyecto_2_edd;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

/**
 *
 * @author sebma
 */
public class LectorJson {
//cambiar el retornpo para devolver el arbol familiar completo

    public static void leerJson(String nombreArchivo) throws Exception {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(nombreArchivo)) {

            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);

            if (tree.isJsonObject()) {
                JsonObject familyJO = tree.getAsJsonObject();
                Set<String> keys = familyJO.keySet();
                Object[] arrKeys = keys.toArray();
                for (Object k : arrKeys) {
                    // Clave: Nombre de la casa
                    System.out.println(k);
                    // Contenido: arreglo de personas
                    String kStr = (String) k;
                    JsonArray miembros = familyJO.getAsJsonArray(kStr);
                    System.out.println(miembros);
                    for (JsonElement element : miembros) {
                        if (element.isJsonObject()) {
                            System.out.println(element);

                            JsonObject jsonFM = element.getAsJsonObject();
                            Set<String> kNames = jsonFM.keySet();
                            Object[] arrKNames = kNames.toArray();
                            //Recorro cada miembro de la familia
                            for (Object kn : arrKNames) {
                                // Clave: Nombre de una persona
                                String knStr = (String) kn;
                                JsonArray propiedades = jsonFM.getAsJsonArray(knStr);
                                int i = 0;
                                String strOHN = null;
                                String strBorn = null;
                                String strPadre = null;
                                String strMadre = null;
                                String strKTA = null;
                                String strHT = null;
                                String strOfEyes = null;
                                String strOfHair = null;
                                String strWed = null;
                                String strNotes = null;
                                String strFate = null;
                                JsonArray hijosJson = null;
                                for (Object prop : propiedades) {        
                                    String strPropName = propiedades.get(i).getAsJsonObject().keySet().iterator().next();
                                    System.out.println("\n clave de la propiedad: " + strPropName);
                                    switch (strPropName) {
                                        case "Of his name":
                                            strOHN = propiedades.get(i).getAsJsonObject().get("Of his name").getAsString();
                                            System.out.println("Of his name: " + strOHN);
                                            break;

                                        case "Born to":
                                            //debo poder llenar dos veces esto, para padre y madre
                                            strBorn = propiedades.get(i).getAsJsonObject().get("Born to").getAsString();
                                            if (strPadre == null) {
                                                strPadre = strBorn;
                                            } else {
                                                strMadre = strBorn;
                                            }

                                            System.out.println(" Born to: " + strBorn);
                                            break;
                                        case "Known throughout as":
                                            strKTA = propiedades.get(i).getAsJsonObject().get("Known throughout as").getAsString();
                                            System.out.println("Known throught as: " + strKTA);
                                            break;
                                        case "Held title":
                                            strHT = propiedades.get(i).getAsJsonObject().get("Held title").getAsString();
                                            System.out.println("Held title: " + strHT);
                                            break;
                                        case "Of eyes":
                                            strOfEyes = propiedades.get(i).getAsJsonObject().get("Of eyes").getAsString();
                                            System.out.println("Of eyes: " + strOfEyes);
                                            break;
                                        case "Of hair":
                                            strOfHair = propiedades.get(i).getAsJsonObject().get("Of hair").getAsString();
                                            System.out.println("Of hair: " + strOfHair);
                                            break;
                                        case "Wed to":
                                            strWed = propiedades.get(i).getAsJsonObject().get("Wed to").getAsString();
                                            System.out.println("Wed to: " + strWed);
                                            break;
                                        case "Notes":
                                            strNotes = propiedades.get(i).getAsJsonObject().get("Notes").getAsString();
                                            System.out.println("Notes: " + strNotes);
                                            break;
                                        case "Fate":
                                            strFate = propiedades.get(i).getAsJsonObject().get("Fate").getAsString();
                                            System.out.println("Fate: " + strFate);
                                            break;
                                        case "Father to":
                                            hijosJson = propiedades.get(i).getAsJsonObject().get("Father to").getAsJsonArray();
                                            String[] hijos = new String[hijosJson.size()];
                                            int l = 0;
                                            for (JsonElement e : hijosJson) {
                                                hijos[l] = e.toString();
                                                System.out.println("hijo: " + hijos[l]);
                                                l++;

                                            }
                                            System.out.println("hijosJ " + hijosJson);

                                            break;

                                    }

                                    i = i + 1;
                                }

//Crear elementos 
     

                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
