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

SELECT pg_catalog.setval('hibernate_sequence', 375, true);


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
    comment character varying(255)
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

INSERT INTO businessclient (user_id, name) VALUES (20, 'Company');
INSERT INTO businessclient (user_id, name) VALUES (22, 'Company');
INSERT INTO businessclient (user_id, name) VALUES (24, 'Company');
INSERT INTO businessclient (user_id, name) VALUES (28, 'Company');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO client (user_id) VALUES (20);
INSERT INTO client (user_id) VALUES (22);
INSERT INTO client (user_id) VALUES (24);
INSERT INTO client (user_id) VALUES (28);
INSERT INTO client (user_id) VALUES (49);
INSERT INTO client (user_id) VALUES (64);
INSERT INTO client (user_id) VALUES (79);
INSERT INTO client (user_id) VALUES (94);
INSERT INTO client (user_id) VALUES (103);
INSERT INTO client (user_id) VALUES (105);
INSERT INTO client (user_id) VALUES (107);
INSERT INTO client (user_id) VALUES (111);
INSERT INTO client (user_id) VALUES (225);
INSERT INTO client (user_id) VALUES (245);
INSERT INTO client (user_id) VALUES (265);
INSERT INTO client (user_id) VALUES (268);
INSERT INTO client (user_id) VALUES (287);


--
-- Data for Name: currencytype; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO currencytype (id, currency) VALUES (41, '$$Quote0');
INSERT INTO currencytype (id, currency) VALUES (56, '$$Quote1');
INSERT INTO currencytype (id, currency) VALUES (71, '$$Quote2');
INSERT INTO currencytype (id, currency) VALUES (86, '$$Quote3');
INSERT INTO currencytype (id, currency) VALUES (118, '$0');
INSERT INTO currencytype (id, currency) VALUES (128, '$1');
INSERT INTO currencytype (id, currency) VALUES (138, '$2');
INSERT INTO currencytype (id, currency) VALUES (148, '$3');
INSERT INTO currencytype (id, currency) VALUES (162, '$$0');
INSERT INTO currencytype (id, currency) VALUES (174, '$$1');
INSERT INTO currencytype (id, currency) VALUES (195, 'some currency3');
INSERT INTO currencytype (id, currency) VALUES (186, '$$2');
INSERT INTO currencytype (id, currency) VALUES (200, '$$3');
INSERT INTO currencytype (id, currency) VALUES (212, '$$Project0');
INSERT INTO currencytype (id, currency) VALUES (232, '$$Project1');
INSERT INTO currencytype (id, currency) VALUES (252, '$$Project2');
INSERT INTO currencytype (id, currency) VALUES (274, '$$Project3');
INSERT INTO currencytype (id, currency) VALUES (293, 'PLN0');
INSERT INTO currencytype (id, currency) VALUES (301, 'PLN1');
INSERT INTO currencytype (id, currency) VALUES (309, 'PLN2');
INSERT INTO currencytype (id, currency) VALUES (317, 'PLN3');
INSERT INTO currencytype (id, currency) VALUES (325, 'PLN4');
INSERT INTO currencytype (id, currency) VALUES (331, 'PLN');
INSERT INTO currencytype (id, currency) VALUES (332, 'EUR');
INSERT INTO currencytype (id, currency) VALUES (333, 'CHF');
INSERT INTO currencytype (id, currency) VALUES (335, 'GBP');
INSERT INTO currencytype (id, currency) VALUES (337, 'Portuguese0');
INSERT INTO currencytype (id, currency) VALUES (339, 'Portuguese1');
INSERT INTO currencytype (id, currency) VALUES (341, 'Portuguese2');
INSERT INTO currencytype (id, currency) VALUES (343, 'JPY');
INSERT INTO currencytype (id, currency) VALUES (344, 'Portuguese3');
INSERT INTO currencytype (id, currency) VALUES (354, '$$$0');
INSERT INTO currencytype (id, currency) VALUES (360, '$$$1');
INSERT INTO currencytype (id, currency) VALUES (366, '$$$2');
INSERT INTO currencytype (id, currency) VALUES (374, '$$$3');


