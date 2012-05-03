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

SELECT pg_catalog.setval('hibernate_sequence', 507, true);


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
    quoteid bigint,
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
    reviewer bigint,
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
    defaultprice bigint
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
-- Name: translator_translation; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE translator_translation (
    user_id bigint NOT NULL,
    translation_id bigint NOT NULL
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

INSERT INTO businessclient (user_id, name) VALUES (450, 'Nightmare Company');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO client (user_id) VALUES (381);
INSERT INTO client (user_id) VALUES (383);
INSERT INTO client (user_id) VALUES (450);


--
-- Data for Name: currencytype; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO currencytype (id, currency) VALUES (5, 'RUB (Russian ruble)');
INSERT INTO currencytype (id, currency) VALUES (45, 'AFN (Afghan afghani)');
INSERT INTO currencytype (id, currency) VALUES (60, 'EUR (Euro)');
INSERT INTO currencytype (id, currency) VALUES (75, 'ALL (Albanian lek)');
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
INSERT INTO currencytype (id, currency) VALUES (253, 'USD (United States dollar)');
INSERT INTO currencytype (id, currency) VALUES (90, 'GBP (British pound)');


--
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO emails (id, email) VALUES (301, 'admin@pengurus.com');
INSERT INTO emails (id, email) VALUES (382, 'jsmith@yahoo.com');
INSERT INTO emails (id, email) VALUES (384, 'bb@yahoo.com');
INSERT INTO emails (id, email) VALUES (394, 'stork@pengurus.com');
INSERT INTO emails (id, email) VALUES (398, 'nina@pengurus.com');
INSERT INTO emails (id, email) VALUES (398, 'juanita.hernandez@gmail.com');
INSERT INTO emails (id, email) VALUES (422, 'novak@pengurus.com');
INSERT INTO emails (id, email) VALUES (423, 'novak@pengurus.com');
INSERT INTO emails (id, email) VALUES (427, 'george.tigre@pengurus.com');
INSERT INTO emails (id, email) VALUES (447, 'vache@pengurus.com');
INSERT INTO emails (id, email) VALUES (447, 'vache.michael@gmail.com');
INSERT INTO emails (id, email) VALUES (449, 'hp.lindstrom@nrc.no');
INSERT INTO emails (id, email) VALUES (451, 'scary@nightmare.uk');
INSERT INTO emails (id, email) VALUES (452, 'toddie@nightmare.uk');
INSERT INTO emails (id, email) VALUES (453, 'mary@nightmare.uk');
INSERT INTO emails (id, email) VALUES (467, 'stork@pengurus.com');
INSERT INTO emails (id, email) VALUES (469, 'annie@pengurus.com');


--
-- Data for Name: individualclient; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO individualclient (user_id, data) VALUES (381, 382);
INSERT INTO individualclient (user_id, data) VALUES (383, 384);


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (470, 'open', '2012-05-19 00:00:00', 391, 12, 471, 'Personal interpreter for 12 hours.', 428, 472);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (480, 'open', '2012-05-25 00:00:00', 463, 32, 481, 'translation of the second chapter of the masucript.', 477, 484);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (482, 'open', '2012-06-01 00:00:00', 389, 25, 483, 'translation of the spanish errate.', 477, 484);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (478, 'open', '2012-05-18 00:00:00', 463, 24, 479, 'Translation of the first chapter of the manuscript.', 477, 484);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (504, 'open', '2012-05-05 00:00:00', 502, 25, 505, 'Translation of the tech-documentation', 501, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (506, 'open', '2012-05-10 00:00:00', 459, 125, 507, 'sworn paper', 501, NULL);


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

INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (301, 'John', 'Smith', '1 Reds Road, Manchester E5 00T, United Kingdom', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (382, 'Jeremy', 'Smith', '139 Gunnersbury Avenue, London W3 8LG, England', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (384, 'Bernadette', 'Breeze', 'Avenida João Jorge, 112, ap. 31 Vila Industrial Campinas - SP 13035-680', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (394, 'James', 'Storker', '132, My Street, Kingston, New York 12401 United States', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (398, 'Juanita', 'Hernandez', 'Av. Bellavista N° 185 Dep. 609 8420507 Recoleta Santiago', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (422, 'Joseph', 'Novák', 'Brněnská 56, 123 07 Jitrnice', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (423, 'Joseph', 'Novák', 'Brněnská 56, 123 07 Jitrnice', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (425, 'José Carlos Araújo', 'Rodriquez', 'Piedras 623, Piso 2, depto 4 C1070AAM, Capital Federa', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (427, 'George', 'Tigre', 'Nelson N° 10, Dep. 415 Cerro Barón Valparaíso', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (447, 'Michael', 'Vache', '59 Rue de Rivoli 75001 Paris, France', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (449, 'Hans Peter ', 'Lindstrøm', 'Bjørnstjerne Bjørnsons plass 1, 0340 Oslo, Norway', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (451, 'Joe', 'Scary', '13 St. Leonadrd''s Rd, H440 021 Exeter, England', 450);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (452, 'Todd', 'Sweeney', '2 Commin''s Rd, H408 R22 Exeter, England', 450);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (453, 'Mary', 'Night', '5 Greyfriars Rd., H404 T21 Exeter, England', 450);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (467, 'James', 'Storker', '132, My Street, Kingston, New York 12401 United States', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (469, 'Anne', 'Halloway', '12 Greenhouse St, 9JE 8D01 London, England', NULL);


--
-- Data for Name: phonenumbers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO phonenumbers (id, phonenumber) VALUES (301, '9-003-092‑1110');
INSERT INTO phonenumbers (id, phonenumber) VALUES (382, '(022) 7287-4172');
INSERT INTO phonenumbers (id, phonenumber) VALUES (384, '(042) 7325 - 8872');
INSERT INTO phonenumbers (id, phonenumber) VALUES (394, '(0011) 6735 331 003');
INSERT INTO phonenumbers (id, phonenumber) VALUES (422, '(013) 827 93 83');
INSERT INTO phonenumbers (id, phonenumber) VALUES (423, '(013) 827 93 83');
INSERT INTO phonenumbers (id, phonenumber) VALUES (447, '(031) 6573 8281');
INSERT INTO phonenumbers (id, phonenumber) VALUES (451, '(048) 8237 4231');
INSERT INTO phonenumbers (id, phonenumber) VALUES (467, '(0011) 6735 331 003');


--
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO price (id, price, currency) VALUES (378, 15, 90);
INSERT INTO price (id, price, currency) VALUES (380, 15, 90);
INSERT INTO price (id, price, currency) VALUES (386, 15, 90);
INSERT INTO price (id, price, currency) VALUES (388, 15, 90);
INSERT INTO price (id, price, currency) VALUES (390, 12, 90);
INSERT INTO price (id, price, currency) VALUES (392, 12, 90);
INSERT INTO price (id, price, currency) VALUES (396, 15, 90);
INSERT INTO price (id, price, currency) VALUES (400, 17, 90);
INSERT INTO price (id, price, currency) VALUES (402, 17, 90);
INSERT INTO price (id, price, currency) VALUES (404, 17, 90);
INSERT INTO price (id, price, currency) VALUES (406, 17, 90);
INSERT INTO price (id, price, currency) VALUES (408, 17, 90);
INSERT INTO price (id, price, currency) VALUES (410, 17, 90);
INSERT INTO price (id, price, currency) VALUES (412, 17, 90);
INSERT INTO price (id, price, currency) VALUES (414, 17, 90);
INSERT INTO price (id, price, currency) VALUES (416, 17, 90);
INSERT INTO price (id, price, currency) VALUES (418, 7, 90);
INSERT INTO price (id, price, currency) VALUES (420, 7, 90);
INSERT INTO price (id, price, currency) VALUES (430, 144, 90);
INSERT INTO price (id, price, currency) VALUES (432, 144, 90);
INSERT INTO price (id, price, currency) VALUES (435, 144, 90);
INSERT INTO price (id, price, currency) VALUES (439, 15, 90);
INSERT INTO price (id, price, currency) VALUES (441, 15, 90);
INSERT INTO price (id, price, currency) VALUES (443, 13, 90);
INSERT INTO price (id, price, currency) VALUES (445, 13, 90);
INSERT INTO price (id, price, currency) VALUES (455, 12, 90);
INSERT INTO price (id, price, currency) VALUES (458, 0, 90);
INSERT INTO price (id, price, currency) VALUES (460, 2, 90);
INSERT INTO price (id, price, currency) VALUES (462, 2, 90);
INSERT INTO price (id, price, currency) VALUES (464, 6, 90);
INSERT INTO price (id, price, currency) VALUES (466, 6, 90);
INSERT INTO price (id, price, currency) VALUES (471, 144, 90);
INSERT INTO price (id, price, currency) VALUES (474, 144, 90);
INSERT INTO price (id, price, currency) VALUES (479, 144, 90);
INSERT INTO price (id, price, currency) VALUES (481, 192, 90);
INSERT INTO price (id, price, currency) VALUES (483, 300, 90);
INSERT INTO price (id, price, currency) VALUES (486, 300, 90);
INSERT INTO price (id, price, currency) VALUES (490, 96, 90);
INSERT INTO price (id, price, currency) VALUES (492, 96, 90);
INSERT INTO price (id, price, currency) VALUES (496, 72, 90);
INSERT INTO price (id, price, currency) VALUES (498, 72, 90);
INSERT INTO price (id, price, currency) VALUES (503, 6, 253);
INSERT INTO price (id, price, currency) VALUES (505, 150, 90);
INSERT INTO price (id, price, currency) VALUES (507, 250, 90);


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project (project_id, quoteid, client, supervisor, description) VALUES (472, 0, 383, 424, 'Intérprete de personal para la conferencia en Nueva York.
Personal interpreter for the conference in New York.');
INSERT INTO project (project_id, quoteid, client, supervisor, description) VALUES (484, 0, 450, 426, 'Translation of latin manuscript, which is going to be part of a horror scenario.');


--
-- Data for Name: project_experts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_experts (project_id, user_id) VALUES (472, 397);
INSERT INTO project_experts (project_id, user_id) VALUES (484, 393);
INSERT INTO project_experts (project_id, user_id) VALUES (484, 446);
INSERT INTO project_experts (project_id, user_id) VALUES (484, 468);


--
-- Data for Name: project_freelancers; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: project_worker; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_worker (project_id, user_id) VALUES (472, 424);
INSERT INTO project_worker (project_id, user_id) VALUES (484, 446);


--
-- Data for Name: quote; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (428, 'accepted', 383, 424, 'Intérprete de personal para la conferencia en Nueva York.
Personal interpreter for the conference in New York.');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (477, 'in_progress', 450, 426, 'Translation of latin manuscript, which is going to be part of a horror scenario.');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (501, 'in_progress', 381, 446, 'english -> french, some tech-documentation + one sworn paper.');


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO task (task_id, status, expert, reviewer, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (473, 'open', 397, 393, '2012-05-19 00:00:00', 391, 12, 474, 'Personal interpreter in New York', 470, NULL, NULL, NULL);
INSERT INTO task (task_id, status, expert, reviewer, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (485, 'open', 446, 426, '2012-06-01 00:00:00', 389, 25, 486, 'We need to record translation of the spanish errate.', 482, NULL, NULL, NULL);
INSERT INTO task (task_id, status, expert, reviewer, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (489, 'open', 468, 426, '2012-05-25 00:00:00', 463, 16, 490, 'translation of the first half', 480, NULL, NULL, NULL);
INSERT INTO task (task_id, status, expert, reviewer, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (491, 'open', 393, 426, '2012-05-25 00:00:00', 463, 16, 492, 'translation of the second part', 480, NULL, NULL, NULL);
INSERT INTO task (task_id, status, expert, reviewer, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (495, 'open', 393, 426, '2012-05-18 00:00:00', 463, 12, 496, 'translation of the first part', 478, NULL, NULL, NULL);
INSERT INTO task (task_id, status, expert, reviewer, deadline, translation, amount, price, description, job, rating, comment, job_id) VALUES (497, 'open', 468, 426, '2012-05-18 00:00:00', 463, 12, 498, 'translation of the second part', 478, NULL, NULL, NULL);


--
-- Data for Name: translation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (377, 368, 36, 37, 378);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (379, 368, 37, 36, 380);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (385, 368, 72, 36, 386);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (389, 368, 42, 36, 390);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (391, 368, 36, 42, 392);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (395, 368, 36, 72, 396);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (411, 368, 200, 36, 412);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (415, 368, 36, 200, 416);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (417, 368, 237, 200, 418);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (419, 368, 200, 237, 420);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (438, 368, 36, 119, 439);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (440, 368, 119, 36, 441);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (442, 368, 119, 42, 443);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (444, 368, 42, 119, 445);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (459, 370, 36, 37, 460);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (461, 370, 37, 36, 462);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (463, 367, 1, 36, 464);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (465, 367, 36, 1, 466);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice) VALUES (502, 371, 119, 36, 503);


--
-- Data for Name: translationtype; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translationtype (id, description, unit) VALUES (366, 'written', 'line');
INSERT INTO translationtype (id, description, unit) VALUES (369, 'sworn', 'page');
INSERT INTO translationtype (id, description, unit) VALUES (372, 'express', 'line');
INSERT INTO translationtype (id, description, unit) VALUES (373, 'technical', 'line');
INSERT INTO translationtype (id, description, unit) VALUES (374, 'technical', 'page');
INSERT INTO translationtype (id, description, unit) VALUES (375, 'specialized', 'page');
INSERT INTO translationtype (id, description, unit) VALUES (376, 'specialized', 'line');
INSERT INTO translationtype (id, description, unit) VALUES (368, 'oral', 'hour');
INSERT INTO translationtype (id, description, unit) VALUES (370, 'sworn', 'line');
INSERT INTO translationtype (id, description, unit) VALUES (367, 'written', 'page');
INSERT INTO translationtype (id, description, unit) VALUES (371, 'express', 'page');


--
-- Data for Name: translator; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translator (user_id) VALUES (393);
INSERT INTO translator (user_id) VALUES (397);
INSERT INTO translator (user_id) VALUES (421);
INSERT INTO translator (user_id) VALUES (426);
INSERT INTO translator (user_id) VALUES (446);
INSERT INTO translator (user_id) VALUES (448);
INSERT INTO translator (user_id) VALUES (468);


--
-- Data for Name: translator_translation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translator_translation (user_id, translation_id) VALUES (397, 389);
INSERT INTO translator_translation (user_id, translation_id) VALUES (397, 391);
INSERT INTO translator_translation (user_id, translation_id) VALUES (421, 419);
INSERT INTO translator_translation (user_id, translation_id) VALUES (421, 411);
INSERT INTO translator_translation (user_id, translation_id) VALUES (421, 417);
INSERT INTO translator_translation (user_id, translation_id) VALUES (421, 415);
INSERT INTO translator_translation (user_id, translation_id) VALUES (426, 391);
INSERT INTO translator_translation (user_id, translation_id) VALUES (426, 389);
INSERT INTO translator_translation (user_id, translation_id) VALUES (446, 438);
INSERT INTO translator_translation (user_id, translation_id) VALUES (446, 442);
INSERT INTO translator_translation (user_id, translation_id) VALUES (446, 391);
INSERT INTO translator_translation (user_id, translation_id) VALUES (446, 444);
INSERT INTO translator_translation (user_id, translation_id) VALUES (446, 440);
INSERT INTO translator_translation (user_id, translation_id) VALUES (446, 389);
INSERT INTO translator_translation (user_id, translation_id) VALUES (448, 395);
INSERT INTO translator_translation (user_id, translation_id) VALUES (448, 385);
INSERT INTO translator_translation (user_id, translation_id) VALUES (448, 377);
INSERT INTO translator_translation (user_id, translation_id) VALUES (448, 379);
INSERT INTO translator_translation (user_id, translation_id) VALUES (393, 463);
INSERT INTO translator_translation (user_id, translation_id) VALUES (393, 465);
INSERT INTO translator_translation (user_id, translation_id) VALUES (393, 385);
INSERT INTO translator_translation (user_id, translation_id) VALUES (468, 465);
INSERT INTO translator_translation (user_id, translation_id) VALUES (468, 463);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "user" (user_id, username, password, description) VALUES (300, 'admin', 'a40546cc4fd6a12572828bb803380888ad1bfdab', 'I''m admin');
INSERT INTO "user" (user_id, username, password, description) VALUES (381, 'jerry.s', 'cc342d992e1014c56474df8a00d1495e774d2982', 'from DC&K company');
INSERT INTO "user" (user_id, username, password, description) VALUES (383, 'bern', '6eb0e544f61216c8cec70ce3d0d2b27aeb744b68', '-');
INSERT INTO "user" (user_id, username, password, description) VALUES (397, 'nina', 'f89e3c4d06e7dc7b675244513d76ccdcda06dfec', 'Adicto al trabajo');
INSERT INTO "user" (user_id, username, password, description) VALUES (421, 'novak', '771b57f6f7c3e8afa0232601f0b45c50233b2ff1', 'Praha - město Kafky, pojď, miláčku!');
INSERT INTO "user" (user_id, username, password, description) VALUES (424, 'jose', 'ffaa6ff3396b774265fbda9bc9d88abdcc05021f', 'Viva Madrid, España ¡Salud!');
INSERT INTO "user" (user_id, username, password, description) VALUES (426, 'tiger', '1ba4e55f2b0fa0fee15fe045f0bf4f2f1059ec8d', 'Not today, seriously?');
INSERT INTO "user" (user_id, username, password, description) VALUES (446, 'vache', '939567e561817fc2b39465a60aae94b23e601199', 'Parlez-vous français? Non, juste expliquer.');
INSERT INTO "user" (user_id, username, password, description) VALUES (448, 'haper', 'd9c59d85a1a66357b9789818b2e52051a2a2664c', 'Norge, kongedømmet den hvite ulven.');
INSERT INTO "user" (user_id, username, password, description) VALUES (450, 'nightmare', 'b89c5f897a3e0f838c92672d5001f8cf3e3bc944', 'production of horror films');
INSERT INTO "user" (user_id, username, password, description) VALUES (393, 'stork', '7ab44f0344ad619e7feeb9d2d44e0633ac151e02', 'Wicked sick');
INSERT INTO "user" (user_id, username, password, description) VALUES (468, 'annie', '6d71b874f8359d59b645d7e52b18523061cb3460', 'Each myth is mistic in its truth.');


--
-- Data for Name: user_userrole; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_userrole (user_id, user_role) VALUES (300, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (300, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (300, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (381, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (381, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (383, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (383, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (397, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (397, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (397, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (397, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (421, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (421, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (421, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (421, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (421, 'ROLE_PROJECTMANAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (424, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (424, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (424, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (424, 'ROLE_PROJECTMANAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (426, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (426, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (426, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (426, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (426, 'ROLE_PROJECTMANAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (446, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (446, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (446, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (446, 'ROLE_PROJECTMANAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (448, 'ROLE_FREELANCER');
INSERT INTO user_userrole (user_id, user_role) VALUES (448, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (448, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (448, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (450, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (450, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (393, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (393, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (393, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (393, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (393, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (468, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (468, 'ROLE_WORKER');
INSERT INTO user_userrole (user_id, user_role) VALUES (468, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (468, 'ROLE_FREELANCER');


--
-- Data for Name: worker; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO worker (user_id, data) VALUES (300, 301);
INSERT INTO worker (user_id, data) VALUES (397, 398);
INSERT INTO worker (user_id, data) VALUES (421, 423);
INSERT INTO worker (user_id, data) VALUES (424, 425);
INSERT INTO worker (user_id, data) VALUES (426, 427);
INSERT INTO worker (user_id, data) VALUES (446, 447);
INSERT INTO worker (user_id, data) VALUES (448, 449);
INSERT INTO worker (user_id, data) VALUES (393, 467);
INSERT INTO worker (user_id, data) VALUES (468, 469);


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
-- Name: translator_translation_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY translator_translation
    ADD CONSTRAINT translator_translation_pkey PRIMARY KEY (user_id, translation_id);


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
-- Name: fk1d96b3ec209dff15; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translator_translation
    ADD CONSTRAINT fk1d96b3ec209dff15 FOREIGN KEY (translation_id) REFERENCES translation(translation_id);


--
-- Name: fk1d96b3ecf9fa85ce; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translator_translation
    ADD CONSTRAINT fk1d96b3ecf9fa85ce FOREIGN KEY (user_id) REFERENCES translator(user_id);


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
-- Name: fk272d85f32e25c4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk272d85f32e25c4 FOREIGN KEY (reviewer) REFERENCES translator(user_id);


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

