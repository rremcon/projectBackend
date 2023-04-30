
-- AUTHORITIES

INSERT INTO authorities(username, authority) VALUES ('user', 'USER');
INSERT INTO authorities(username, authority) VALUES ('admin', 'ADMIN');


-- USERS

INSERT INTO users(email, username, password) VALUES ('user@mail.nl','user', '$2a$12$zkTtwaYcMUThSEUzOI0rIevyRPThSVGuNTYxEro.x3vcZjzC21bbG');
INSERT INTO users(email, username, password) VALUES ('admin@mail.nl','admin', '$2a$12$9jNM/uqC9Mp1szpqPfCT7eBvQ5qtuAgatBO/wMpL/lzx33pTQmHHO');
INSERT INTO users(email, username, password) VALUES ('h.lang@live.nl','gpfan', '$2a$12$hLjEBB0Qz1O8mpQvZv/bkOpGqmG49a86roVP1iypWOz7u7TYb/AHe');
INSERT INTO users(email, username, password) VALUES ('janegrey@gmail.com','racefan', '$2a$12$NsItI5orQNm1sab34aXvzenXy9uaAAorm7UjT65mFFC/4JhD75SsC');
INSERT INTO users(email, username, password) VALUES ('l.hollister@live.com','racingfan', '$2a$12$z04XeDco01xIJcUcfkAAcu3YKx/sFPAeGEqp0daPLgAyio6uRh1.C');
INSERT INTO users(email, username, password) VALUES ('mitch.hitch@outlook.com','racingaddict', '$2a$12$4m9/ZxtuYdxpziOZX87d/eh4GMMgWnm3eJEj19c3Mj7SqafBYPoUS');


-- ACCOUNTS

INSERT INTO accounts(id, firstname, lastname, birthdate, address, zipcode, city, country, email, username, password, user_username) VALUES (75968, 'useruser', 'user', '15-08-1972', 'office', 12345, 'Eindhoven', 'Netherlands', 'user@mail.nl', 'user', '$2a$12$zkTtwaYcMUThSEUzOI0rIevyRPThSVGuNTYxEro.x3vcZjzC21bbG', 'user');
INSERT INTO accounts(id, firstname, lastname, birthdate, address, zipcode, city, country, email, username, password, user_username) VALUES (87739, 'adminadmin', 'admin', '15-08-1972', 'office', 12345, 'Eindhoven', 'Netherlands', 'admin@mail.nl', 'admin', '$2a$12$9jNM/uqC9Mp1szpqPfCT7eBvQ5qtuAgatBO/wMpL/lzx33pTQmHHO', 'admin');
INSERT INTO accounts(id, firstname, lastname, birthdate, address, zipcode, city, country, email, username, password) VALUES (75746, 'Hilbert', 'Lang', '15-08-1972', 'Hoekstraat', 64787, 'Utrecht', 'Netherlands', 'h.lang@live.nl', 'gpfan', '$2a$12$hLjEBB0Qz1O8mpQvZv/bkOpGqmG49a86roVP1iypWOz7u7TYb/AHe');
INSERT INTO accounts(id, firstname, lastname, birthdate, address, zipcode, city, country, email, username, password) VALUES (43569, 'Jane', 'Grey', '08-11-2000', 'Streetlane', 93788, 'Vancouver', 'Canada', 'janegrey@gmail.com', 'racefan', '$2a$12$NsItI5orQNm1sab34aXvzenXy9uaAAorm7UjT65mFFC/4JhD75SsC');
INSERT INTO accounts(id, firstname, lastname, birthdate, address, zipcode, city, country, email, username, password) VALUES (98768, 'Luuk', 'Hollister', '17-12-1987', 'Upperstreet', 54978, 'Miami', 'USA', 'l.hollister@live.com', 'racingfan', '$2a$12$z04XeDco01xIJcUcfkAAcu3YKx/sFPAeGEqp0daPLgAyio6uRh1.C');
INSERT INTO accounts(id, firstname, lastname, birthdate, address, zipcode, city, country, email, username, password) VALUES (98879, 'Mitch', 'Hitch', '29-06-1999', 'Streetedge', 66978, 'London', 'UK', 'mitch.hitch@outlook.com', 'racingaddict', '$2a$12$4m9/ZxtuYdxpziOZX87d/eh4GMMgWnm3eJEj19c3Mj7SqafBYPoUS');


-- PRODUCTS

