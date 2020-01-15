delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;

insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');

insert into Taco(id, created_at, name)
values (1, CURRENT_TIMESTAMP(), 'My taco');
insert into Taco(id, created_at, name)
values (2, CURRENT_TIMESTAMP(), 'Complete taco');

insert into user(id,username, password, fullname, street, city, state, zip, phone_number)
values (10,'mechague', '123456', 'Marcos Echagüe', '34th NW', 'Asuncion', 'AS','5215', '595984885421');


insert into Taco_Order(id, cccvv, cc_expiration, cc_number, city, name, placed_at, state, street, zip, user_id)
values (1,'455','01/25','4111111111111111','Asuncion', 'Marcos Echagüe', CURRENT_TIMESTAMP(), 'AS', 'St. 40 SW', '0000', 10);

insert into Taco_Order_Tacos(order_id, tacos_id)
values (1,1);
insert into Taco_Order_Tacos(order_id, tacos_id)
values (1,2);


