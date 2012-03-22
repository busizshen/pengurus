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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: businessclient; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE businessclient (
    user_id bigint NOT NULL,
    name character varying(255)
);


--
-- Name: client; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE client (
    user_id bigint NOT NULL
);


--
-- Name: currencytype; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE currencytype (
    id bigint NOT NULL,
    currency character varying(255)
);


--
-- Name: emails; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE emails (
    id bigint NOT NULL,
    email character varying(255)
);


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('hibernate_sequence', 323, true);


--
-- Name: individualclient; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE individualclient (
    user_id bigint NOT NULL,
    data bigint
);


--
-- Name: job; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE job (
    job_id bigint NOT NULL,
    status character varying(255),
    deadline timestamp without time zone,
    translation bigint,
    amount integer,
    price bigint,
    description character varying(255),
    quote_id bigint,
    project_id bigint
);


--
-- Name: language; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE language (
    id bigint NOT NULL,
    language character varying(255)
);


--
-- Name: personaldata; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE personaldata (
    id bigint NOT NULL,
    firstname character varying(255),
    lastname character varying(255),
    address character varying(255),
    user_id bigint
);


--
-- Name: phonenumbers; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE phonenumbers (
    id bigint NOT NULL,
    phonenumber character varying(255)
);


--
-- Name: price; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE price (
    id bigint NOT NULL,
    price integer,
    currency bigint
);


--
-- Name: project; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE project (
    project_id bigint NOT NULL,
    status character varying(255),
    client bigint,
    supervisor bigint,
    description character varying(255)
);


