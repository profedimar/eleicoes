--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5rc1
-- Dumped by pg_dump version 9.5rc1

-- Started on 2018-06-25 16:39:49

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 188 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 188
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 180 (class 1259 OID 16458)
-- Name: bens_candidato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE bens_candidato (
    codigo integer NOT NULL,
    sigla_uf_candidato character(2) NOT NULL,
    nr_sequencial_candidato bigint NOT NULL,
    cod_tipo_bem integer NOT NULL,
    descricao_bem character varying(80) NOT NULL,
    valor_bem numeric(15,2) NOT NULL
);


ALTER TABLE bens_candidato OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16461)
-- Name: bens_candidato_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bens_candidato_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bens_candidato_codigo_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 181
-- Name: bens_candidato_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bens_candidato_codigo_seq OWNED BY bens_candidato.codigo;


--
-- TOC entry 182 (class 1259 OID 16463)
-- Name: candidato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE candidato (
    sigla_uf character(2) NOT NULL,
    nr_sequencial bigint NOT NULL,
    cpf numeric(11,0) NOT NULL,
    nome character varying(80) NOT NULL,
    nome_urna character varying(80) NOT NULL,
    data_nasc date NOT NULL,
    cod_cargo integer NOT NULL,
    cod_municipio integer NOT NULL,
    nr_partido integer NOT NULL,
    cod_sexo integer NOT NULL
);


ALTER TABLE candidato OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16466)
-- Name: cargo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cargo (
    codigo integer NOT NULL,
    descricao character varying(80) NOT NULL
);


ALTER TABLE cargo OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16469)
-- Name: municipio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE municipio (
    codigo integer NOT NULL,
    nome character varying(80) NOT NULL,
    uf character(2) NOT NULL
);


ALTER TABLE municipio OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16472)
-- Name: partido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE partido (
    numero integer NOT NULL,
    nome character varying(80) NOT NULL,
    sigla character varying(10) NOT NULL
);


ALTER TABLE partido OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16475)
-- Name: sexo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sexo (
    codigo integer NOT NULL,
    descricao character varying(80) NOT NULL
);


ALTER TABLE sexo OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16478)
-- Name: tipo_bem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_bem (
    codigo integer NOT NULL,
    descricao character varying(80) NOT NULL
);


ALTER TABLE tipo_bem OWNER TO postgres;

--
-- TOC entry 2005 (class 2604 OID 16481)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bens_candidato ALTER COLUMN codigo SET DEFAULT nextval('bens_candidato_codigo_seq'::regclass);


--
-- TOC entry 2007 (class 2606 OID 16483)
-- Name: pk_bens_candidato; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bens_candidato
    ADD CONSTRAINT pk_bens_candidato PRIMARY KEY (codigo);


--
-- TOC entry 2009 (class 2606 OID 16485)
-- Name: pk_candidato; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT pk_candidato PRIMARY KEY (nr_sequencial, sigla_uf);


--
-- TOC entry 2011 (class 2606 OID 16487)
-- Name: pk_cargo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cargo
    ADD CONSTRAINT pk_cargo PRIMARY KEY (codigo);


--
-- TOC entry 2013 (class 2606 OID 16489)
-- Name: pk_municipio; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY municipio
    ADD CONSTRAINT pk_municipio PRIMARY KEY (codigo);


--
-- TOC entry 2015 (class 2606 OID 16491)
-- Name: pk_partido; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY partido
    ADD CONSTRAINT pk_partido PRIMARY KEY (numero);


--
-- TOC entry 2017 (class 2606 OID 16493)
-- Name: pk_sexo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sexo
    ADD CONSTRAINT pk_sexo PRIMARY KEY (codigo);


--
-- TOC entry 2019 (class 2606 OID 16495)
-- Name: pk_tipo_bem; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_bem
    ADD CONSTRAINT pk_tipo_bem PRIMARY KEY (codigo);


--
-- TOC entry 2020 (class 2606 OID 16496)
-- Name: fk_bc_candidato; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bens_candidato
    ADD CONSTRAINT fk_bc_candidato FOREIGN KEY (sigla_uf_candidato, nr_sequencial_candidato) REFERENCES candidato(sigla_uf, nr_sequencial);


--
-- TOC entry 2021 (class 2606 OID 16501)
-- Name: fk_bc_tipo_bem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bens_candidato
    ADD CONSTRAINT fk_bc_tipo_bem FOREIGN KEY (cod_tipo_bem) REFERENCES tipo_bem(codigo);


--
-- TOC entry 2022 (class 2606 OID 16506)
-- Name: fk_candidato_cargo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT fk_candidato_cargo FOREIGN KEY (cod_cargo) REFERENCES cargo(codigo);


--
-- TOC entry 2023 (class 2606 OID 16511)
-- Name: fk_candidato_municipio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT fk_candidato_municipio FOREIGN KEY (cod_municipio) REFERENCES municipio(codigo);


--
-- TOC entry 2024 (class 2606 OID 16516)
-- Name: fk_candidato_partido; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT fk_candidato_partido FOREIGN KEY (nr_partido) REFERENCES partido(numero);


--
-- TOC entry 2025 (class 2606 OID 16521)
-- Name: fk_candidato_sexo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidato
    ADD CONSTRAINT fk_candidato_sexo FOREIGN KEY (cod_sexo) REFERENCES sexo(codigo);


--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-06-25 16:39:51

--
-- PostgreSQL database dump complete
--