INSERT INTO products(id, img, title, description, price) VALUES (1, decode('bWF4X3ZlcnN0YXBwZW5fY2FwLmpwZw==','base64'), 'Red Bull Cap', 'Flex-Fit Black', 39.95);
INSERT INTO products(id, img, title, description, price) VALUES (2, decode('bWF4X3ZlcnN0YXBwZW5fdC1zaGlydC5qcGc=','base64'), 'Max Verstappen T-Shirt', 'Nr.1 with red-line', 32.50);
INSERT INTO products(id, img, title, description, price) VALUES (3, decode('aG9vZGllX3JlZF9idWxsLmpwZw==','base64'), 'Hoodie Red-Bull', 'blue/red', 59.99);
INSERT INTO products(id, img, title, description, price) VALUES (4, decode('cG9sb19yZWRfYnVsbC5qcGc=','base64'), 'Polo Red-Bull', 'Basic color dark blue', 25.00);
INSERT INTO products(id, img, title, description, price) VALUES (5, decode('bXZfY2hhbXBpb25fcHVsbG92ZXIuanBn','base64'), 'MV Champion Pullover', 'Dark blue with gold color', 74.99);
INSERT INTO products(id, img, title, description, price) VALUES (6, decode('dmVyc3RhcHBlbl9zd2VhdHNoaXJ0X3JiLmpwZw==','base64'), 'Verstappen Sweatshirt RB', 'Grey light with dutch flag', 35.95);
INSERT INTO products(id, img, title, description, price) VALUES (7, decode('cmJfZjFfY2FyX3NjYWxlbW9kZWwuanBn','base64'), 'Red Bull F1-Car ScaleModel', 'RB18 Nr.1', 68.99);
INSERT INTO products(id, img, title, description, price) VALUES (8, decode('cmVkX2J1bGxfcHVsbG92ZXIuanBn','base64'), 'Red Bull Pullover', 'Basic blue with Red Bull Racing text', 39.95);
INSERT INTO products(id, img, title, description, price) VALUES (9, decode('cmVkX2J1bGxfdC1zaGlydC5qcGc=','base64'), 'Red Bull T-Shirt', 'Official Red Bull Team t-shirt', 29.95);


-- ORDERS

INSERT INTO orders(orderid, selectedticket, quantity, price, totalprice) VALUES (10001, 9006, 4, 85, 340);
INSERT INTO orders(orderid, selectedticket, quantity, price, totalprice) VALUES (10002, 6008, 2, 130, 260);
INSERT INTO orders(orderid, selectedticket, quantity, price, totalprice) VALUES (10003, 8007, 3, 80, 240);
INSERT INTO orders(orderid, selectedticket, quantity, price, totalprice) VALUES (10004, 7009, 2, 75, 150);


-- TICKETS FORMULA 1

INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6001,
                                                                                            'Formula 1',
                                                                                            'Regular',
                                                                                            'Friday (Free practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-08-2023',
                                                                                            40 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6002,
                                                                                            'Formula 1',
                                                                                            'Regular',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '26-08-2023',
                                                                                            60 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6003,
                                                                                            'Formula 1',
                                                                                            'Regular',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            90 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6004,
                                                                                            'Formula 1',
                                                                                            'Regular',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            120 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6005,
                                                                                            'Formula 1',
                                                                                            'Silver',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-08-2023',
                                                                                            55 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6006,
                                                                                            'Formula 1',
                                                                                            'Silver',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '26-08-2023',
                                                                                            75 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6007,
                                                                                            'Formula 1',
                                                                                            'Silver',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            110 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6008,
                                                                                            'Formula 1',
                                                                                            'Silver',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            130 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6009,
                                                                                            'Formula 1',
                                                                                            'Gold',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-08-2023',
                                                                                            85 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6010,
                                                                                            'Formula 1',
                                                                                            'Gold',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '26-08-2023',
                                                                                            125 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6011,
                                                                                            'Formula 1',
                                                                                            'Gold',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            145 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6012,
                                                                                            'Formula 1',
                                                                                            'Gold',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            165 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6013,
                                                                                            'Formula 1',
                                                                                            'VIP',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-08-2023',
                                                                                            110 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6014,
                                                                                            'Formula 1',
                                                                                            'VIP',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '26-08-2023',
                                                                                            135 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6015,
                                                                                            'Formula 1',
                                                                                            'VIP',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            165 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (6016,
                                                                                            'Formula 1',
                                                                                            'VIP',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '27-08-2023',
                                                                                            185 );

-- TICKETS MOTO GP

INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7001,
                                                                                            'Moto GP',
                                                                                            'Regular',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-06-23',
                                                                                            50 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7002,
                                                                                            'Moto GP',
                                                                                            'Regular',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '24-06-23',
                                                                                            65 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7003,
                                                                                            'Moto GP',
                                                                                            'Regular',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            80 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7004,
                                                                                            'Moto GP',
                                                                                            'Regular',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            110 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7005,
                                                                                            'Moto GP',
                                                                                            'Silver',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-06-23',
                                                                                            65 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7006,
                                                                                            'Moto GP',
                                                                                            'Silver',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '24-06-23',
                                                                                            75 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7007,
                                                                                            'Moto GP',
                                                                                            'Silver',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            95 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7008,
                                                                                            'Moto GP',
                                                                                            'Silver',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            110 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7009,
                                                                                            'Moto GP',
                                                                                            'Gold',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-06-23',
                                                                                            75 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7010,
                                                                                            'Moto GP',
                                                                                            'Gold',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '24-06-23',
                                                                                            90 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7011,
                                                                                            'Moto GP',
                                                                                            'Gold',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            115 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7012,
                                                                                            'Moto GP',
                                                                                            'Gold',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            125 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7013,
                                                                                            'Moto GP',
                                                                                            'VIP',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-06-23',
                                                                                            90 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7014,
                                                                                            'Moto GP',
                                                                                            'VIP',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '24-06-23',
                                                                                            115 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7015,
                                                                                            'Moto GP',
                                                                                            'VIP',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            135 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (7016,
                                                                                            'Moto GP',
                                                                                            'VIP',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '25-06-23',
                                                                                            160 );

