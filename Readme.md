 ## Instrucciones para usar repositorio localmente:
* Descargar repositorio
* Tener eclipse Oxygen 2 con java 1.8 con el plugin TestNG y Google Engine (opcional).
* Importar proyecto

* Para correrlo localmente con tomcat ejecutar: mvn tomcat7:run
* Para correr los test: Click derecho sobre la clase con los tests y poner Run with TestNG.

 ## Instrucciones para correr proyecto con docker:
* Descargar docker
* Descargar solo el archivo de docker: MLRequest/Dockerfile
* En la misma ruta del archivo Dockerfile ejecutar:
```
docker build -t mlrequest .
docker run -p 8080:8080 mlrequest
```

## Errores comunes:

* Maven invalid LOC header
Si tiene problemas para correr el proyecto con este error: 
```
Maven invalid LOC header (bad signature)
```
puede haber una libreria corrupa. Para solucionarlo eliminela y vuelva a correr clean install.

## Notas:
* Para generar el war se recomienda eliminar los archivos pdf del directorio.
* Por favor leer archivo Readme.pdf y Enunciado.pdf adjuntos.
