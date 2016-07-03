insert into category(name, sortNr) values('Elektromotor-Prüfstand', 1);
insert into category(name, sortNr) values('Inverter Testsystem', 2);
insert into category(name, sortNr) values('Turbolader Prüfstand', 3);
insert into category(name, sortNr) values('Einspritz-Testsystem', 4);
insert into category(name, sortNr) values('Verbrennungsmotor-Prüfstand', 5);
insert into category(name, sortNr) values('End-of-line Testsystem', 6);
insert into category(name, sortNr) values('Real-life Testing', 7);
insert into category(name, sortNr) values('Antriebsstrang-Prüfsystem', 8);
insert into category(name, sortNr) values('Abgassmesssystem', 9);
insert into category(name, sortNr) values('Fuel and Lube Test Systems', 10);
insert into category(name, sortNr) values('Rollenprüfstand', 11);
insert into category(name, sortNr) values('Racing Test Systems', 12);

create table letters(col1 varchar(1))
ENGINE=MEMORY;

insert into letters values ('A');
insert into letters values ('B');
insert into letters values ('C');
insert into letters values ('D');
insert into letters values ('E');

insert into article (name, price, description, categoryId, insDt, insUserId)
select concat(cat.name, ' ', let.col1), FLOOR(RAND()*1000)*100, concat('Beschreibung für ', cat.name, ' ', let.col1),cat.categoryId, now(), 0 from category cat join letters let;

select * from customer;

insert into customer(companyName, street, houseNr, postCode, city, contactFirstName, contactLastName, contactEmail, contactGender, contactPhoneNr, insDt, insUserId)
values ('PriceRite Warehouse Club', 'Waßmannsdorfer Chaussee', '32', '22769', 'Hamburg', 'Maik', 'Ritter', 'MaikRitter@warehouse.com', 'M', '040 58 42 80',now(), 0);

insert into customer(companyName, street, houseNr, postCode, city, contactFirstName, contactLastName, contactEmail, contactGender, contactPhoneNr, insDt, insUserId)
values ('Super Place', 'Joachimstaler Str.', '88', '56759', 'Laubach', 'Bernd', 'Trommler', 'BerndTrommler@superplace.com', 'M', '06762 61 78 41',now(), 0);

insert into customer(companyName, street, houseNr, postCode, city, contactFirstName, contactLastName, contactEmail, contactGender, contactPhoneNr, insDt, insUserId)
values ('Sofa Express', 'Dreiheiligenstrasse', '81', '5232', 'Hilprechtsham', 'Antje', 'Schmitz', 'AntjeSchmitz@sofaexpress.de', 'F', '0660 347 81 46',now(), 0);

insert into customer(companyName, street, houseNr, postCode, city, contactFirstName, contactLastName, contactEmail, contactGender, contactPhoneNr, insDt, insUserId)
values ('Bugle Boy', 'Kimpling', '2', '8113', 'Södingberg', 'Sabine', 'Wolf', 'SabineWolf@bugleboy.com', 'F', '0699 694 57 71',now(), 0);

insert into customer(companyName, street, houseNr, postCode, city, contactFirstName, contactLastName, contactEmail, contactGender, contactPhoneNr, insDt, insUserId)
values ('Maloleys Finer Foods', 'Schönaugasse', '67', '3124', 'Unterwölbling', 'Marcel', 'Ritter', 'MarcelRitter@maloleys.com', 'M', '0650 511 81 73',now(), 0);

insert into customer(companyName, street, houseNr, postCode, city, contactFirstName, contactLastName, contactEmail, contactGender, contactPhoneNr, insDt, insUserId)
values ('Pomeroys', 'Clousson Road', '3255', '77002', 'Houston', 'Stephanie', 'Kratz', 'StephanieRKratz@pomeroys.com', 'F', '713-230-3629',now(), 0);


-- passwort: 'password'

insert into employee (username, password, firstname, lastname, email)
values('danhar', '$2a$04$bVurSMKjTDk/nVNkbiUvQuKw6NxTsXGuvCyYQ/2ebfnze036mEWSe', 'Daniel', 'Hartl', 'daniel.hartl@salesmate.at');

insert into employee (username, password, firstname, lastname, email)
values('phigro', '$2a$04$bVurSMKjTDk/nVNkbiUvQuKw6NxTsXGuvCyYQ/2ebfnze036mEWSe', 'Philipp', 'Grobelscheg', 'philipp.grobelscheg@salesmate.at');

insert into employee (username, password, firstname, lastname, email)
values('micgmo', '$2a$04$bVurSMKjTDk/nVNkbiUvQuKw6NxTsXGuvCyYQ/2ebfnze036mEWSe', 'Michael', 'Gmoser', 'michael.gmoser@salesmate.at');

insert into employee (username, password, firstname, lastname, email)
values('phites', '$2a$04$bVurSMKjTDk/nVNkbiUvQuKw6NxTsXGuvCyYQ/2ebfnze036mEWSe', 'Philipp', 'Teschl', 'philipp.teschl@salesmate.at');

insert into employee (username, password, firstname, lastname, email)
values('thofas', '$2a$04$bVurSMKjTDk/nVNkbiUvQuKw6NxTsXGuvCyYQ/2ebfnze036mEWSe', 'Thomas', 'Fasswald', 'thomas.fasswald@salesmate.at');

drop table letters;