--
-- Name: project_experts; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE project_experts (
    project_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- Name: project_freelancers; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE project_freelancers (
    project_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- Name: project_worker; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE project_worker (
    project_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- Name: quote; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE quote (
    quote_id bigint NOT NULL,
    status character varying(255),
    client bigint,
    supervisor bigint,
    description character varying(255)
);


--
-- Name: task; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE task (
    task_id bigint NOT NULL,
    status character varying(255),
    expert bigint,
    deadline timestamp without time zone,
    translation bigint,
    amount integer,
    price bigint,
    description character varying(255),
    job bigint,
    rating character varying(255),
    comment character varying(255),
    job_id bigint
);


--
-- Name: translation; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE translation (
    translation_id bigint NOT NULL,
    type bigint,
    lfrom bigint,
    lto bigint,
    defaultprice bigint,
    user_id bigint
);


--
-- Name: translationtype; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE translationtype (
    id bigint NOT NULL,
    description character varying(255),
    unit character varying(255)
);


--
-- Name: translator; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE translator (
    user_id bigint NOT NULL
);


--
-- Name: user; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "user" (
    user_id bigint NOT NULL,
    username character varying(255),
    password character varying(255),
    description character varying(255)
);


--
-- Name: user_userrole; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE user_userrole (
    user_id bigint NOT NULL,
    user_role character varying(255)
);


--
-- Name: worker; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE worker (
    user_id bigint NOT NULL,
    data bigint
);


--
-- Data for Name: businessclient; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO businessclient (user_id, name) VALUES (24, 'Nightmare Company');
INSERT INTO businessclient (user_id, name) VALUES (26, 'Johnson & sons CO');
INSERT INTO businessclient (user_id, name) VALUES (28, 'Morden Storm');
INSERT INTO businessclient (user_id, name) VALUES (32, 'Herolds');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO client (user_id) VALUES (321);


--
-- Data for Name: currencytype; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO currencytype (id, currency) VALUES (5, 'RUB (Russian ruble)');
INSERT INTO currencytype (id, currency) VALUES (45, 'AFN (Afghan afghani)');
INSERT INTO currencytype (id, currency) VALUES (60, 'EUR (Euro)');
INSERT INTO currencytype (id, currency) VALUES (75, 'ALL (Albanian lek)');
INSERT INTO currencytype (id, currency) VALUES (90, 'GBP (British pound)');
INSERT INTO currencytype (id, currency) VALUES (122, 'DZD (Algerian dinar)');
INSERT INTO currencytype (id, currency) VALUES (132, 'AOA (Angolan kwanza)');
INSERT INTO currencytype (id, currency) VALUES (142, 'XCD (East Caribbean dollar)');
INSERT INTO currencytype (id, currency) VALUES (152, 'ARS (Argentine peso)');
INSERT INTO currencytype (id, currency) VALUES (166, 'AMD (Armenian dram)');
INSERT INTO currencytype (id, currency) VALUES (178, 'AWG (Aruban florin)');
INSERT INTO currencytype (id, currency) VALUES (199, 'AUD (Australlian dolar)');
INSERT INTO currencytype (id, currency) VALUES (190, 'AZN (Azerbaijani manat)');
INSERT INTO currencytype (id, currency) VALUES (204, 'PLN (Polish zloty)');
INSERT INTO currencytype (id, currency) VALUES (215, 'NZD (New Zealand dollar)');
INSERT INTO currencytype (id, currency) VALUES (223, 'LTL (Lithuanian litas)');
INSERT INTO currencytype (id, currency) VALUES (231, 'KWD (Kuwaiti dinar)');
INSERT INTO currencytype (id, currency) VALUES (239, 'IQD (Iraqi dinar)');
INSERT INTO currencytype (id, currency) VALUES (247, 'EGP (Egyptian pound)');
INSERT INTO currencytype (id, currency) VALUES (253, 'USD (United States dollar)');
INSERT INTO currencytype (id, currency) VALUES (254, 'DOP (Dominican peso)');
INSERT INTO currencytype (id, currency) VALUES (255, 'CZK (Czech koruna)');
INSERT INTO currencytype (id, currency) VALUES (257, 'DKK (Danish krone)');
INSERT INTO currencytype (id, currency) VALUES (259, 'HRK (Croatian kuna)');
INSERT INTO currencytype (id, currency) VALUES (261, 'COP (Colombian peso)');
INSERT INTO currencytype (id, currency) VALUES (263, 'CNY (Chinese yuan)');
INSERT INTO currencytype (id, currency) VALUES (265, 'JPY (Japanese yen)');
INSERT INTO currencytype (id, currency) VALUES (266, 'JOD (Jordanian dinar)');
INSERT INTO currencytype (id, currency) VALUES (272, 'RSD (Serbian dinar)');
INSERT INTO currencytype (id, currency) VALUES (278, 'CHF (Swiss franc)');
INSERT INTO currencytype (id, currency) VALUES (284, 'SEK (Swedish krona)');
INSERT INTO currencytype (id, currency) VALUES (292, 'TRY (Turkish lira)');
INSERT INTO currencytype (id, currency) VALUES (308, '$$Project0');


--
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO emails (id, email) VALUES (299, 'jeremy_smith@yahoo.com');
INSERT INTO emails (id, email) VALUES (299, 'jeremy_smith@gmail.com');
INSERT INTO emails (id, email) VALUES (301, 'admin@hacks.com');
INSERT INTO emails (id, email) VALUES (303, 'm.angelo@yahoo.fr');
INSERT INTO emails (id, email) VALUES (311, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (311, 'john2@yahoo.com');


--
-- Data for Name: individualclient; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO individualclient (user_id, data) VALUES (53, 48);
INSERT INTO individualclient (user_id, data) VALUES (68, 63);
INSERT INTO individualclient (user_id, data) VALUES (83, 78);
INSERT INTO individualclient (user_id, data) VALUES (98, 93);
INSERT INTO individualclient (user_id, data) VALUES (107, 108);
INSERT INTO individualclient (user_id, data) VALUES (109, 110);
INSERT INTO individualclient (user_id, data) VALUES (111, 112);
INSERT INTO individualclient (user_id, data) VALUES (115, 116);
INSERT INTO individualclient (user_id, data) VALUES (321, 311);


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (51, 'open', '2012-04-16 15:00:00', 46, 21000, 44, 'Job description2', 55, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (49, 'closed', '2012-01-01 00:00:00', 46, 1000, 44, 'Job description', 55, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (64, 'closed', '2011-10-25 12:15:00', 61, 1000, 59, 'Job description', 70, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (66, 'open', '2012-05-15 10:00:00', 61, 21000, 59, 'Job description2', 70, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (81, 'open', '2012-04-01 13:45:00', 76, 21000, 74, 'Job description2', 85, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (79, 'closed', '2012-02-10 15:00:00', 76, 1000, 74, 'Job description', 85, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (94, 'closed', '2012-02-10 13:00:00', 91, 1000, 89, 'Job description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (96, 'open', '2012-03-15 13:45:00', 91, 21000, 89, 'Job description2', NULL, NULL);
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
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (319, 'open', '2012-03-22 20:36:51.304', 309, 21000, 307, 'Job description2', NULL, 323);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (317, 'closed', '2012-03-22 20:36:51.304', 309, 1000, 307, 'Job description', NULL, 323);


--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO language (id, language) VALUES (1, 'Latin');
INSERT INTO language (id, language) VALUES (2, 'Ancient Greek');
INSERT INTO language (id, language) VALUES (12, 'Hindi');
INSERT INTO language (id, language) VALUES (35, 'Polish');
INSERT INTO language (id, language) VALUES (36, 'English');
INSERT INTO language (id, language) VALUES (37, 'German');
INSERT INTO language (id, language) VALUES (39, 'Italian');
INSERT INTO language (id, language) VALUES (41, 'Chinese');
INSERT INTO language (id, language) VALUES (42, 'Spanish');
INSERT INTO language (id, language) VALUES (56, 'Arabic');
INSERT INTO language (id, language) VALUES (57, 'Bengali');
INSERT INTO language (id, language) VALUES (71, 'Portuguese');
INSERT INTO language (id, language) VALUES (72, 'Russian');
INSERT INTO language (id, language) VALUES (86, 'Japanese');
INSERT INTO language (id, language) VALUES (87, 'Dholuo');
INSERT INTO language (id, language) VALUES (118, 'Vietnamese');
INSERT INTO language (id, language) VALUES (119, 'French');
INSERT INTO language (id, language) VALUES (128, 'Korean');
INSERT INTO language (id, language) VALUES (129, 'Turkish');
INSERT INTO language (id, language) VALUES (138, 'Pashto');
INSERT INTO language (id, language) VALUES (139, 'Persian');
INSERT INTO language (id, language) VALUES (148, 'Romanian');
INSERT INTO language (id, language) VALUES (149, 'Dutch');
INSERT INTO language (id, language) VALUES (162, 'Azerbaijani');
INSERT INTO language (id, language) VALUES (163, 'Kurdish');
INSERT INTO language (id, language) VALUES (174, 'Greek');
INSERT INTO language (id, language) VALUES (175, 'Catalan');
INSERT INTO language (id, language) VALUES (186, 'Hungarian');
INSERT INTO language (id, language) VALUES (187, 'Zulu');
INSERT INTO language (id, language) VALUES (200, 'Czech');
INSERT INTO language (id, language) VALUES (201, 'Swedish');
INSERT INTO language (id, language) VALUES (213, 'Albanian');
INSERT INTO language (id, language) VALUES (214, 'Armenian');
INSERT INTO language (id, language) VALUES (221, 'Kongo');
INSERT INTO language (id, language) VALUES (222, 'Mongolian');
INSERT INTO language (id, language) VALUES (229, 'Danish');
INSERT INTO language (id, language) VALUES (230, 'Finnish');
INSERT INTO language (id, language) VALUES (237, 'Slovak');
INSERT INTO language (id, language) VALUES (238, 'Afrikaans');
INSERT INTO language (id, language) VALUES (245, 'Guarani');
INSERT INTO language (id, language) VALUES (246, 'Sicilian');
INSERT INTO language (id, language) VALUES (268, 'Norwegian');
INSERT INTO language (id, language) VALUES (269, 'Georgian');
INSERT INTO language (id, language) VALUES (274, 'Umbundu');
INSERT INTO language (id, language) VALUES (275, 'Belinese');
INSERT INTO language (id, language) VALUES (280, 'Galician');
INSERT INTO language (id, language) VALUES (281, 'Shan');
INSERT INTO language (id, language) VALUES (286, 'Esperanto');
INSERT INTO language (id, language) VALUES (287, 'Lithuanian');
INSERT INTO language (id, language) VALUES (288, 'Jamaican Creole');
INSERT INTO language (id, language) VALUES (289, 'Southern Thai');


--
-- Data for Name: personaldata; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (8, 'Jeremy', 'Smith', '139 Gunnersbury Avenue, London W3 8LG, United Kingdom', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (14, 'Tom', 'Keane', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (16, 'Jane', 'Hugues', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (18, 'Theodor', 'Smith', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (20, 'Michael', 'Deluga', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (22, 'Bernadete', 'Breeze', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (25, 'Sue', 'Morning', 'Address', 24);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (27, 'Fred', 'Green', 'Address', 26);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (29, 'Charlie', 'Wanne', 'Address', 28);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (31, 'Jake', 'Stamper', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (33, 'Calle', 'Longington', 'Address', 32);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (48, 'Marge', 'Moore', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (63, 'Vernon', 'Moore', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (78, 'Patricia', 'Salvatore', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (93, 'Helen', 'Stranger', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (108, 'Mike', 'Lasocky', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (110, 'Juan', 'Hernandez', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (112, 'Sharon', 'Cave', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (114, 'Widerton', 'Salander', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (116, 'Lisabeth', 'Tayler', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (125, 'Karen', 'McLuscy', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (135, 'Britt', 'Wellington', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (145, 'Danny', 'Glitter', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (155, 'Beth', 'Doe', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (169, 'Narcise', 'Stamp', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (181, 'Joseph', 'Mitchell', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (193, 'Martha', 'Ostern', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (207, 'Steven', 'Gardern', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (219, 'Helga', 'Fruck', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (227, 'Martin', 'Ferth', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (235, 'Jake', 'Hoperstam', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (243, 'Augustin', 'Gumbber', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (251, 'Mark', 'Glamer', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (294, 'Judith', 'Tobber', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (295, 'Eve', 'Gleener', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (296, 'Eva', 'Jordin', 'new address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (299, 'Carol', 'Storker', '139 Dorothy Street, London F5 8JT, United Kingdom', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (301, 'John', 'Smith', '1 Reds Road, Manchester E5 00T, United Kingdom', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (303, 'Michael', 'Angelo', '59 Rue de Rivoli 75001 Paris, France', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (311, 'Jacob', 'Harper', '44 Church Road Wimbledon London SW19 5AE, England', NULL);


--
-- Data for Name: phonenumbers; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO phonenumbers (id, phonenumber) VALUES (299, '1-243-002‑2920');
INSERT INTO phonenumbers (id, phonenumber) VALUES (299, '9-286-513-0001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (301, '9-003-092‑1110');
INSERT INTO phonenumbers (id, phonenumber) VALUES (311, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (311, '00-001-001');


--
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO price (id, price, currency) VALUES (307, 200, 308);


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project (project_id, status, client, supervisor, description) VALUES (323, 'inProgress', 321, 322, 'the very first project created');


--
-- Data for Name: project_experts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_experts (project_id, user_id) VALUES (323, 312);
INSERT INTO project_experts (project_id, user_id) VALUES (323, 310);


--
-- Data for Name: project_freelancers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_freelancers (project_id, user_id) VALUES (323, 315);
INSERT INTO project_freelancers (project_id, user_id) VALUES (323, 316);


--
-- Data for Name: project_worker; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_worker (project_id, user_id) VALUES (323, 314);
INSERT INTO project_worker (project_id, user_id) VALUES (323, 313);


--
-- Data for Name: quote; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (55, 'inProgress', 53, 54, 'Quote description');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (70, 'inProgress', 68, 69, 'Quote description');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (85, 'open', NULL, 84, 'new quote description');


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (318, 'accepted', 310, '2012-03-22 20:36:51.303', 309, 100, 307, 'desc', 317, 'bad', 'comm', 317);
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (320, 'accepted', 310, '2012-03-22 20:36:51.303', 309, 100, 307, 'desc2', 319, 'bad', 'comm2', 319);


--
-- Data for Name: translation; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (309, 306, 42, 268, 307, NULL);


--
-- Data for Name: translationtype; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translationtype (id, description, unit) VALUES (3, '5 chapters of Greek Mythology by Edith Hamilton', 'pages');
INSERT INTO translationtype (id, description, unit) VALUES (43, 'J. Williams biography pages 100-250', 'unit');
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
INSERT INTO translationtype (id, description, unit) VALUES (306, 'description', 'unit');


--
-- Data for Name: translator; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO translator (user_id) VALUES (310);
INSERT INTO translator (user_id) VALUES (312);
INSERT INTO translator (user_id) VALUES (315);
INSERT INTO translator (user_id) VALUES (316);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "user" (user_id, username, password, description) VALUES (7, 'jSmith', 'unknownPass', 'I''m specialist in Italic and Romance languages.');
INSERT INTO "user" (user_id, username, password, description) VALUES (13, 'meggieSue', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (15, 'blueWork', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (17, 'navyBlue', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (21, 'Storm2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (24, 'JTCollar', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (26, 'Dennish_co', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (28, 'orion', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (32, 'BankOfAmerica', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (47, 'Stopper', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (53, 'MarkerTodd', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (54, 'BaseMan', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (62, 'SpaceMan', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (68, 'MaryJane', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (69, 'Harry', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (77, 'netJohn', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (83, 'Barbie12', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (84, 'StewBrew', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (92, 'Akinator', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (98, 'StacyChan', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (99, 'GoForIt', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (101, 'Tester', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (102, 'BorredJohn', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (103, 'myNickname', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (105, 'Shakespear', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (107, 'BornThorn', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (109, 'Agnes', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (111, 'WenndyT', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (115, 'Volley5', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (124, 'Aggie_ao', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (134, 'TommyLee', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (144, 'Naruto9', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (154, 'CindyOnly', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (168, 'BoyToy', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (180, 'GirlThrill', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (192, 'KateMcTell', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (206, 'MaryTheNight', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (218, 'Tododd', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (226, 'ZengaVille', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (234, 'Louise', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (250, 'Babez', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (298, 'worker', '2c54b2394df1ef67321a5ccb241ed57bdc6c511a', 'I''m just a user');
INSERT INTO "user" (user_id, username, password, description) VALUES (300, 'admin', 'a40546cc4fd6a12572828bb803380888ad1bfdab', 'I''m admin');
INSERT INTO "user" (user_id, username, password, description) VALUES (302, 'user', 'b6b1f4781776979c0775c71ebdd8bdc084aac5fe', 'Brand new user');
INSERT INTO "user" (user_id, username, password, description) VALUES (310, 'projectTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (312, '2projectTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (313, 'ProjectManager0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (314, '1ProjectManager0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (315, 'projectFreelancer0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (316, '2projectFreelancer0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (321, 'IndividualClientProject0', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (322, 'ProjectSupervisor0', 'pass', 'supervisor description');


--
-- Data for Name: user_userrole; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO user_userrole (user_id, user_role) VALUES (298, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (300, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (300, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (302, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (310, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (310, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (312, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (312, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (315, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (315, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (316, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (316, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (321, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (322, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (322, 'ROLE_EXECUTIVE');


--
-- Data for Name: worker; Type: TABLE DATA; Schema: public; Owner: -
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
INSERT INTO worker (user_id, data) VALUES (298, 299);
INSERT INTO worker (user_id, data) VALUES (300, 301);
INSERT INTO worker (user_id, data) VALUES (302, 303);
INSERT INTO worker (user_id, data) VALUES (310, 311);
INSERT INTO worker (user_id, data) VALUES (312, 311);
INSERT INTO worker (user_id, data) VALUES (313, 311);
INSERT INTO worker (user_id, data) VALUES (314, 311);
INSERT INTO worker (user_id, data) VALUES (315, 311);
INSERT INTO worker (user_id, data) VALUES (316, 311);
INSERT INTO worker (user_id, data) VALUES (322, 311);


--
-- Name: businessclient_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY businessclient
    ADD CONSTRAINT businessclient_pkey PRIMARY KEY (user_id);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (user_id);


--
-- Name: currencytype_currency_key; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY currencytype
    ADD CONSTRAINT currencytype_currency_key UNIQUE (currency);


--
-- Name: currencytype_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY currencytype
    ADD CONSTRAINT currencytype_pkey PRIMARY KEY (id);


--
-- Name: individualclient_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY individualclient
    ADD CONSTRAINT individualclient_pkey PRIMARY KEY (user_id);


--
-- Name: job_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (job_id);


--
-- Name: language_language_key; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY language
    ADD CONSTRAINT language_language_key UNIQUE (language);


--
-- Name: language_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY language
    ADD CONSTRAINT language_pkey PRIMARY KEY (id);


--
-- Name: personaldata_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY personaldata
    ADD CONSTRAINT personaldata_pkey PRIMARY KEY (id);


--
-- Name: price_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_pkey PRIMARY KEY (id);


--
-- Name: project_experts_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY project_experts
    ADD CONSTRAINT project_experts_pkey PRIMARY KEY (project_id, user_id);


--
-- Name: project_freelancers_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY project_freelancers
    ADD CONSTRAINT project_freelancers_pkey PRIMARY KEY (project_id, user_id);


--
-- Name: project_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_pkey PRIMARY KEY (project_id);


--
-- Name: project_worker_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY project_worker
    ADD CONSTRAINT project_worker_pkey PRIMARY KEY (project_id, user_id);


--
-- Name: quote_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY quote
    ADD CONSTRAINT quote_pkey PRIMARY KEY (quote_id);


--
-- Name: task_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_pkey PRIMARY KEY (task_id);


--
-- Name: translation_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY translation
    ADD CONSTRAINT translation_pkey PRIMARY KEY (translation_id);


--
-- Name: translationtype_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY translationtype
    ADD CONSTRAINT translationtype_pkey PRIMARY KEY (id);


--
-- Name: translator_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY translator
    ADD CONSTRAINT translator_pkey PRIMARY KEY (user_id);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- Name: user_username_key; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_username_key UNIQUE (username);


--
-- Name: worker_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY worker
    ADD CONSTRAINT worker_pkey PRIMARY KEY (user_id);


--
-- Name: fk11f9d7891ff75; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT fk11f9d7891ff75 FOREIGN KEY (quote_id) REFERENCES quote(quote_id);


--
-- Name: fk11f9da02efb95; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT fk11f9da02efb95 FOREIGN KEY (project_id) REFERENCES project(project_id);


--
-- Name: fk11f9db34851cd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT fk11f9db34851cd FOREIGN KEY (price) REFERENCES price(id);


--
-- Name: fk11f9de77d3c5d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY job
    ADD CONSTRAINT fk11f9de77d3c5d FOREIGN KEY (translation) REFERENCES translation(translation_id);


--
-- Name: fk129d8691790bba24; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translation
    ADD CONSTRAINT fk129d8691790bba24 FOREIGN KEY (lto) REFERENCES language(id);


--
-- Name: fk129d86917f2c1853; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translation
    ADD CONSTRAINT fk129d86917f2c1853 FOREIGN KEY (lfrom) REFERENCES language(id);


--
-- Name: fk129d869187153c2c; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translation
    ADD CONSTRAINT fk129d869187153c2c FOREIGN KEY (defaultprice) REFERENCES price(id);


--
-- Name: fk129d8691923d2640; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translation
    ADD CONSTRAINT fk129d8691923d2640 FOREIGN KEY (type) REFERENCES translationtype(id);


--
-- Name: fk129d8691f9fa85ce; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translation
    ADD CONSTRAINT fk129d8691f9fa85ce FOREIGN KEY (user_id) REFERENCES translator(user_id);


--
-- Name: fk185bd6f988badfbb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project
    ADD CONSTRAINT fk185bd6f988badfbb FOREIGN KEY (client) REFERENCES client(user_id);


--
-- Name: fk185bd6f996cd9f0b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project
    ADD CONSTRAINT fk185bd6f996cd9f0b FOREIGN KEY (supervisor) REFERENCES worker(user_id);


--
-- Name: fk20449bcbd0e2d9ff; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY businessclient
    ADD CONSTRAINT fk20449bcbd0e2d9ff FOREIGN KEY (user_id) REFERENCES client(user_id);


--
-- Name: fk272d85140efe95; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk272d85140efe95 FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- Name: fk272d8558e4b975; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk272d8558e4b975 FOREIGN KEY (job) REFERENCES job(job_id);


--
-- Name: fk272d85b34851cd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk272d85b34851cd FOREIGN KEY (price) REFERENCES price(id);


--
-- Name: fk272d85b5e885e9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk272d85b5e885e9 FOREIGN KEY (expert) REFERENCES translator(user_id);


--
-- Name: fk272d85e77d3c5d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk272d85e77d3c5d FOREIGN KEY (translation) REFERENCES translation(translation_id);


--
-- Name: fk357ae0b5bac0973f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_userrole
    ADD CONSTRAINT fk357ae0b5bac0973f FOREIGN KEY (user_id) REFERENCES "user"(user_id);


--
-- Name: fk48db929f878fb61; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY price
    ADD CONSTRAINT fk48db929f878fb61 FOREIGN KEY (currency) REFERENCES currencytype(id);


--
-- Name: fk49d465c88badfbb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY quote
    ADD CONSTRAINT fk49d465c88badfbb FOREIGN KEY (client) REFERENCES client(user_id);


--
-- Name: fk49d465c96cd9f0b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY quote
    ADD CONSTRAINT fk49d465c96cd9f0b FOREIGN KEY (supervisor) REFERENCES worker(user_id);


--
-- Name: fk5b315384a02efb95; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project_worker
    ADD CONSTRAINT fk5b315384a02efb95 FOREIGN KEY (project_id) REFERENCES project(project_id);


--
-- Name: fk5b315384f33239b2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project_worker
    ADD CONSTRAINT fk5b315384f33239b2 FOREIGN KEY (user_id) REFERENCES worker(user_id);


--
-- Name: fk6205d823a02efb95; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project_experts
    ADD CONSTRAINT fk6205d823a02efb95 FOREIGN KEY (project_id) REFERENCES project(project_id);


--
-- Name: fk6205d823f9fa85ce; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project_experts
    ADD CONSTRAINT fk6205d823f9fa85ce FOREIGN KEY (user_id) REFERENCES translator(user_id);


--
-- Name: fk76a5e7cbbac0973f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY client
    ADD CONSTRAINT fk76a5e7cbbac0973f FOREIGN KEY (user_id) REFERENCES "user"(user_id);


--
-- Name: fk7a1a1c578aceff8a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY emails
    ADD CONSTRAINT fk7a1a1c578aceff8a FOREIGN KEY (id) REFERENCES personaldata(id);


--
-- Name: fk7abaddea6339c0df; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY personaldata
    ADD CONSTRAINT fk7abaddea6339c0df FOREIGN KEY (user_id) REFERENCES businessclient(user_id);


--
-- Name: fk8a98e11c8aceff8a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY phonenumbers
    ADD CONSTRAINT fk8a98e11c8aceff8a FOREIGN KEY (id) REFERENCES personaldata(id);


--
-- Name: fk98f5477e8afde219; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker
    ADD CONSTRAINT fk98f5477e8afde219 FOREIGN KEY (data) REFERENCES personaldata(id);


--
-- Name: fk98f5477ebac0973f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY worker
    ADD CONSTRAINT fk98f5477ebac0973f FOREIGN KEY (user_id) REFERENCES "user"(user_id);


--
-- Name: fkb647261af33239b2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translator
    ADD CONSTRAINT fkb647261af33239b2 FOREIGN KEY (user_id) REFERENCES worker(user_id);


--
-- Name: fkdb89eaeaa02efb95; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project_freelancers
    ADD CONSTRAINT fkdb89eaeaa02efb95 FOREIGN KEY (project_id) REFERENCES project(project_id);


--
-- Name: fkdb89eaeaf9fa85ce; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY project_freelancers
    ADD CONSTRAINT fkdb89eaeaf9fa85ce FOREIGN KEY (user_id) REFERENCES translator(user_id);


--
-- Name: fkf0d1afe48afde219; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY individualclient
    ADD CONSTRAINT fkf0d1afe48afde219 FOREIGN KEY (data) REFERENCES personaldata(id);


--
-- Name: fkf0d1afe4d0e2d9ff; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY individualclient
    ADD CONSTRAINT fkf0d1afe4d0e2d9ff FOREIGN KEY (user_id) REFERENCES client(user_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

