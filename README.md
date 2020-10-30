# Programación avanzada  II - Grupo 1

## Enlaces externos:

- Panel: https://newserv.freewha.com/freewha.cpanel

- Cliente: http://pagrupo1.freeoda.com/ftp/

- PHPMyAdmin: http://pagrupo1.freeoda.com/pma/index.php

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
	5. Utilizamos "git remote add <nombre> <URL>" sin las comillas (Agregamos el repositorio remoto a la info).
	Un ejemplo sería: "git remote add main https://github.com/AxelNegro/PA2_Grupo1.git".
	
	Con esto ya tendríamos configurado el git para usarlo localmente.

## Pasos a seguir para hacer un Pull luego de cambiar algo (Actualizar el proyecto):
 
	1. Abrimos CMD.
	2. Utilizar el comando cd para ubicarnos en la carpeta del proyecto. Un ejemplo sería: "cd C:\Users\Axel\Desktop\PA2_Grupo1-main".
	3. Utilizamos el comando "git init" sin las comillas.
	4. Utilizamos el comando "git add <Ruta local>" sin las comillas (Le estamos diciendo a git donde tenemos el repositorio local).
	Un ejemplo sería: "git add C:\Users\Axel\Desktop\PA2_Grupo1-main".
	5. Si ya tenemos configurado el repositorio localmente, simplemente tendríamos que utilizar el siguiente comando:
	"git pull main HEAD:main --allow-unrelated-histories" sin las comillas.
	
	De esta forma obtendriamos el repositorio que está en GitHub.
	
## Pasos a seguir para hacer un Push (Enviar modificaciones al repositorio remoto):

	1. Abrimos CMD.
	2. Utilizar el comando cd para ubicarnos en la carpeta del proyecto. Un ejemplo sería: "cd C:\Users\Axel\Desktop\PA2_Grupo1-main".
	3. Utilizamos el comando "git init" sin las comillas.
	4. Utilizamos el comando "git add <Ruta local>" sin las comillas (Le estamos diciendo a git donde tenemos el repositorio local).
	Un ejemplo sería: "git add C:\Users\Axel\Desktop\PA2_Grupo1-main".
	5. Utilizamos el comando "git commit -m <Texto del commit entre comillas dobles>". 
	Un ejemplo sería: 'git commit -m "Agregada info al Readme.md"'.
	6. Si ya tenemos configurado el repositorio localmente, simplemente tendríamos que utilizar el siguiente comando:
	"git push main HEAD:main" sin las comillas.
	
	De esta forma habríamos enviado los archivos al repositorio remoto.

## Entregas:

- Entrega 1: Documentación | **Hecha.**

- Entrega 2: Pantallas. | **Hecha.**

- Entrega 3: Final. | **Actual.**

Se deberá entregar el proyecto completo y funcionando. La entrega se realizará vía zoom, a cada grupo se le asignará un horario. Se deberá presentar el grupo completo, durante la presentación los integrantes del grupo serán evaluados de manera individual. En caso de que decidan presentarse en el recuperatorio por favor avisar, así no se les asigna horario. Tener en cuenta que si se presentar en la última fecha de recuperatorio, ya no habrá otra instancia para poder evaluarlos en caso de que desaprueben por ende desaprobarán la materia.

Fecha límite de entrega: Lunes 16/11/2020.

## Pantallas:

- Tipo Usuario – Consumidor:

	- Usuario:
		- Alta de usuario. **(Hecha)**
		- Modificación de usuario. **(Hecha)**
		- Validación de usuario. **(Hecha)**
		- Listado de datos de usuario. **(Hecha)**
		
	- Señas:
		- Listado de señas. **(Hecha)**
		- Búsqueda de señas. **(Hecha)**
		
	- Niveles:
		- Listado de niveles. **(Hecha)**
		
	- Instituto:
		- Búsqueda de institutos. **(Hecha)**
		
- Tipo Usuario – Administrador:

	- Usuario:
		- Baja y Modificación de usuarios. **(Hecha)**
		- Listado de usuarios **(Hecha)**
		
	- Consignas:
		- Alta de consignas. **(Hecha)**
		- Modificación de consignas. **(Hecha)**
		- Baja y Listado de consignas. **(Hecha)**
		
	- Opciones:
		- Alta de opciones. **(Hecha)**
		- Modificación de opciones. **(Hecha)**
		- Baja y Listado de opciones. **(Hecha)**


## Tablas:

- Usuarios:
  - Usuario.
  - Contraseña.
  - Nombre.
  - Apellido.
  - Email.
  - Estado.
  - Tipo.
  
- Señas:
  - IdSeña.
  - Seña.
  - URL_Imagen.
  - Descripción.
  
- Niveles:
  - IdNivel.
  - Nivel.
  
- Consignas:
  - IdConsigna.
  - Descripción.
  - Estado.
  
- Orden_Nivel:
  - IdNivel.
  - IdSena.
  - IdNivel.
  - Orden.
  
- Niveles x Usuario:
  - Usuario.
  - IdNivel.
  
- Consignas x Usuario:
  - Usuario.
  - IdConsigna.
  
- Opciones x Consignas:
  - IdConsigna.
  - IdOpcion.

- Opciones:
  - IdOpcion.
  - Descripción.
  - Resultado.
  - Estado.


