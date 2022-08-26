# Proyecto1
Ingenieria en sistemas - Programacion II.
Natanael Eleazar Escobar Monzon--- 7690-16-16333

PROYECTO 1 OPEN SOURCE TICKET REQUEST SYSTEM (OTRS)
DESCRIPCION:
En este proyecto se realizara el proceso de la creacion de ticket asi como el gestionamiento dentro de las diferentes mesas que existen dentro de la empresa, se trabajara el tema de reporteria en 3 formas especificas, y se tendra la opcion de cargar un archivo con informacion predefinido para la demostracion de la funcionalidad del sistema.

DIAGRAMA DE CLASES A IMPLEMENTAR
![image](https://user-images.githubusercontent.com/109691088/186983735-af125812-8cfd-4110-b306-2adc286b632b.png)


MANUAL DE USUARIO:

MODULO 1 - SUBIR ARCHIVO PREDEFINIDO  
En esta opcion el usuario debe de elegir la opcion 1 del menu que se muestra al iniciar el sistema y automaticamente se le asignaran datos predefinidos para que pueda iniciar a trabajar los ticket.

MODULO 2 - CREAR NUEVO TICKET
Aca el usuario cuando encuentre un error dentro del sistema de su empresa recurrira al uso del sistema OTRS, el debe de seleccionar la opcion 2 del menu, con ello el sistema le solicitara su numero de nit y el detalle del error que se generara. una vez ingresado los datos debe de presionar enter para que el sistema le genere el ticket, automaticamente se le genera un estado Iniciado y un correlativo automatico del sistema.

MODULO 3 - TRABAJAR TICKET
Para poder trabajar un ticket debera de indicar si nit de soporte y su rol de lo cual se le presentara un menu para que pueda seleccionar el numero de rol que le pertenece. una vez ingresaso al sistema debera de elegir una opcion del nuevo menu en pantalla
  3.1) si usted es de la mesa de apoyo al solictar un ticket se le asignara el primero que fue ingresado por lo cual tendra un menu nuevamente para procesar los datos, del cual podra solucionarlo a trasladarlo a la mesa Tecnica.
  3.2) si usted es de la mesa Tecnica al solictar un ticket se le asignara el ultimo que fue ingresado por lo cual tendra un menu nuevamente para procesar los datos, del cual podra solucionarlo a trasladarlo a la mesa de desarrollo.
  3.3) si usted es de la mesa Desarrollo al solictar un ticket se genereara un ticket aleatorio y no podra trasladarlo a ningun lado solo podra solucionarlo y escribir un mensaje de la solucion.
  
 MODULO 4 - REPORTERIA. LISTA GENERAL DE TICKET CON BITACORA
 este modulo le mostrara el detalle de cada ticket y su bitacora si es que ya fue procesado por alguna de las mesas del sistema
 
 MODULO 5 - REPORTERIA. LISTA DE TICKET POR COLA
 este modulo le mostrara los ticket que se encuentra en cada cola con sus tedalles individuales.
 
 MODULO 6 - REPORTERIA. LISTA DE TICKET POR USUARIO
 este modulo le mostrara los ticket que se han creado por usuario.
 
 
 
