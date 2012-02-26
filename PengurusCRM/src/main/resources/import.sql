--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 297, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "user" (user_id, username, password, description) VALUES (7, 'jSmith', 'unknownPass', 'I''m specialist in Italic and Romance languages.');
INSERT INTO "user" (user_id, username, password, description) VALUES (13, 'WorkerTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (15, 'WorkerTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (17, 'new name2', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (21, 'WorkerTest4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (24, 'BusinessClientTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (26, 'BusinessClientTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (28, 'new name4', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (32, 'BusinessClientTest4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (47, 'QuoteTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (53, 'IndividualClientQuote0', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (54, 'QuoteSupervisor0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (62, 'QuoteTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (68, 'IndividualClientQuote1', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (69, 'QuoteSupervisor1', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (77, 'QuoteTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (83, 'IndividualClientQuote2', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (84, 'QuoteSupervisor2', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (92, 'QuoteTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (98, 'IndividualClientQuote3', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (99, 'QuoteSupervisor3', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (101, 'Test', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (102, 'Test2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (103, 'new name', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (105, 'Test4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (107, 'IndividualClientTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (109, 'IndividualClientTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (111, 'IndividualClientTest3', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (115, 'IndividualClientTest4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (124, 'TaskTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (134, 'TaskTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (144, 'TaskTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (154, 'TaskTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (168, 'JobTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (180, 'JobTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (192, 'JobTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (206, 'JobTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (218, 'TranslatorTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (226, 'TranslatorTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (234, 'new name1', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (250, 'TranslatorTest4', 'password', 'description');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO client (user_id) VALUES (24);
INSERT INTO client (user_id) VALUES (26);
INSERT INTO client (user_id) VALUES (28);
INSERT INTO client (user_id) VALUES (32);
INSERT INTO client (user_id) VALUES (53);
INSERT INTO client (user_id) VALUES (68);
INSERT INTO client (user_id) VALUES (83);
INSERT INTO client (user_id) VALUES (98);
INSERT INTO client (user_id) VALUES (107);
INSERT INTO client (user_id) VALUES (109);
INSERT INTO client (user_id) VALUES (111);
INSERT INTO client (user_id) VALUES (115);


--
-- Data for Name: businessclient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO businessclient (user_id, name) VALUES (24, 'Company');
INSERT INTO businessclient (user_id, name) VALUES (26, 'Company');
INSERT INTO businessclient (user_id, name) VALUES (28, 'Company');
INSERT INTO businessclient (user_id, name) VALUES (32, 'Company');


--
-- Data for Name: bussinessclient; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: currencytype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO currencytype (id, currency) VALUES (5, '€');
INSERT INTO currencytype (id, currency) VALUES (45, '$$Quote0');
INSERT INTO currencytype (id, currency) VALUES (60, '$$Quote1');
INSERT INTO currencytype (id, currency) VALUES (75, '$$Quote2');
INSERT INTO currencytype (id, currency) VALUES (90, '$$Quote3');
INSERT INTO currencytype (id, currency) VALUES (122, '$0');
INSERT INTO currencytype (id, currency) VALUES (132, '$1');
INSERT INTO currencytype (id, currency) VALUES (142, '$2');
INSERT INTO currencytype (id, currency) VALUES (152, '$3');
INSERT INTO currencytype (id, currency) VALUES (166, '$$0');
INSERT INTO currencytype (id, currency) VALUES (178, '$$1');
INSERT INTO currencytype (id, currency) VALUES (199, 'some currency3');
INSERT INTO currencytype (id, currency) VALUES (190, '$$2');
INSERT INTO currencytype (id, currency) VALUES (204, '$$3');
INSERT INTO currencytype (id, currency) VALUES (215, 'PLN0');
INSERT INTO currencytype (id, currency) VALUES (223, 'PLN1');
INSERT INTO currencytype (id, currency) VALUES (231, 'PLN2');
INSERT INTO currencytype (id, currency) VALUES (239, 'PLN3');
INSERT INTO currencytype (id, currency) VALUES (247, 'PLN4');
INSERT INTO currencytype (id, currency) VALUES (253, 'PLN');
INSERT INTO currencytype (id, currency) VALUES (254, 'EUR');
INSERT INTO currencytype (id, currency) VALUES (255, 'CHF');
INSERT INTO currencytype (id, currency) VALUES (257, 'GBP');
INSERT INTO currencytype (id, currency) VALUES (259, 'Portuguese0');
INSERT INTO currencytype (id, currency) VALUES (261, 'Portuguese1');
INSERT INTO currencytype (id, currency) VALUES (263, 'Portuguese2');
INSERT INTO currencytype (id, currency) VALUES (265, 'JPY');
INSERT INTO currencytype (id, currency) VALUES (266, 'Portuguese3');
INSERT INTO currencytype (id, currency) VALUES (272, '$$$0');
INSERT INTO currencytype (id, currency) VALUES (278, '$$$1');
INSERT INTO currencytype (id, currency) VALUES (284, '$$$2');
INSERT INTO currencytype (id, currency) VALUES (292, '$$$3');


--
-- Data for Name: personaldata; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (8, 'Jeremy', 'Smith', '139 Gunnersbury Avenue, London W3 8LG, United Kingdom', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (14, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (16, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (18, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (20, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (22, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (25, 'Firstame', 'Lastname', 'Address', 24);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (27, 'Firstame', 'Lastname', 'Address', 26);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (29, 'Firstame', 'Lastname', 'Address', 28);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (31, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (33, 'Firstame', 'Lastname', 'Address', 32);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (48, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (63, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (78, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (93, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (108, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (110, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (112, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (114, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (116, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (125, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (135, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (145, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (155, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (169, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (181, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (193, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (207, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (219, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (227, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (235, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (243, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (251, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (294, 'first name', 'last name', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (295, 'first name', 'last name', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (296, 'new first name', 'new last name', 'new address', NULL);


--
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO emails (id, email) VALUES (8, 'jeremy_smith@yahoo.com');
INSERT INTO emails (id, email) VALUES (8, 'jeremy_smith@gmail.com');
INSERT INTO emails (id, email) VALUES (14, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (14, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (16, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (16, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (18, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (18, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (20, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (20, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (22, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (22, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (25, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (25, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (27, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (27, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (29, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (29, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (31, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (31, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (33, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (33, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (48, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (48, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (63, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (63, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (78, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (78, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (93, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (93, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (108, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (108, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (110, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (110, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (112, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (112, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (114, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (114, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (116, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (116, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (125, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (125, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (135, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (135, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (145, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (145, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (155, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (155, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (169, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (169, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (181, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (181, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (193, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (193, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (207, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (207, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (219, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (219, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (227, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (227, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (235, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (235, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (243, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (243, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (251, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (251, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (294, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (294, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (295, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (295, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (296, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (296, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (296, 'newemail@email.com');


--
-- Data for Name: individualclient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO individualclient (user_id, data) VALUES (53, 48);
INSERT INTO individualclient (user_id, data) VALUES (68, 63);
INSERT INTO individualclient (user_id, data) VALUES (83, 78);
INSERT INTO individualclient (user_id, data) VALUES (98, 93);
INSERT INTO individualclient (user_id, data) VALUES (107, 108);
INSERT INTO individualclient (user_id, data) VALUES (109, 110);
INSERT INTO individualclient (user_id, data) VALUES (111, 112);
INSERT INTO individualclient (user_id, data) VALUES (115, 116);


--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO language (id, language) VALUES (1, 'Latin');
INSERT INTO language (id, language) VALUES (2, 'Ancient Greek');
INSERT INTO language (id, language) VALUES (12, 'Hindi');
INSERT INTO language (id, language) VALUES (35, 'Polish');
INSERT INTO language (id, language) VALUES (36, 'English');
INSERT INTO language (id, language) VALUES (37, 'German');
INSERT INTO language (id, language) VALUES (39, 'Italian');
INSERT INTO language (id, language) VALUES (41, 'FrenchQuote0');
INSERT INTO language (id, language) VALUES (42, 'DanishQuote0');
INSERT INTO language (id, language) VALUES (56, 'FrenchQuote1');
INSERT INTO language (id, language) VALUES (57, 'DanishQuote1');
INSERT INTO language (id, language) VALUES (71, 'FrenchQuote2');
INSERT INTO language (id, language) VALUES (72, 'DanishQuote2');
INSERT INTO language (id, language) VALUES (86, 'FrenchQuote3');
INSERT INTO language (id, language) VALUES (87, 'DanishQuote3');
INSERT INTO language (id, language) VALUES (118, 'French0');
INSERT INTO language (id, language) VALUES (119, 'Danish0');
INSERT INTO language (id, language) VALUES (128, 'French1');
INSERT INTO language (id, language) VALUES (129, 'Danish1');
INSERT INTO language (id, language) VALUES (138, 'French2');
INSERT INTO language (id, language) VALUES (139, 'Danish2');
INSERT INTO language (id, language) VALUES (148, 'French3');
INSERT INTO language (id, language) VALUES (149, 'Danish3');
INSERT INTO language (id, language) VALUES (162, 'Frenchjob0');
INSERT INTO language (id, language) VALUES (163, 'Danishjob0');
INSERT INTO language (id, language) VALUES (174, 'Frenchjob1');
INSERT INTO language (id, language) VALUES (175, 'Danishjob1');
INSERT INTO language (id, language) VALUES (186, 'Frenchjob2');
INSERT INTO language (id, language) VALUES (187, 'Danishjob2');
INSERT INTO language (id, language) VALUES (200, 'Frenchjob3');
INSERT INTO language (id, language) VALUES (201, 'Danishjob3');
INSERT INTO language (id, language) VALUES (213, 'Polish0');
INSERT INTO language (id, language) VALUES (214, 'English0');
INSERT INTO language (id, language) VALUES (221, 'Polish1');
INSERT INTO language (id, language) VALUES (222, 'English1');
INSERT INTO language (id, language) VALUES (229, 'Polish2');
INSERT INTO language (id, language) VALUES (230, 'English2');
INSERT INTO language (id, language) VALUES (237, 'Polish3');
INSERT INTO language (id, language) VALUES (238, 'English3');
INSERT INTO language (id, language) VALUES (245, 'Polish4');
INSERT INTO language (id, language) VALUES (246, 'English4');
INSERT INTO language (id, language) VALUES (268, 'LanguageTranslationFrom0');
INSERT INTO language (id, language) VALUES (269, 'LanguageTranslationTo0');
INSERT INTO language (id, language) VALUES (274, 'LanguageTranslationFrom1');
INSERT INTO language (id, language) VALUES (275, 'LanguageTranslationTo1');
INSERT INTO language (id, language) VALUES (280, 'LanguageTranslationFrom2');
INSERT INTO language (id, language) VALUES (281, 'LanguageTranslationTo2');
INSERT INTO language (id, language) VALUES (286, 'language1');
INSERT INTO language (id, language) VALUES (287, 'language2');
INSERT INTO language (id, language) VALUES (288, 'LanguageTranslationFrom3');
INSERT INTO language (id, language) VALUES (289, 'LanguageTranslationTo3');


--
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO price (id, price, currency) VALUES (4, 12, 5);
INSERT INTO price (id, price, currency) VALUES (44, 200, 45);
INSERT INTO price (id, price, currency) VALUES (59, 201, 60);
INSERT INTO price (id, price, currency) VALUES (74, 202, 75);
INSERT INTO price (id, price, currency) VALUES (89, 203, 90);
INSERT INTO price (id, price, currency) VALUES (121, 200, 122);
INSERT INTO price (id, price, currency) VALUES (131, 201, 132);
INSERT INTO price (id, price, currency) VALUES (141, 202, 142);
INSERT INTO price (id, price, currency) VALUES (151, 203, 152);
INSERT INTO price (id, price, currency) VALUES (165, 200, 166);
INSERT INTO price (id, price, currency) VALUES (177, 201, 178);
INSERT INTO price (id, price, currency) VALUES (198, 100, 199);
INSERT INTO price (id, price, currency) VALUES (189, 202, 190);
INSERT INTO price (id, price, currency) VALUES (203, 203, 204);
INSERT INTO price (id, price, currency) VALUES (216, 100, 215);
INSERT INTO price (id, price, currency) VALUES (224, 100, 223);
INSERT INTO price (id, price, currency) VALUES (232, 100, 231);
INSERT INTO price (id, price, currency) VALUES (240, 100, 239);
INSERT INTO price (id, price, currency) VALUES (248, 100, 247);
INSERT INTO price (id, price, currency) VALUES (260, 200, 259);
INSERT INTO price (id, price, currency) VALUES (262, 200, 261);
INSERT INTO price (id, price, currency) VALUES (264, 1000, 265);
INSERT INTO price (id, price, currency) VALUES (271, 200, 272);
INSERT INTO price (id, price, currency) VALUES (277, 201, 278);
INSERT INTO price (id, price, currency) VALUES (283, 202, 284);
INSERT INTO price (id, price, currency) VALUES (291, 203, 292);


--
-- Data for Name: worker; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO worker (user_id, data) VALUES (7, 8);
INSERT INTO worker (user_id, data) VALUES (13, 14);
INSERT INTO worker (user_id, data) VALUES (15, 16);
INSERT INTO worker (user_id, data) VALUES (17, 18);
INSERT INTO worker (user_id, data) VALUES (21, 22);
INSERT INTO worker (user_id, data) VALUES (47, 48);
INSERT INTO worker (user_id, data) VALUES (54, 48);
INSERT INTO worker (user_id, data) VALUES (62, 63);
INSERT INTO worker (user_id, data) VALUES (69, 63);
INSERT INTO worker (user_id, data) VALUES (77, 78);
INSERT INTO worker (user_id, data) VALUES (84, 78);
INSERT INTO worker (user_id, data) VALUES (92, 93);
INSERT INTO worker (user_id, data) VALUES (99, 93);
INSERT INTO worker (user_id, data) VALUES (124, 125);
INSERT INTO worker (user_id, data) VALUES (134, 135);
INSERT INTO worker (user_id, data) VALUES (144, 145);
INSERT INTO worker (user_id, data) VALUES (154, 155);
INSERT INTO worker (user_id, data) VALUES (168, 169);
INSERT INTO worker (user_id, data) VALUES (180, 181);
INSERT INTO worker (user_id, data) VALUES (192, 193);
INSERT INTO worker (user_id, data) VALUES (206, 207);
INSERT INTO worker (user_id, data) VALUES (218, 219);
INSERT INTO worker (user_id, data) VALUES (226, 227);
INSERT INTO worker (user_id, data) VALUES (234, 235);
INSERT INTO worker (user_id, data) VALUES (250, 251);


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: quote; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (55, 'inProgress', 53, 54, 'Quote description');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (70, 'inProgress', 68, 69, 'Quote description');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (85, 'open', NULL, 84, 'new quote description');


--
-- Data for Name: translationtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO translationtype (id, description, unit) VALUES (3, '5 chapters of Greek Mythology by Edith Hamilton', 'pages');
INSERT INTO translationtype (id, description, unit) VALUES (43, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (58, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (73, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (88, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (120, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (130, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (140, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (150, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (158, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (159, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (160, 'new description', 'new unit');
INSERT INTO translationtype (id, description, unit) VALUES (164, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (176, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (188, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (202, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (212, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (220, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (228, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (236, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (244, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (270, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (276, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (282, 'translation descritpion', 'unit');


--
-- Data for Name: translator; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO translator (user_id) VALUES (7);
INSERT INTO translator (user_id) VALUES (47);
INSERT INTO translator (user_id) VALUES (62);
INSERT INTO translator (user_id) VALUES (77);
INSERT INTO translator (user_id) VALUES (92);
INSERT INTO translator (user_id) VALUES (124);
INSERT INTO translator (user_id) VALUES (134);
INSERT INTO translator (user_id) VALUES (144);
INSERT INTO translator (user_id) VALUES (154);
INSERT INTO translator (user_id) VALUES (168);
INSERT INTO translator (user_id) VALUES (180);
INSERT INTO translator (user_id) VALUES (192);
INSERT INTO translator (user_id) VALUES (206);
INSERT INTO translator (user_id) VALUES (218);
INSERT INTO translator (user_id) VALUES (226);
INSERT INTO translator (user_id) VALUES (234);
INSERT INTO translator (user_id) VALUES (250);


--
-- Data for Name: translation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (6, 3, 1, 2, 4, 7);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (46, 43, 41, 42, 44, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (61, 58, 56, 57, 59, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (76, 73, 71, 72, 74, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (91, 88, 86, 87, 89, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (123, 120, 118, 119, 121, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (133, 130, 128, 129, 131, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (143, 140, 138, 139, 141, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (153, 150, 148, 149, 151, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (167, 164, 162, 163, 165, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (179, 176, 174, 175, 177, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (191, 188, 186, 187, 189, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (205, 202, 200, 201, 203, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (217, 212, 213, 214, 216, 218);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (225, 220, 221, 222, 224, 226);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (233, 228, 229, 230, 232, 234);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (241, 236, 237, 238, 240, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (249, 244, 245, 246, 248, 250);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (273, 270, 268, 269, 271, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (279, 276, 274, 275, 277, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (285, 282, 287, 286, 283, NULL);


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (51, 'open', '2012-02-26 13:36:35.63', 46, 21000, 44, 'Job description2', 55, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (49, 'closed', '2012-02-26 13:36:35.63', 46, 1000, 44, 'Job description', 55, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (64, 'closed', '2012-02-26 13:36:35.887', 61, 1000, 59, 'Job description', 70, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (66, 'open', '2012-02-26 13:36:35.887', 61, 21000, 59, 'Job description2', 70, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (81, 'open', '2012-02-26 13:36:36.179', 76, 21000, 74, 'Job description2', 85, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (79, 'closed', '2012-02-26 13:36:36.179', 76, 1000, 74, 'Job description', 85, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (94, 'closed', '2012-02-26 13:36:36.581', 91, 1000, 89, 'Job description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (96, 'open', '2012-02-26 13:36:36.581', 91, 21000, 89, 'Job description2', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (126, 'closed', '2012-02-26 13:36:37.501', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (136, 'closed', '2012-02-26 13:36:37.68', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (146, 'closed', '2012-02-26 13:36:37.835', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (156, 'closed', '2012-02-26 13:36:38.012', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (170, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (173, 'accepted', '2012-02-26 13:36:38.325', 167, 1000, 165, 'job descriptio', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (182, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (185, 'accepted', '2012-02-26 13:36:38.538', 179, 1000, 177, 'job descriptio', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (194, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (197, 'resolved', '2012-02-26 13:36:38.792', 191, 123123, 198, 'new awesome description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (208, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: phonenumbers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO phonenumbers (id, phonenumber) VALUES (8, '3-210-552-2311');
INSERT INTO phonenumbers (id, phonenumber) VALUES (8, '1-888-242‑2100');
INSERT INTO phonenumbers (id, phonenumber) VALUES (14, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (14, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (16, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (16, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (18, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (18, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (20, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (20, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (22, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (22, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (25, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (25, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (27, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (27, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (29, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (29, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (31, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (31, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (33, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (33, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (48, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (48, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (63, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (63, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (78, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (78, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (93, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (93, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (108, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (108, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (110, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (110, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (112, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (112, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (114, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (114, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (116, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (116, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (125, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (125, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (135, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (135, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (145, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (145, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (155, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (155, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (169, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (169, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (181, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (181, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (193, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (193, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (207, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (207, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (219, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (219, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (227, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (227, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (235, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (235, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (243, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (243, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (251, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (251, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (294, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (294, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (295, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (295, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (296, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (296, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (296, '000-111-111');


--
-- Data for Name: project_experts; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: project_freelancers; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: project_worker; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (10, 'accepted', 7, '2012-02-25 15:05:01.601', 6, 100, 4, 'chapters from 1 to 3', 9, 'ok', 'Ok.', 9);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (11, 'accepted', 7, '2012-02-25 15:05:01.601', 6, 100, 4, 'chapters 4 and 5', 9, 'good', '5th chapter was was magnificent work!', 9);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (52, 'accepted', 47, '2012-02-26 13:36:35.63', 46, 100, 44, 'desc2', 51, 'bad', 'comm2', 51);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (50, 'accepted', 47, '2012-02-26 13:36:35.63', 46, 100, 44, 'desc', 49, 'bad', 'comm', 49);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (209, 'accepted', 206, '2012-02-26 13:36:38.992', 205, 100, 203, 'desc', 208, 'bad', 'comm', NULL);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (65, 'accepted', 62, '2012-02-26 13:36:35.887', 61, 100, 59, 'desc', 64, 'bad', 'comm', 64);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (67, 'accepted', 62, '2012-02-26 13:36:35.887', 61, 100, 59, 'desc2', 66, 'bad', 'comm2', 66);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (210, 'accepted', 206, '2012-02-26 13:36:38.992', 205, 100, 203, 'desc2', 208, 'bad', 'comm2', NULL);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (82, 'accepted', 77, '2012-02-26 13:36:36.179', 76, 100, 74, 'desc2', 81, 'bad', 'comm2', 81);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (80, 'accepted', 77, '2012-02-26 13:36:36.179', 76, 100, 74, 'desc', 79, 'bad', 'comm', 79);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (95, 'accepted', 92, '2012-02-26 13:36:36.58', 91, 100, 89, 'desc', 94, 'bad', 'comm', 94);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (97, 'accepted', 92, '2012-02-26 13:36:36.58', 91, 100, 89, 'desc2', 96, 'bad', 'comm2', 96);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (127, 'accepted', 124, '2012-02-26 13:36:37.512', 123, 100, 121, 'desc', 126, 'bad', 'comm', NULL);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (137, 'accepted', 134, '2012-02-26 13:36:37.69', 133, 100, 131, 'desc2', 136, 'bad', 'comm', NULL);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (147, 'resolved', 144, '2012-02-26 13:36:37.844', 143, 20000, 141, 'new description', 146, 'good', 'new comment', NULL);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (171, 'accepted', 168, '2012-02-26 13:36:38.279', 167, 100, 165, 'desc', 170, 'bad', 'comm', 173);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (172, 'accepted', 168, '2012-02-26 13:36:38.279', 167, 100, 165, 'desc2', 170, 'bad', 'comm2', 173);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (184, 'accepted', 180, '2012-02-26 13:36:38.507', 179, 100, 177, 'desc2', 182, 'bad', 'comm2', 185);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (183, 'accepted', 180, '2012-02-26 13:36:38.507', 179, 100, 177, 'desc', 182, 'bad', 'comm', 185);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (196, 'accepted', 192, '2012-02-26 13:36:38.741', 191, 100, 189, 'desc2', 194, 'bad', 'comm2', 197);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (195, 'accepted', 192, '2012-02-26 13:36:38.741', 191, 100, 189, 'desc', 194, 'bad', 'comm', 197);


--
-- Data for Name: user_userrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_userrole (user_id, user_role) VALUES (7, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (7, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (13, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (13, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (15, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (15, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (17, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (17, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (21, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (21, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (24, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (24, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (26, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (26, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (28, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (28, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (32, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (32, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (47, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (47, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (53, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (54, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (54, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (62, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (62, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (68, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (69, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (69, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (77, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (77, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (83, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (84, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (84, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (92, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (92, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (98, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (99, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (99, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (101, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (101, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (102, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (102, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (103, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (103, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (105, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (105, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (107, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (107, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (109, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (109, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (111, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (111, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (115, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (115, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (124, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (124, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (134, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (134, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (144, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (144, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (154, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (154, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (168, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (168, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (180, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (180, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (192, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (192, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (206, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (206, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (218, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (218, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (226, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (226, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (234, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (234, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (250, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (250, 'ROLE_EXPERT');


--
-- PostgreSQL database dump complete
--

