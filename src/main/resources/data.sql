/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dmytr
 * Created: 20 лист. 2020
 */
INSERT INTO brands(id,name) VALUES(1,"Samsung");
INSERT INTO brands(id,name) VALUES(2,"Apple");
INSERT INTO brands(id,name) VALUES(3,"Asus");
INSERT INTO brands(id,name) VALUES(4,"Lenovo");
INSERT INTO brands(id,name) VALUES(5,"HP");
INSERT INTO brands(id,name) VALUES(6,"Acer");
INSERT INTO brands(id,name) VALUES(7,"Xiaomi");
INSERT INTO brands(id,name) VALUES(8,"Sony");
INSERT INTO brands(id,name) VALUES(9,"Ergo");
INSERT INTO brands(id,name) VALUES(10,"Logitech");

INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(1,"KWE4501",20000,45,"FULL_HD",true,true,8);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(2,"UE7600",25000,45,"WQHD",true,true,1);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(3," QE8800",30000,60,"WQHD",true,true,1);

INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(1,"Macbook",65000,15,"FULL_HD",16,1000,2);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(2,"Zenbook",20000,15,"FULL_HD",16,1000,3);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(3,"Ideapad",15000,15,"FULL_HD",8,500,4);

INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(1,"Iphone X",35000,5,"FULL_HD",4,128,0,1,16,true,2);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(2,"A51",20000,5,"FULL_HD",8,64,256,2,48,true,1);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(3,"S20",35000,5,"WQHD",16,256,256,2,48,true,1);

INSERT INTO Accesories(id,model,price,name,short_description,brand_id) VALUES(1,"U20",300,"Earphones","Good earphones",9);
INSERT INTO Accesories(id,model,price,name,short_description,brand_id) VALUES(2,"M180",600,"Computer mouse","Good computer mouse",10);
INSERT INTO Accesories(id,model,price,name,short_description,brand_id) VALUES(3,"U20",900,"Keyboard","Good keyboard",10);