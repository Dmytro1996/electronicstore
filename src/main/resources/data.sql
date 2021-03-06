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
INSERT INTO brands(id,name) VALUES(11,"Belkin");
INSERT INTO brands(id,name) VALUES(12,"Meizu");

INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(1,"KD45XH80",20000,45,"FULL_HD",true,true,8);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(2,"UE7600",25000,45,"WQHD",true,true,1);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(3,"QE7500",30000,60,"WQHD",true,true,1);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(4,"QE5500",25000,45,"WQHD",true,true,1);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(5,"KD55XH90",20000,45,"FULL_HD",true,false,8);
INSERT INTO TV(id,model,price,screen_size,resolution,smart_tv,3D,brand_id) VALUES(6,"KD43xf7",20000,40,"FULL_HD",false,false,8);


INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(1,"Macbook Air",65000,15,"FULL_HD",16,1000,2);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(2,"Zenbook UX325JA",20000,15,"FULL_HD",16,1000,3);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(3,"Ideapad S145",15000,15,"FULL_HD",8,500,4);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(4,"Pavillon",13500,17,"FULL_HD",8,500,5);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(5,"Swift 5",15000,15,"FULL_HD",12,500,6);
INSERT INTO laptops(id,model,price,screen_size,resolution,oper_memory,internal_memory,brand_id)
 VALUES(6,"ThinkPad E480",25000,15,"WQHD",16,10000,4);

INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(1,"Iphone X",35000,5,"FULL_HD",4,128,0,1,16,true,2);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(2,"Iphone 7",10000,5,"FULL_HD",2,128,0,1,12,true,2);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(3,"A51",8600,6,"WQHD",8,128,512,2,48,true,1);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(4,"A31",6000,6,"FULL_HD",4,64,256,2,48,true,1);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(5,"M31",7500,6,"FULL_HD",6,128,512,2,64,true,1);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(6,"S20",35000,5,"WQHD",16,256,256,2,48,true,1);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(7,"16",5300,6,"WQHD",6,128,0,2,20,true,12);
INSERT INTO mobile_devices(id,model,price,screen_size,resolution,oper_memory,internal_memory,
external_memory,sim_count,camera,gps,brand_id) VALUES(8,"Redmi Note 9",7000,6,"WQHD",6,128,0,2,64,true,7);

INSERT INTO Accesories(id,model,price,type,short_description,brand_id) VALUES(1,"U20",300,"Earphones","Good earphones",9);
INSERT INTO Accesories(id,model,price,type,short_description,brand_id) VALUES(2,"M180",600,"Computer mouse","Good computer mouse",10);
INSERT INTO Accesories(id,model,price,type,short_description,brand_id) VALUES(3,"KL50",900,"Keyboard","Good keyboard",10);
INSERT INTO Accesories(id,model,price,type,short_description,brand_id) VALUES(4,"B10",900,"Bag for laptop","Good bag",11);

INSERT INTO Users(id,first_name,last_name,email,password,role) VALUES(1,"John","Smith","johnsmith@mail.com","Aa12345","ADMIN");
INSERT INTO Users(id,first_name,last_name,email,password,role) VALUES(2,"Sam","Samuelson","sam@mail.com","Bb23456","USER");
INSERT INTO Users(id,first_name,last_name,email,password,role) VALUES(3,"Ron","White","ronwhite@mail.com","Cc34567","USER");

INSERT INTO Orders(id,user_id,executed) VALUES(1,1,false);

INSERT INTO tv_ordered(order_id,tv_id) VALUES(1,5);

INSERT INTO laptops_ordered(order_id,laptop_id) VALUES(1,3);

INSERT INTO mobiles_ordered(order_id,mobile_id) VALUES(1,4);

INSERT INTO acc_ordered(order_id,acc_id) VALUES(1,2);