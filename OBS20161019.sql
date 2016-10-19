--
-- PostgreSQL database dump
--

CREATE DATABASE db_obs
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account (
    account_no bigint NOT NULL,
    jai integer,
    preffered_name character varying(150),
    balance numeric
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: constant; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE constant (
    field_name character varying(50) NOT NULL,
    field_value character varying(200)
);


ALTER TABLE public.constant OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE customer (
    customer_id character varying(8) NOT NULL,
    full_name character varying(150),
    birthday date,
    salutation character varying(10) DEFAULT 'Ohters'::character varying,
    national_id character varying(100),
    marital_stat character varying(10),
    gender character varying(10),
    email character varying(50),
    race character varying(20) DEFAULT 'Others'::character varying,
    permanent_address character varying(500),
    postal_code integer,
    tel_no character varying(15),
    mob_no character varying(15),
    promo_materials character varying(3),
    disclosure_info character varying(3),
    other_salutation character varying(10),
    other_race character varying(10)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_link; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE customer_link (
    link_id character varying(10) NOT NULL,
    customer_id character varying(10) NOT NULL,
    account_no bigint NOT NULL
);


ALTER TABLE public.customer_link OWNER TO postgres;

--
-- Name: transact; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transact (
    transaction_id bigint NOT NULL,
    transaction_date timestamp without time zone,
    transation_type character varying(1),
    balance_before numeric,
    balance_after numeric,
    receiver_account_no bigint,
    sender_account_no bigint,
    shop_name character varying(50),
    atm_ref_id character varying(10)
);


ALTER TABLE public.transact OWNER TO postgres;

--
-- Name: transfer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transfer (
    account_no bigint NOT NULL,
    account_no_to bigint,
    amount numeric,
    auth_code bigint,
    transfer_status character varying(1)
);


ALTER TABLE public.transfer OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    customer_id character varying(8) NOT NULL,
    username character varying(8),
    password character varying(10),
    last_log timestamp without time zone
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: verify; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE verify (
    link_id character varying(12) NOT NULL,
    web_verify bigint,
    atm_verify bigint,
    verify_status character varying(1) DEFAULT 'N'::character varying,
    reg_date timestamp without time zone
);


ALTER TABLE public.verify OWNER TO postgres;

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account (account_no, jai, preffered_name, balance) FROM stdin;
123456789051	1	Second Account	8900
123456789052	1	Third Account	10900
123456789050	1	Test Name	7000
123456789053	1	Justine Account	6200
123456789060	1	Jenirose Petronio	10000
\.


--
-- Data for Name: constant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY constant (field_name, field_value) FROM stdin;
Salutation	Mr
Salutation	Miss
Salutation	Madam
Salutation	Others
Marital Status	Married
Marital Status	Single
Marital Status	Widowed
Marital Status	Divorced
Gender	Male
Gender	Female
Race	Malay
Race	Chinese
Race	Indian
Race	Filipino
Race	Others
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY customer (customer_id, full_name, birthday, salutation, national_id, marital_stat, gender, email, race, permanent_address, postal_code, tel_no, mob_no, promo_materials, disclosure_info, other_salutation, other_race) FROM stdin;
C7581972	Test Name	1995-02-19	Mr	1995-02-19170902375836357089104	Single	Male	galvezsergio19@yahoo.com	Others	Paranaque	1709	(12)12-12-121	(21)21-21-212	Y	Y		Japanese
C3969909	Jenirose Petronio	1993-10-30	Ohters	\N	\N	\N	\N	Others	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: customer_link; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY customer_link (link_id, customer_id, account_no) FROM stdin;
L4938860	C7581972	123456789050
L4742770	C7581972	123456789051
L9684079	C7581972	123456789052
L8764135	C7581972	123456789053
L8419020	C3969909	123456789060
\.


--
-- Data for Name: transact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transact (transaction_id, transaction_date, transation_type, balance_before, balance_after, receiver_account_no, sender_account_no, shop_name, atm_ref_id) FROM stdin;
2544905918	2016-04-22 11:43:35.483	S	9000	8500	123456789051	123456789050	NA	NA
2498471075	2016-04-22 11:43:35.543	R	9000	9500	123456789051	123456789050	NA	NA
6233876146	2016-04-22 11:49:40.649	S	9500	8900	123456789050	123456789051	NA	NA
9795398976	2016-04-22 11:49:40.701	R	8500	9100	123456789050	123456789051	NA	NA
8966899648	2016-04-22 11:54:51.131	S	9100	8200	123456789052	123456789050	NA	NA
8554603886	2016-04-22 11:54:51.203	R	10000	10900	123456789052	123456789050	NA	NA
5087618733	2016-06-13 16:01:41.682	S	8200	7000	123456789053	123456789050	NA	NA
5798004201	2016-06-13 16:01:41.783	R	5000	6200	123456789053	123456789050	NA	NA
\.


--
-- Data for Name: transfer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transfer (account_no, account_no_to, amount, auth_code, transfer_status) FROM stdin;
123456789050	123456789051	500	2929361913	Y
123456789051	123456789050	600	7386577117	Y
123456789050	123456789052	900	9762613908	Y
123456789050	\N	\N	6142858120	N
123456789050	123456789053	1200	6748264953	Y
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (customer_id, username, password, last_log) FROM stdin;
C7581972	Uuser	P@sw0rd@@	2016-06-21 18:06:07.582
\.


--
-- Data for Name: verify; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY verify (link_id, web_verify, atm_verify, verify_status, reg_date) FROM stdin;
L4938860	4623350500	5558064402	Y	2016-04-22 11:38:07.576
L4742770	3628737191	9993011687	Y	2016-04-22 11:42:22.62
L9684079	6927233494	7142954966	Y	2016-04-22 11:53:19.132
L8764135	7782275149	8854151142	Y	2016-06-13 15:59:25.067
L8419020	4518370442	4028312789	Y	2016-06-20 10:29:22.025
\.


--
-- Name: account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_no);


--
-- Name: customer_link_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY customer_link
    ADD CONSTRAINT customer_link_pkey PRIMARY KEY (link_id);


--
-- Name: customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- Name: transact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transact
    ADD CONSTRAINT transact_pkey PRIMARY KEY (transaction_id);


--
-- Name: customer_link_account_no_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY customer_link
    ADD CONSTRAINT customer_link_account_no_fkey FOREIGN KEY (account_no) REFERENCES account(account_no);


--
-- Name: customer_link_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY customer_link
    ADD CONSTRAINT customer_link_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES customer(customer_id);


--
-- Name: transfer_account_no_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transfer
    ADD CONSTRAINT transfer_account_no_fkey FOREIGN KEY (account_no) REFERENCES account(account_no);


--
-- Name: users_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES customer(customer_id);


--
-- Name: verify_link_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY verify
    ADD CONSTRAINT verify_link_id_fkey FOREIGN KEY (link_id) REFERENCES customer_link(link_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

