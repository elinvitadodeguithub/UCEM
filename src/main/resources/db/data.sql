INSERT INTO categoria_cuarto (nombre,descripcion,precio)
    VALUES ('Individual', 'Ideal para quienes viajan solos.',50.0);

INSERT INTO cuarto (numero, descripcion,categoria)
    VALUES(1,'Vista a la piscina',1);
INSERT INTO cuarto (numero, descripcion,categoria)
  VALUES(2,'Remodelado recientemente',1);
  
INSERT INTO huesped(nombre, email, telefono)
VALUES('El invitado del hotel', '@elinvitado.com', '0000-0000');
INSERT INTO huesped(nombre, email, telefono)
VALUES('Familia peluche', '@peluche.com', '1111-1111');

INSERT INTO reservacion(desde,hasta,cuarto,huesped)
VALUES({ts '2017-02-27 00:00:00.0'},{ts '2017-02-28 00:00:00.0'},1,1)