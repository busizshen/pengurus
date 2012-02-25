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

SELECT pg_catalog.setval('hibernate_sequence', 12, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "user" (user_id, username, password, description) VALUES (7, 'jSmith', 'unknownPass', 'I''m specialist in Italic and Romance languages.');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: businessclient; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: bussinessclient; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: currencytype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO currencytype (id, currency) VALUES (5, '€');


--
-- Data for Name: personaldata; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO personaldata (id, firstname, lastname, address, user_id) VALUES (8, 'Jeremy', 'Smith', '139 Gunnersbury Avenue, London W3 8LG, United Kingdom', NULL);


--
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO emails (id, email) VALUES (8, 'jeremy_smith@yahoo.com');
INSERT INTO emails (id, email) VALUES (8, 'jeremy_smith@gmail.com');


--
-- Data for Name: individualclient; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO language (id, language) VALUES (1, 'Latin');
INSERT INTO language (id, language) VALUES (2, 'Ancient Greek');
INSERT INTO language (id, language) VALUES (12, 'Hindi');


--
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO price (id, price, currency) VALUES (4, 12, 5);


--
-- Data for Name: worker; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO worker (user_id, data) VALUES (7, 8);


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: quote; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: translationtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO translationtype (id, description, unit) VALUES (3, '5 chapters of Greek Mythology by Edith Hamilton', 'pages');


--
-- Data for Name: translator; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO translator (user_id) VALUES (7);


--
-- Data for Name: translation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO translation (translation_id, type, lfrom, lto, defaultprice, user_id) VALUES (6, 3, 1, 2, 4, 7);


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO job (job_id, status, deadline, translation, amount, price, description, quote_id, project_id) VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: phonenumbers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO phonenumbers (id, phonenumber) VALUES (8, '3-210-552-2311');
INSERT INTO phonenumbers (id, phonenumber) VALUES (8, '1-888-242‑2100');


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


--
-- Data for Name: user_userrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_userrole (user_id, user_role) VALUES (7, 'ROLE_EXPERT');
INSERT INTO user_userrole (user_id, user_role) VALUES (7, 'ROLE_VERIFICATOR');


--
-- PostgreSQL database dump complete
--

