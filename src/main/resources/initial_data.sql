drop table EVENT_REGISTRATION if exists;
drop table EMPLOYEES if exists;
drop table EVENTS if exists;


create table EMPLOYEES (ID bigint generated by default as identity (start with 1), EMAIL varchar(255) not null, JOIN_DATE date not null, LAST_MODIFIED_BY varchar(255), LAST_MODIFIED_DATE varbinary(255), MID bigint not null, NAME varchar(255) not null, VERSION_NUMBER varchar(20), primary key (ID));
create table EVENTS (ID bigint generated by default as identity (start with 1), DESCRIPTION LONGVARCHAR  not null, EVENT_TITLE LONGVARCHAR  not null, LAST_MODIFIED_BY varchar(255), LAST_MODIFIED_DATE varbinary(255), VERSION_NUMBER bigint, primary key (ID));
create table EVENT_REGISTRATION (EVENT_REGISTRATION_ID bigint generated by default as identity (start with 1), EMPLOYEE_ID bigint not null, EVENT_ID bigint not null, LAST_MODIFIED_BY varchar(255), LAST_MODIFIED_DATE varbinary(255), VERSION_NUMBER bigint, primary key (EVENT_REGISTRATION_ID));
alter table EMPLOYEES add constraint UK_j7k2727lpyu5fc2mlbrwap5w5  unique (MID);
alter table EVENT_REGISTRATION add constraint UK_j7k2727lpyu5fc2mlbrwap676  unique (EMPLOYEE_ID,EVENT_ID);
alter table EVENT_REGISTRATION add constraint FK_mse1ooapyp728qgij9s88syf8 foreign key (EVENT_ID) references EVENTS;
alter table EVENT_REGISTRATION add constraint FK_mse1ooapyp728qgij9s88syf9 foreign key (EMPLOYEE_ID) references EMPLOYEES;

COMMIT;

INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'RAMESH@MINDDTREE', '2017-10-10', 1013624, 'RAMESH', NULL, NULL, NULL);
INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'PAUL@blacksystems', '2017-09-12', 1013654, 'PAUL', NULL, NULL, NULL);
INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'LEAN@blacksystems', '2017-08-01', 1013424, 'LEAN', NULL, NULL, NULL);
INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'KAMR@blacksystems', '2017-05-21', 1015424, 'KAMR', NULL, NULL, NULL);
INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'URYUI@blacksystems', '2017-11-30', 1078624, 'URYUI', NULL, NULL, NULL);
INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'TARA@blacksystems', '2017-12-31', 1017824, 'TARA', NULL, NULL, NULL);
INSERT INTO EMPLOYEES (ID, EMAIL, JOIN_DATE, MID, NAME, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) VALUES (DEFAULT, 'JAMES@blacksystems', '2017-09-19', 1056624, 'JAMES', NULL, NULL, NULL);

insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'FAMILY FUN DAY', 'your kids to be future engineer', NULL, NULL, NULL);
insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'Export Pricing Masterclass', 'Cost factors involved in marketing and delivering products and services to overseas customers, including the hidden.', NULL, NULL, NULL);
insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'Introduction to International Trade', 'The introductory workshop focuses on the key issues which occur when dealing with export enquiries and orders, discussing subjects such as the benefits of export and differences from domestic business. It will demonstrate legal, financial and logistical obstacles and how to mitigate these risks as well as how to trade safely overseas.', NULL, NULL, NULL);
insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'South West Dance Festival Coach Packages', 'All Coach Travel is booked through www.eventztravel.co.uk once your booking is made on here your details will be passed on to them and you can confirm the exact location of pick up from the list below.Tickets are coach tickets only, please select an event ticket separately as the coach ticket only will not gain you entry to the event.', NULL, NULL, NULL);
insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'Action for Children Baby Massage Course', 'Following the changes to Childrens Centres in Hampshire, Action for Childrens professionally trained staff are planning several Baby Massage Courses in the Farnborough, Fleet and Odiham area.These will carry a charge of £50 for a 5 week course (to cover costs).', NULL, NULL, NULL);
insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'Community Actions Fancy dress Fun Run for Positive Action', 'Welcome to The Positive Action Fancy Dress Fun Run at Frimley Lodge Park this September!The Event: We are hosting a charity fundraising walk around the parks Parkrun route taking in the natural beauty surrounding the Basingstoke Canal and the parks wide open green spaces.', NULL, NULL, NULL);
insert into EVENTS (ID, EVENT_TITLE, DESCRIPTION, LAST_MODIFIED_BY, LAST_MODIFIED_DATE, VERSION_NUMBER) values (default, 'THE LEAD GEN SUMMER SUMMIT', 'WE ARE BACK! After a successful event in January, get ready to kick start your summer with a major focus on hot, consistent leads for your business.', NULL, NULL, NULL)

COMMIT;