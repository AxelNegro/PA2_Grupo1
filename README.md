# Programación avanzada  II - Grupo 1

## Información útil:

- Diccionario de señas: http://manosquehablan.com.ar/diccionario/

- Guardar datos en una base de datos online MySQL desde una aplicacion Android: https://www.youtube.com/watch?v=_YjNXTybueY

- Como asignar la Llave foranea en phpMyAdmin: https://www.youtube.com/watch?v=JWTpaxkNLW8

- Mostrar datos de MySQL en tabla html con php: https://www.youtube.com/watch?v=nPAp-gT5gPI

- Realizar Push a un repositorio GitHub desde Android Studio: https://www.youtube.com/watch?v=-dAr6VnmomM

## Pasos a seguir para configurar el repositorio localmente:

	1. Descargamos el proyecto de GitHub.
	2. Abrimos CMD.
	3. Utilizar el comando cd para ubicarnos en la carpeta del proyecto. Un ejemplo sería: "cd C:\Users\Axel\Desktop\PA2_Grupo1-main".
	4. Utilizamos el comando "git init" sin las comillas (Esto va a crear una carpeta oculta llamada .git con la info que vamos a guardar acá).
	5. Utilizamos el comando "git add <Ruta local>" sin las comillas (Le estamos diciendo a git donde tenemos el repositorio local).
	Un ejemplo sería: "git add C:\Users\Axel\Desktop\PA2_Grupo1-main".
	6. Utilizamos "git remote add <nombre> <URL>" sin las comillas (Agregamos el repositorio remoto a la info).
	Un ejemplo sería: "git remote add main https://github.com/AxelNegro/PA2_Grupo1.git".
	
	Con esto ya tendríamos configurado el git para usarlo localmente.

## Pasos a seguir para hacer un Pull luego de cambiar algo(Actualizar el proyecto):
 
	1. Abrimos CMD.
	2. Utilizar el comando cd para ubicarnos en la carpeta del proyecto. Un ejemplo sería: "cd C:\Users\Axel\Desktop\PA2_Grupo1-main".
	3. Utilizamos el comando "git init" sin las comillas.
	4. Si ya tenemos configurado el repositorio localmente, simplemente tendríamos que utilizar el siguiente comando:
	"git pull main HEAD:main --allow-unrelated-histories" sin las comillas.
	
	De esta forma obtendriamos el repositorio que está en GitHub.

## Entregas:

- Entrega 1: Documentación | **Hecha.**

- Entrega 2: Pantallas. | **Actual.**

Se deberá subir al aula virtual:

A.   Un archivo que contenga: las impresiones de pantallas de cada layout del TP integrador junto con la explicación de la funcionalidad que se realiza en dicha pantalla.

B.   El proyecto realizado en Android Studio, por favor aclarar para que dispositivo realizaron la aplicación.

Fecha de entrega: lunes 26/10/2020 23:55 hs.

- Entrega 3: Final.

Se deberá entregar el proyecto completo y funcionando. La entrega se realizará vía zoom, a cada grupo se le asignará un horario. Se deberá presentar el grupo completo, durante la presentación los integrantes del grupo serán evaluados de manera individual. En caso de que decidan presentarse en el recuperatorio por favor avisar, así no se les asigna horario. Tener en cuenta que si se presentar en la última fecha de recuperatorio, ya no habrá otra instancia para poder evaluarlos en caso de que desaprueben por ende desaprobarán la materia.

Fecha límite de entrega: Lunes 16/11/2020.

## Tablas:

- Usuarios:
  - IdUsuario.
  - Usuario.
  - Contraseña.
  - Nombre.
  - Apellido.
  - Email.
  - Ubicación.
  - Estado.
  
- Señas:
  - IdSeña.
  - Seña.
  - Descripción.
  
- Niveles:
  - IdNivel.
  - Nivel.
  
- Consignas:
  - IdConsigna.
  - IdSeña.
  - Descripción.
  
- Niveles x Usuario:
  - IdUsuario.
  - IdNivel.
  
- Consignas x Usuario:
  - IdUsuario.
  - IdConsigna.
  
- Señas x Nivel:
  - IdSeña.
  - IdNivel.
  
- Consignas x Niveles:
  - IdNivel.
  - IdConsigna.
  
- Respuestas x Consignas:
  - IdConsigna.
  - IdRespuesta.
  - Descripción.
  - Resultado.


