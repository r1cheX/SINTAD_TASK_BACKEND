/* Populate tables */

insert into tb_rol (nombre) values ('ADMIN');

insert into tb_usuario (email, password, username) values ('admin@gmail.com', 'admin', 'admin');

insert into tb_user_rol (user_id, rol_id) values (1, 1);

insert into tb_tipo_contribuyente (nombre, estado) values ('Natural Sin Negocio', 1);
insert into tb_tipo_contribuyente (nombre, estado) values ('Juridica', 1);
insert into tb_tipo_contribuyente (nombre, estado) values ('Natural Con Negocio', 1);
insert into tb_tipo_contribuyente (nombre, estado) values ('No Domiciliado', 1);

insert into tb_tipo_documento (codigo,nombre,descripcion, estado) values ('4','CARNET DE EXTRANJERIA','CARNET DE EXTRANJERIA', 1);
insert into tb_tipo_documento (codigo,nombre,descripcion, estado) values ('7','PASAPORTE','PASAPORTE', 1);
insert into tb_tipo_documento (codigo,nombre,descripcion, estado) values ('11','PARTIDA DE NACIMIENTO - IDENTIDAD','PARTIDA DE NACIMIENTO - IDENTIDAD', 1);
insert into tb_tipo_documento (codigo,nombre,descripcion, estado) values ('99','OTROS','OTROS', 1);
insert into tb_tipo_documento (codigo,nombre,descripcion, estado) values ('6','RUC','REGISTRO UNICO DEL CONTRIBUYENTE', 1);
insert into tb_tipo_documento (codigo,nombre,descripcion, estado) values ('1','DNI','DOCUMENTO NACIONAL DE IDENTIDAD', 1);

insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20505327552','SYL S.A.C','SYL CARGO NOMBRE COMERCIAL',1,'Jr. Comandante Jimenez Nro. 166 Int. a (entre Cuadra 7 y 8 Javier Padro Oeste)','79845612',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20543844838','PUNTUAL EXPRESS S.A.C.','',1,'MZA. F LOTE. 29 AS.RSD.MONTECARLO II LIMA - LIMA - SAN MARTIN DE PORRE','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'10410192999','ALVAREZ MACHUCA RENZO GUSTAVO','',3,'AV. LOS ALISOS MZA. G LOTE. 05 ASC. LA ALBORADA DE OQUENDO III ETAPA (CRUCE PTE OQUENDO CON AV.NESTOR GAMBETTA) PROV. CONST. DEL CALLAO - PROV. CONST. DEL CALLAO - CALLAO','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20600131037','CARNICOS MAFER S.A.C.','',2,'CAL.EL UNIVERSO NRO. 327 URB. LA CAMPIÑA ZONA CINCO (ALTURA ','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20556528218','SUMAQUINARIA S.A.C.','',2,'AV. M.SUCRE NRO. 455 DPTO. 603 LIMA - LIMA - MAGDALENA DEL MAR','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20545412528','OASIS FOODS S.A.C.','',2,'CAL. FRANCISCO MASIAS NRO. 370 URB. SAN EUGENIO (PISO 7) LIM','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20510620195','INVERSIONES PRO3 SAC','',2,'AV. AUTOPIDTA RAMIRO PRIALE LOTE. 02 A.V. PROP HUERTOS DE HU','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (3,'20498383361','REPUESTOS DAVID DIESEL E.I.R.L.','',2,'CAR.VIA EVITAMIENTO MZA. 857 LOTE. 7 SEC. IRRIGACION EL CURAL 1 AREQUIPA - AREQUIPA - CERRO COLORADO','',1);
insert into tb_entidad (id_tipo_documento,nro_documento,razon_social,nombre_comercial,id_tipo_contribuyente,direccion,telefono, estado) values (6,'CNAH00003','ANHUI HAYVO PROTECTIVE PRODUCT MANUFACTURING CO.,LTD','',4,'173 FENGLE AVENUE,ECNOMIC DEVELOPMENT ZONE,QUANJIAO COUNTY','',1);