-- TICKETS DTM

INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8001,
                                                                                            'DTM',
                                                                                            'Regular',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '23-06-23',
                                                                                            45 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8002,
                                                                                            'DTM',
                                                                                            'Regular',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '24-06-23',
                                                                                            55 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8003,
                                                                                            'DTM',
                                                                                            'Regular',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            70 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8004,
                                                                                            'DTM',
                                                                                            'Regular',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            95 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8005,
                                                                                            'DTM',
                                                                                            'Silver',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '23-06-23',
                                                                                            55 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8006,
                                                                                            'DTM',
                                                                                            'Silver',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '24-06-23',
                                                                                            65 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8007,
                                                                                            'DTM',
                                                                                            'Silver',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            80 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8008,
                                                                                            'DTM',
                                                                                            'Silver',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            100 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8009,
                                                                                            'DTM',
                                                                                            'Gold',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '23-06-23',
                                                                                            65 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8010,
                                                                                            'DTM',
                                                                                            'Gold',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '24-06-23',
                                                                                            80 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8011,
                                                                                            'DTM',
                                                                                            'Gold',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            95 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8012,
                                                                                            'DTM',
                                                                                            'Gold',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            120 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8013,
                                                                                            'DTM',
                                                                                            'VIP',
                                                                                            'Friday (Free Practise)',
                                                                                            'Circuit Zandvoort',
                                                                                            '23-06-23',
                                                                                            75 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8014,
                                                                                            'DTM',
                                                                                            'VIP',
                                                                                            'Saturday (Qualification)',
                                                                                            'Circuit Zandvoort',
                                                                                            '24-06-23',
                                                                                            90 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8015,
                                                                                            'DTM',
                                                                                            'VIP',
                                                                                            'Sunday (Race)',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            125 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (8016,
                                                                                            'DTM',
                                                                                            'VIP',
                                                                                            'Weekend',
                                                                                            'Circuit Zandvoort',
                                                                                            '25-06-23',
                                                                                            140 );

-- TICKETS SBK

INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9001,
                                                                                            'SBK',
                                                                                            'Regular',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '21-04-23',
                                                                                            60 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9002,
                                                                                            'SBK',
                                                                                            'Regular',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '22-04-23',
                                                                                            75 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9003,
                                                                                            'SBK',
                                                                                            'Regular',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            95 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9004,
                                                                                            'SBK',
                                                                                            'Regular',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            115 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9005,
                                                                                            'SBK',
                                                                                            'Silver',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '21-04-23',
                                                                                            70 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9006,
                                                                                            'SBK',
                                                                                            'Silver',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '22-04-23',
                                                                                            85 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9007,
                                                                                            'SBK',
                                                                                            'Silver',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            100 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9008,
                                                                                            'SBK',
                                                                                            'Silver',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            130 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9009,
                                                                                            'SBK',
                                                                                            'Gold',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '21-04-23',
                                                                                            85 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9010,
                                                                                            'SBK',
                                                                                            'Gold',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '22-04-23',
                                                                                            95 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9011,
                                                                                            'SBK',
                                                                                            'Gold',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            125 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9012,
                                                                                            'SBK',
                                                                                            'Gold',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            145 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9013,
                                                                                            'SBK',
                                                                                            'VIP',
                                                                                            'Friday (Free Practise)',
                                                                                            'TT Circuit Assen',
                                                                                            '21-04-23',
                                                                                            95 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9014,
                                                                                            'SBK',
                                                                                            'VIP',
                                                                                            'Saturday (Qualification)',
                                                                                            'TT Circuit Assen',
                                                                                            '22-04-23',
                                                                                            120 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9015,
                                                                                            'SBK',
                                                                                            'VIP',
                                                                                            'Sunday (Race)',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            135 );
INSERT INTO tickets(id, eventname, tickettype, daytype, location, eventdate, price) VALUES (9016,
                                                                                            'SBK',
                                                                                            'VIP',
                                                                                            'Weekend',
                                                                                            'TT Circuit Assen',
                                                                                            '23-04-23',
                                                                                            160 );


-- MAILS
-- INSERT INTO mails(recipient, message, subject, attachment) VALUES ('r.noteborn@gmail.com', 'Thanks! \nYour order is confirmed. \nTICKETSDUTCHGP.COM', 'Order Confirmation', '');
INSERT INTO mails(recipient, message, subject, attachment) VALUES ('r.noteborn@gmail.com', 'Hey racefan! \nHere is your ticket, enjoy the event! \nTICKETSDUTCHGP.COM', 'Ticket', 'world_champion_max.jpg');
