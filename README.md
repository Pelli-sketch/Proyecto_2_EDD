# Proyecto #2 sobre Árboles

**Autores:**  
- Luis Noriega  
- Pablo Pellicioni  
- Sebastián Marval  

---
## Descripción del Diagrama de Clases




---

## Descripción del Proyecto

Este proyecto implementa un visor de árboles genealógicos diseñado para ayudar a analizar linajes. Permite navegar entre registros, mostrar detalles de integrantes, realizar búsquedas y visualizar generaciones o antepasados, todo con una interfaz gráfica intuitiva basada en **Java Swing**. El programa utiliza **GraphStream** para la visualización gráfica de los árboles.

### Funcionalidades
1. **Cargar Árbol Genealógico:** Permite cargar un archivo con datos del árbol genealógico y mostrarlo gráficamente.  
2. **Ver Registro de Integrantes:** Visualización de los datos de un nodo específico al hacer clic sobre él.  
3. **Buscar por Nombre:** Encuentra registros por nombre o mote, mostrando los detalles y el árbol descendiente correspondiente.  
4. **Mostrar Antepasados:** Visualiza los antepasados de un integrante en orden y permite explorar sus detalles.  
5. **Buscar por Título Nobiliario:** Enumera todos los integrantes con un título específico y permite ver sus detalles.  
6. **Lista de una Generación:** Permite obtener los integrantes de una generación dentro del linaje.  

---

## Aspectos Técnicos
- **Clase `Árbol (Tree)`**: Maneja la estructura del árbol genealógico.
- **Clase `Tabla de Dispersión (Hash Table)`**: Implementada para búsquedas eficientes con complejidad O(1).
- **Estructuras Auxiliares**: Diseñadas manualmente para optimizar tiempos de respuesta (no se permite el uso de librerías externas para datos abstractos).
- **Interfaz Gráfica**: Implementada con **Java Swing**, completamente funcional sin entrada o salida por consola.
- **Java JRE:** Se requiere al menos Java 8 para ejecutar el programa.  
- **NetBeans:** El proyecto fue desarrollado utilizando NetBeans IDE.  
- **Dependencias:** Incluye la librería GraphStream para visualizaciones.  
  - Descarga oficial: [GraphStream](https://graphstream-project.org/)  

---

## Uso del Programa

1. **Instalar JRE:** Asegúrate de tener el **Java Runtime Environment (JRE)** instalado en tu sistema.
2. **Cargar Archivo:** 
   - Abre el programa.
   - Usa la opción de menú para cargar un archivo JSON con el árbol genealógico.
3. **Interacción:** 
   - Haz clic en los nodos para explorar registros.
   - Usa el menú para buscar por nombre, mote, título, o generación.
4. **Exploración Gráfica:** Visualiza los linajes y explora antepasados y descendencia.

---