--
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO emails (id, email) VALUES (2, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (2, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (4, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (4, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (6, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (6, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (8, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (8, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (10, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (10, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (21, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (21, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (23, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (23, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (25, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (25, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (27, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (27, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (29, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (29, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (44, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (44, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (59, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (59, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (74, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (74, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (89, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (89, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (104, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (104, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (106, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (106, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (108, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (108, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (110, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (110, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (112, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (112, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (121, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (121, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (131, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (131, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (141, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (141, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (151, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (151, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (165, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (165, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (177, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (177, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (189, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (189, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (203, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (203, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (215, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (215, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (235, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (235, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (255, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (255, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (269, '00-222-222-222');
INSERT INTO emails (id, email) VALUES (269, 'john@doe.com');
INSERT INTO emails (id, email) VALUES (277, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (277, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (297, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (297, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (305, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (305, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (313, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (313, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (321, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (321, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (329, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (329, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (346, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (346, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (347, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (347, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (348, 'john@yahoo.com');
INSERT INTO emails (id, email) VALUES (348, 'john2@yahoo.com');
INSERT INTO emails (id, email) VALUES (348, 'newemail@email.com');


--
-- Data for Name: individualclient; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO individualclient (user_id, data) VALUES (49, 44);
INSERT INTO individualclient (user_id, data) VALUES (64, 59);
INSERT INTO individualclient (user_id, data) VALUES (79, 74);
INSERT INTO individualclient (user_id, data) VALUES (94, 89);
INSERT INTO individualclient (user_id, data) VALUES (103, 104);
INSERT INTO individualclient (user_id, data) VALUES (105, 106);
INSERT INTO individualclient (user_id, data) VALUES (107, 108);
INSERT INTO individualclient (user_id, data) VALUES (111, 112);
INSERT INTO individualclient (user_id, data) VALUES (225, 215);
INSERT INTO individualclient (user_id, data) VALUES (245, 235);
INSERT INTO individualclient (user_id, data) VALUES (265, 255);
INSERT INTO individualclient (user_id, data) VALUES (268, 269);
INSERT INTO individualclient (user_id, data) VALUES (287, 277);


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (47, 'open', '2012-04-15 19:35:33.809', 42, 21000, 40, 'Job description2', 51, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (45, 'closed', '2012-04-15 19:35:33.809', 42, 1000, 40, 'Job description', 51, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (62, 'open', '2012-04-15 19:35:34.07', 57, 21000, 55, 'Job description2', 66, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (60, 'closed', '2012-04-15 19:35:34.07', 57, 1000, 55, 'Job description', 66, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (77, 'open', '2012-04-15 19:35:34.384', 72, 21000, 70, 'Job description2', 81, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (75, 'closed', '2012-04-15 19:35:34.384', 72, 1000, 70, 'Job description', 81, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (92, 'open', '2012-04-15 19:35:34.681', 87, 21000, 85, 'Job description2', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (90, 'closed', '2012-04-15 19:35:34.681', 87, 1000, 85, 'Job description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (122, 'closed', '2012-04-15 19:35:35.408', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (132, 'closed', '2012-04-15 19:35:35.512', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (142, 'closed', '2012-04-15 19:35:35.695', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (152, 'closed', '2012-04-15 19:35:35.856', NULL, 10000, NULL, 'description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (166, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (169, 'accepted', '2012-04-15 19:35:36.176', 163, 1000, 161, 'job descriptio', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (178, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (181, 'accepted', '2012-04-15 19:35:36.372', 175, 1000, 173, 'job descriptio', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (190, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (193, 'resolved', '2012-04-15 19:35:36.563', 187, 123123, 194, 'new awesome description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (204, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (223, 'open', '2012-04-15 19:35:37.065', 213, 21000, 211, 'Job description2', NULL, 227);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (221, 'closed', '2012-04-15 19:35:37.065', 213, 1000, 211, 'Job description', NULL, 227);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (243, 'open', '2012-04-15 19:35:37.433', 233, 21000, 231, 'Job description2', NULL, 247);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (241, 'closed', '2012-04-15 19:35:37.433', 233, 1000, 231, 'Job description', NULL, 247);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (261, 'closed', '2012-04-15 19:35:37.765', 253, 1000, 251, 'Job description', NULL, 267);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (263, 'open', '2012-04-15 19:35:37.765', 253, 21000, 251, 'Job description2', NULL, 267);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (283, 'closed', '2012-04-15 19:35:38.2', 275, 1000, 273, 'Job description', NULL, NULL);
INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (285, 'open', '2012-04-15 19:35:38.2', 275, 21000, 273, 'Job description2', NULL, NULL);


--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO language (id, language) VALUES (31, 'Polish');
INSERT INTO language (id, language) VALUES (32, 'English');
INSERT INTO language (id, language) VALUES (33, 'German');
INSERT INTO language (id, language) VALUES (35, 'Italian');
INSERT INTO language (id, language) VALUES (37, 'FrenchQuote0');
INSERT INTO language (id, language) VALUES (38, 'DanishQuote0');
INSERT INTO language (id, language) VALUES (52, 'FrenchQuote1');
INSERT INTO language (id, language) VALUES (53, 'DanishQuote1');
INSERT INTO language (id, language) VALUES (67, 'FrenchQuote2');
INSERT INTO language (id, language) VALUES (68, 'DanishQuote2');
INSERT INTO language (id, language) VALUES (82, 'FrenchQuote3');
INSERT INTO language (id, language) VALUES (83, 'DanishQuote3');
INSERT INTO language (id, language) VALUES (114, 'French0');
INSERT INTO language (id, language) VALUES (115, 'Danish0');
INSERT INTO language (id, language) VALUES (124, 'French1');
INSERT INTO language (id, language) VALUES (125, 'Danish1');
INSERT INTO language (id, language) VALUES (134, 'French2');
INSERT INTO language (id, language) VALUES (135, 'Danish2');
INSERT INTO language (id, language) VALUES (144, 'French3');
INSERT INTO language (id, language) VALUES (145, 'Danish3');
INSERT INTO language (id, language) VALUES (158, 'Frenchjob0');
INSERT INTO language (id, language) VALUES (159, 'Danishjob0');
INSERT INTO language (id, language) VALUES (170, 'Frenchjob1');
INSERT INTO language (id, language) VALUES (171, 'Danishjob1');
INSERT INTO language (id, language) VALUES (182, 'Frenchjob2');
INSERT INTO language (id, language) VALUES (183, 'Danishjob2');
INSERT INTO language (id, language) VALUES (196, 'Frenchjob3');
INSERT INTO language (id, language) VALUES (197, 'Danishjob3');
INSERT INTO language (id, language) VALUES (208, 'FrenchProject0');
INSERT INTO language (id, language) VALUES (209, 'DanishProject0');
INSERT INTO language (id, language) VALUES (228, 'FrenchProject1');
INSERT INTO language (id, language) VALUES (229, 'DanishProject1');
INSERT INTO language (id, language) VALUES (248, 'FrenchProject2');
INSERT INTO language (id, language) VALUES (249, 'DanishProject2');
INSERT INTO language (id, language) VALUES (270, 'FrenchProject3');
INSERT INTO language (id, language) VALUES (271, 'DanishProject3');
INSERT INTO language (id, language) VALUES (291, 'Polish0');
INSERT INTO language (id, language) VALUES (292, 'English0');
INSERT INTO language (id, language) VALUES (299, 'Polish1');
INSERT INTO language (id, language) VALUES (300, 'English1');
INSERT INTO language (id, language) VALUES (307, 'Polish2');
INSERT INTO language (id, language) VALUES (308, 'English2');
INSERT INTO language (id, language) VALUES (315, 'Polish3');
INSERT INTO language (id, language) VALUES (316, 'English3');
INSERT INTO language (id, language) VALUES (323, 'Polish4');
INSERT INTO language (id, language) VALUES (324, 'English4');
INSERT INTO language (id, language) VALUES (350, 'LanguageTranslationFrom0');
INSERT INTO language (id, language) VALUES (351, 'LanguageTranslationTo0');
INSERT INTO language (id, language) VALUES (356, 'LanguageTranslationFrom1');
INSERT INTO language (id, language) VALUES (357, 'LanguageTranslationTo1');
INSERT INTO language (id, language) VALUES (362, 'LanguageTranslationFrom2');
INSERT INTO language (id, language) VALUES (363, 'LanguageTranslationTo2');
INSERT INTO language (id, language) VALUES (368, 'language1');
INSERT INTO language (id, language) VALUES (369, 'language2');
INSERT INTO language (id, language) VALUES (370, 'LanguageTranslationFrom3');
INSERT INTO language (id, language) VALUES (371, 'LanguageTranslationTo3');


--
-- Data for Name: personaldata; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (2, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (4, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (6, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (8, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (10, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (13, NULL, NULL, NULL, NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (15, NULL, NULL, NULL, NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (17, NULL, NULL, NULL, NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (19, NULL, NULL, NULL, NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (21, 'Firstame', 'Lastname', 'Address', 20);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (23, 'Firstame', 'Lastname', 'Address', 22);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (25, 'Firstame', 'Lastname', 'Address', 24);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (27, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (29, 'Firstame', 'Lastname', 'Address', 28);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (44, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (59, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (74, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (89, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (104, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (106, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (108, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (110, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (112, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (121, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (131, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (141, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (151, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (165, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (177, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (189, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (203, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (215, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (235, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (255, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (269, 'FirstName', 'LastName', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (277, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (297, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (305, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (313, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (321, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (329, 'Firstame', 'Lastname', 'Address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (346, 'first name', 'last name', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (347, 'first name', 'last name', 'address', NULL);
INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (348, 'new first name', 'new last name', 'new address', NULL);


--
-- Data for Name: phonenumbers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO phonenumbers (id, phonenumber) VALUES (2, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (2, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (4, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (4, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (6, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (6, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (8, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (8, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (10, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (10, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (21, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (21, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (23, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (23, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (25, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (25, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (27, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (27, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (29, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (29, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (44, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (44, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (59, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (59, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (74, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (74, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (89, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (89, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (104, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (104, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (106, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (106, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (108, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (108, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (110, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (110, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (112, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (112, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (121, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (121, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (131, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (131, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (141, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (141, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (151, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (151, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (165, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (165, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (177, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (177, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (189, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (189, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (203, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (203, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (215, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (215, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (235, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (235, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (255, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (255, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (277, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (277, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (297, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (297, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (305, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (305, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (313, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (313, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (321, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (321, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (329, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (329, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (346, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (346, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (347, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (347, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (348, '00-000-000');
INSERT INTO phonenumbers (id, phonenumber) VALUES (348, '00-001-001');
INSERT INTO phonenumbers (id, phonenumber) VALUES (348, '000-111-111');


--
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO price (id, price, currency) VALUES (40, 200, 41);
INSERT INTO price (id, price, currency) VALUES (55, 201, 56);
INSERT INTO price (id, price, currency) VALUES (70, 202, 71);
INSERT INTO price (id, price, currency) VALUES (85, 203, 86);
INSERT INTO price (id, price, currency) VALUES (117, 200, 118);
INSERT INTO price (id, price, currency) VALUES (127, 201, 128);
INSERT INTO price (id, price, currency) VALUES (137, 202, 138);
INSERT INTO price (id, price, currency) VALUES (147, 203, 148);
INSERT INTO price (id, price, currency) VALUES (161, 200, 162);
INSERT INTO price (id, price, currency) VALUES (173, 201, 174);
INSERT INTO price (id, price, currency) VALUES (194, 100, 195);
INSERT INTO price (id, price, currency) VALUES (185, 202, 186);
INSERT INTO price (id, price, currency) VALUES (199, 203, 200);
INSERT INTO price (id, price, currency) VALUES (211, 200, 212);
INSERT INTO price (id, price, currency) VALUES (231, 201, 232);
INSERT INTO price (id, price, currency) VALUES (251, 202, 252);
INSERT INTO price (id, price, currency) VALUES (273, 203, 274);
INSERT INTO price (id, price, currency) VALUES (294, 100, 293);
INSERT INTO price (id, price, currency) VALUES (302, 100, 301);
INSERT INTO price (id, price, currency) VALUES (310, 100, 309);
INSERT INTO price (id, price, currency) VALUES (318, 100, 317);
INSERT INTO price (id, price, currency) VALUES (326, 100, 325);
INSERT INTO price (id, price, currency) VALUES (338, 200, 337);
INSERT INTO price (id, price, currency) VALUES (340, 200, 339);
INSERT INTO price (id, price, currency) VALUES (342, 1000, 343);
INSERT INTO price (id, price, currency) VALUES (353, 200, 354);
INSERT INTO price (id, price, currency) VALUES (359, 201, 360);
INSERT INTO price (id, price, currency) VALUES (365, 202, 366);
INSERT INTO price (id, price, currency) VALUES (373, 203, 374);


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project (project_id, status, client, supervisor, description) VALUES (227, 'in_progress', 225, 226, 'Project description');
INSERT INTO project (project_id, status, client, supervisor, description) VALUES (247, 'in_progress', 245, 246, 'Project description');
INSERT INTO project (project_id, status, client, supervisor, description) VALUES (267, 'in_progress', 268, 266, 'New description');


--
-- Data for Name: project_experts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_experts (project_id, user_id) VALUES (227, 214);
INSERT INTO project_experts (project_id, user_id) VALUES (227, 216);
INSERT INTO project_experts (project_id, user_id) VALUES (247, 236);
INSERT INTO project_experts (project_id, user_id) VALUES (247, 234);
INSERT INTO project_experts (project_id, user_id) VALUES (267, 254);
INSERT INTO project_experts (project_id, user_id) VALUES (267, 256);


--
-- Data for Name: project_freelancers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_freelancers (project_id, user_id) VALUES (227, 220);
INSERT INTO project_freelancers (project_id, user_id) VALUES (227, 219);
INSERT INTO project_freelancers (project_id, user_id) VALUES (247, 240);
INSERT INTO project_freelancers (project_id, user_id) VALUES (247, 239);
INSERT INTO project_freelancers (project_id, user_id) VALUES (267, 259);
INSERT INTO project_freelancers (project_id, user_id) VALUES (267, 260);


--
-- Data for Name: project_worker; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO project_worker (project_id, user_id) VALUES (227, 218);
INSERT INTO project_worker (project_id, user_id) VALUES (227, 217);
INSERT INTO project_worker (project_id, user_id) VALUES (247, 238);
INSERT INTO project_worker (project_id, user_id) VALUES (247, 237);
INSERT INTO project_worker (project_id, user_id) VALUES (267, 257);
INSERT INTO project_worker (project_id, user_id) VALUES (267, 258);


--
-- Data for Name: quote; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (51, 'in_progress', 49, 50, 'Quote description');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (66, 'in_progress', 64, 65, 'Quote description');
INSERT INTO quote (quote_id, status, client, supervisor, description) VALUES (81, 'open', NULL, 80, 'new quote description');


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (222, 'accepted', 214, '2012-04-15 19:35:37.065', 213, 100, 211, 'desc', 221, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (48, 'accepted', 43, '2012-04-15 19:35:33.809', 42, 100, 40, 'desc2', 47, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (46, 'accepted', 43, '2012-04-15 19:35:33.809', 42, 100, 40, 'desc', 45, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (224, 'accepted', 214, '2012-04-15 19:35:37.065', 213, 100, 211, 'desc2', 223, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (63, 'accepted', 58, '2012-04-15 19:35:34.07', 57, 100, 55, 'desc2', 62, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (61, 'accepted', 58, '2012-04-15 19:35:34.07', 57, 100, 55, 'desc', 60, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (242, 'accepted', 234, '2012-04-15 19:35:37.433', 233, 100, 231, 'desc', 241, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (244, 'accepted', 234, '2012-04-15 19:35:37.433', 233, 100, 231, 'desc2', 243, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (78, 'accepted', 73, '2012-04-15 19:35:34.384', 72, 100, 70, 'desc2', 77, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (76, 'accepted', 73, '2012-04-15 19:35:34.383', 72, 100, 70, 'desc', 75, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (262, 'accepted', 254, '2012-04-15 19:35:37.765', 253, 100, 251, 'desc', 261, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (93, 'accepted', 88, '2012-04-15 19:35:34.681', 87, 100, 85, 'desc2', 92, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (91, 'accepted', 88, '2012-04-15 19:35:34.681', 87, 100, 85, 'desc', 90, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (123, 'accepted', 120, '2012-04-15 19:35:35.422', 119, 100, 117, 'desc', 122, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (133, 'accepted', 130, '2012-04-15 19:35:35.526', 129, 100, 127, 'desc2', 132, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (143, 'resolved', 140, '2012-04-15 19:35:35.706', 139, 20000, 137, 'new description', 142, 'good', 'new comment');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (264, 'accepted', 254, '2012-04-15 19:35:37.765', 253, 100, 251, 'desc2', 263, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (167, 'accepted', 164, '2012-04-15 19:35:36.143', 163, 100, 161, 'desc', 169, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (168, 'accepted', 164, '2012-04-15 19:35:36.143', 163, 100, 161, 'desc2', 169, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (284, 'accepted', 276, '2012-04-15 19:35:38.2', 275, 100, 273, 'desc', 283, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (179, 'accepted', 176, '2012-04-15 19:35:36.317', 175, 100, 173, 'desc', 181, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (180, 'accepted', 176, '2012-04-15 19:35:36.317', 175, 100, 173, 'desc2', 181, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (286, 'accepted', 276, '2012-04-15 19:35:38.2', 275, 100, 273, 'desc2', 285, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (191, 'accepted', 188, '2012-04-15 19:35:36.517', 187, 100, 185, 'desc', 190, 'bad', 'comm');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (192, 'accepted', 188, '2012-04-15 19:35:36.517', 187, 100, 185, 'desc2', 190, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (206, 'accepted', 202, '2012-04-15 19:35:36.75', 201, 100, 199, 'desc2', NULL, 'bad', 'comm2');
INSERT INTO task (task_id, status, expert, deadline, translation, amount, price, description, job, rating, comment) VALUES (205, 'accepted', 202, '2012-04-15 19:35:36.75', 201, 100, 199, 'desc', NULL, 'bad', 'comm');


--
-- Data for Name: translation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (42, 39, 37, 38, 40, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (57, 54, 52, 53, 55, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (72, 69, 67, 68, 70, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (87, 84, 82, 83, 85, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (119, 116, 114, 115, 117, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (129, 126, 124, 125, 127, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (139, 136, 134, 135, 137, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (149, 146, 144, 145, 147, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (163, 160, 158, 159, 161, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (175, 172, 170, 171, 173, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (187, 184, 182, 183, 185, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (201, 198, 196, 197, 199, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (213, 210, 208, 209, 211, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (233, 230, 228, 229, 231, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (253, 250, 248, 249, 251, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (275, 272, 270, 271, 273, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (295, 290, 291, 292, 294, 296);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (303, 298, 299, 300, 302, 304);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (311, 306, 307, 308, 310, 312);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (319, 314, 315, 316, 318, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (327, 322, 323, 324, 326, 328);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (355, 352, 350, 351, 353, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (361, 358, 356, 357, 359, NULL);
INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (367, 364, 369, 368, 365, NULL);


--
-- Data for Name: translationtype; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translationtype (id, description, unit) VALUES (39, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (54, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (69, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (84, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (116, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (126, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (136, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (146, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (154, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (155, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (156, 'new description', 'new unit');
INSERT INTO translationtype (id, description, unit) VALUES (160, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (172, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (184, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (198, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (210, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (230, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (250, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (272, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (290, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (298, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (306, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (314, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (322, 'description', 'word');
INSERT INTO translationtype (id, description, unit) VALUES (352, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (358, 'description', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (364, 'translation descritpion', 'unit');
INSERT INTO translationtype (id, description, unit) VALUES (372, 'description', 'unit');


--
-- Data for Name: translator; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO translator (user_id) VALUES (43);
INSERT INTO translator (user_id) VALUES (58);
INSERT INTO translator (user_id) VALUES (73);
INSERT INTO translator (user_id) VALUES (88);
INSERT INTO translator (user_id) VALUES (120);
INSERT INTO translator (user_id) VALUES (130);
INSERT INTO translator (user_id) VALUES (140);
INSERT INTO translator (user_id) VALUES (150);
INSERT INTO translator (user_id) VALUES (164);
INSERT INTO translator (user_id) VALUES (176);
INSERT INTO translator (user_id) VALUES (188);
INSERT INTO translator (user_id) VALUES (202);
INSERT INTO translator (user_id) VALUES (214);
INSERT INTO translator (user_id) VALUES (216);
INSERT INTO translator (user_id) VALUES (219);
INSERT INTO translator (user_id) VALUES (220);
INSERT INTO translator (user_id) VALUES (234);
INSERT INTO translator (user_id) VALUES (236);
INSERT INTO translator (user_id) VALUES (239);
INSERT INTO translator (user_id) VALUES (240);
INSERT INTO translator (user_id) VALUES (254);
INSERT INTO translator (user_id) VALUES (256);
INSERT INTO translator (user_id) VALUES (259);
INSERT INTO translator (user_id) VALUES (260);
INSERT INTO translator (user_id) VALUES (276);
INSERT INTO translator (user_id) VALUES (278);
INSERT INTO translator (user_id) VALUES (281);
INSERT INTO translator (user_id) VALUES (282);
INSERT INTO translator (user_id) VALUES (296);
INSERT INTO translator (user_id) VALUES (304);
INSERT INTO translator (user_id) VALUES (312);
INSERT INTO translator (user_id) VALUES (328);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "user" (user_id, username, password, description) VALUES (1, 'WorkerTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (3, 'WorkerTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (5, 'new name2', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (9, 'WorkerTest4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (12, 'userServiceTestUserCreating', '6a83321fb78375c28816ad2292381000ebd47c49', 'Decrtiption');
INSERT INTO "user" (user_id, username, password, description) VALUES (14, 'user', '507ed82e726e659e17ab9b845c2315708fe7cfdf', 'Decrtiption');
INSERT INTO "user" (user_id, username, password, description) VALUES (16, 'userServiceTestPasswordEncodingCreating', '965abcd1c07ecf1a0bcafe636c644fdc749c0d07', 'Decrtiption');
INSERT INTO "user" (user_id, username, password, description) VALUES (18, 'userServiceTestPasswordEncodingUpdating', '12ecc94827c0df6a1880759c7d9058855a5af828', 'Decrtiption');
INSERT INTO "user" (user_id, username, password, description) VALUES (20, 'BusinessClientTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (22, 'BusinessClientTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (24, 'new name4', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (28, 'BusinessClientTest4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (43, 'QuoteTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (49, 'IndividualClientQuote0', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (50, 'QuoteSupervisor0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (58, 'QuoteTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (64, 'IndividualClientQuote1', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (65, 'QuoteSupervisor1', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (73, 'QuoteTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (79, 'IndividualClientQuote2', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (80, 'QuoteSupervisor2', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (88, 'QuoteTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (94, 'IndividualClientQuote3', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (95, 'QuoteSupervisor3', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (97, 'Test', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (98, 'Test2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (99, 'new name', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (101, 'Test4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (103, 'IndividualClientTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (105, 'IndividualClientTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (107, 'IndividualClientTest3', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (111, 'IndividualClientTest4', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (120, 'TaskTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (130, 'TaskTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (140, 'TaskTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (150, 'TaskTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (164, 'JobTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (176, 'JobTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (188, 'JobTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (202, 'JobTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (214, 'projectTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (216, '2projectTranslator0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (217, 'ProjectManager0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (218, '1ProjectManager0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (219, 'projectFreelancer0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (220, '2projectFreelancer0', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (225, 'IndividualClientProject0', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (226, 'ProjectSupervisor0', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (234, 'projectTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (236, '2projectTranslator1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (237, 'ProjectManager1', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (238, '1ProjectManager1', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (239, 'projectFreelancer1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (240, '2projectFreelancer1', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (245, 'IndividualClientProject1', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (246, 'ProjectSupervisor1', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (254, 'projectTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (256, '2projectTranslator2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (257, 'ProjectManager2', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (258, '1ProjectManager2', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (259, 'projectFreelancer2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (260, '2projectFreelancer2', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (265, 'IndividualClientProject2', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (266, 'ProjectSupervisor2', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (268, 'UpdateProjectClient', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (276, 'projectTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (278, '2projectTranslator3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (279, 'ProjectManager3', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (280, '1ProjectManager3', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (281, 'projectFreelancer3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (282, '2projectFreelancer3', 'pass', 'desc');
INSERT INTO "user" (user_id, username, password, description) VALUES (287, 'IndividualClientProject3', 'pass', 'individual client');
INSERT INTO "user" (user_id, username, password, description) VALUES (288, 'ProjectSupervisor3', 'pass', 'supervisor description');
INSERT INTO "user" (user_id, username, password, description) VALUES (296, 'TranslatorTest', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (304, 'TranslatorTest2', 'password', 'description');
INSERT INTO "user" (user_id, username, password, description) VALUES (312, 'new name1', 'password', 'new description');
INSERT INTO "user" (user_id, username, password, description) VALUES (328, 'TranslatorTest4', 'password', 'description');


--
-- Data for Name: user_userrole; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_userrole (user_id, user_role) VALUES (1, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (1, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (3, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (3, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (5, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (5, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (9, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (9, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (12, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (14, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (20, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (20, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (22, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (22, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (24, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (24, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (28, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (28, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (43, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (43, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (49, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (50, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (50, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (58, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (58, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (64, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (65, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (65, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (73, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (73, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (79, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (80, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (80, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (88, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (88, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (94, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (95, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (95, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (97, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (97, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (98, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (98, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (99, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (99, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (101, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (101, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (103, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (103, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (105, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (105, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (107, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (107, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (111, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (111, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (120, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (120, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (130, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (130, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (140, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (140, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (150, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (150, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (164, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (164, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (176, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (176, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (188, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (188, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (202, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (202, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (214, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (214, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (216, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (216, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (219, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (219, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (220, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (220, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (225, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (226, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (226, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (234, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (234, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (236, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (236, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (239, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (239, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (240, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (240, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (245, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (246, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (246, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (254, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (254, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (256, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (256, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (259, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (259, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (260, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (260, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (265, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (266, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (266, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (276, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (276, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (278, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (278, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (281, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (281, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (282, 'ROLE_VERIFICATOR');
INSERT INTO user_userrole (user_id, user_role) VALUES (282, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (287, 'ROLE_CLIENT');
INSERT INTO user_userrole (user_id, user_role) VALUES (288, 'ROLE_EXECUTIVE');
INSERT INTO user_userrole (user_id, user_role) VALUES (288, 'ROLE_PROJECTMNAGER');
INSERT INTO user_userrole (user_id, user_role) VALUES (296, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (296, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (304, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (304, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (312, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (312, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (328, 'ROLE_USER');
INSERT INTO user_userrole (user_id, user_role) VALUES (328, 'ROLE_EXPERT');


--
-- Data for Name: worker; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO worker (user_id, data) VALUES (1, 2);
INSERT INTO worker (user_id, data) VALUES (3, 4);
INSERT INTO worker (user_id, data) VALUES (5, 6);
INSERT INTO worker (user_id, data) VALUES (9, 10);
INSERT INTO worker (user_id, data) VALUES (12, 13);
INSERT INTO worker (user_id, data) VALUES (14, 15);
INSERT INTO worker (user_id, data) VALUES (16, 17);
INSERT INTO worker (user_id, data) VALUES (18, 19);
INSERT INTO worker (user_id, data) VALUES (43, 44);
INSERT INTO worker (user_id, data) VALUES (50, 44);
INSERT INTO worker (user_id, data) VALUES (58, 59);
INSERT INTO worker (user_id, data) VALUES (65, 59);
INSERT INTO worker (user_id, data) VALUES (73, 74);
INSERT INTO worker (user_id, data) VALUES (80, 74);
INSERT INTO worker (user_id, data) VALUES (88, 89);
INSERT INTO worker (user_id, data) VALUES (95, 89);
INSERT INTO worker (user_id, data) VALUES (120, 121);
INSERT INTO worker (user_id, data) VALUES (130, 131);
INSERT INTO worker (user_id, data) VALUES (140, 141);
INSERT INTO worker (user_id, data) VALUES (150, 151);
INSERT INTO worker (user_id, data) VALUES (164, 165);
INSERT INTO worker (user_id, data) VALUES (176, 177);
INSERT INTO worker (user_id, data) VALUES (188, 189);
INSERT INTO worker (user_id, data) VALUES (202, 203);
INSERT INTO worker (user_id, data) VALUES (214, 215);
INSERT INTO worker (user_id, data) VALUES (216, 215);
INSERT INTO worker (user_id, data) VALUES (217, 215);
INSERT INTO worker (user_id, data) VALUES (218, 215);
INSERT INTO worker (user_id, data) VALUES (219, 215);
INSERT INTO worker (user_id, data) VALUES (220, 215);
INSERT INTO worker (user_id, data) VALUES (226, 215);
INSERT INTO worker (user_id, data) VALUES (234, 235);
INSERT INTO worker (user_id, data) VALUES (236, 235);
INSERT INTO worker (user_id, data) VALUES (237, 235);
INSERT INTO worker (user_id, data) VALUES (238, 235);
INSERT INTO worker (user_id, data) VALUES (239, 235);
INSERT INTO worker (user_id, data) VALUES (240, 235);
INSERT INTO worker (user_id, data) VALUES (246, 235);
INSERT INTO worker (user_id, data) VALUES (254, 255);
INSERT INTO worker (user_id, data) VALUES (256, 255);
INSERT INTO worker (user_id, data) VALUES (257, 255);
INSERT INTO worker (user_id, data) VALUES (258, 255);
INSERT INTO worker (user_id, data) VALUES (259, 255);
INSERT INTO worker (user_id, data) VALUES (260, 255);
INSERT INTO worker (user_id, data) VALUES (266, 255);
INSERT INTO worker (user_id, data) VALUES (276, 277);
INSERT INTO worker (user_id, data) VALUES (278, 277);
INSERT INTO worker (user_id, data) VALUES (279, 277);
INSERT INTO worker (user_id, data) VALUES (280, 277);
INSERT INTO worker (user_id, data) VALUES (281, 277);
INSERT INTO worker (user_id, data) VALUES (282, 277);
INSERT INTO worker (user_id, data) VALUES (288, 277);
INSERT INTO worker (user_id, data) VALUES (296, 297);
INSERT INTO worker (user_id, data) VALUES (304, 305);
INSERT INTO worker (user_id, data) VALUES (312, 313);
INSERT INTO worker (user_id, data) VALUES (328, 329);


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

