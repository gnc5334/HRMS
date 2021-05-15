

CREATE TABLE public.candidates
(
    id integer NOT NULL,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    identification_number character varying(11) COLLATE pg_catalog."default" NOT NULL,
    birth_date date NOT NULL,
    CONSTRAINT pk_candidates PRIMARY KEY (id),
    CONSTRAINT uc_candidates_identification_number UNIQUE (identification_number),
    CONSTRAINT fk_candidates_users FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)



CREATE TABLE public.employee_confirms
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    employee_id integer NOT NULL,
    is_confirmed boolean NOT NULL DEFAULT false,
    CONSTRAINT pk_employee_confirms PRIMARY KEY (id),
    CONSTRAINT fk_employee_confirms_employees FOREIGN KEY (employee_id)
        REFERENCES public.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)


CREATE TABLE public.employee_confirms_employers
(
    id integer NOT NULL,
    employer_id integer NOT NULL,
    CONSTRAINT pk_employee_confirms_employers PRIMARY KEY (id),
    CONSTRAINT fk_employee_confirms_employers_employee_confirms FOREIGN KEY (id)
        REFERENCES public.employee_confirms (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_employee_confirms_employers_employers FOREIGN KEY (employer_id)
        REFERENCES public.employers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)


CREATE TABLE public.employees
(
    id integer NOT NULL,
    first_name character varying(35) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(35) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (id),
    CONSTRAINT fk_employees_users FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

CREATE OR REPLACE FUNCTION public.validate_email_by_domain(
	integer,
	character varying)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
	DECLARE user_email_address varchar(320);
	DECLARE web_address varchar(255);
    DECLARE result boolean;
BEGIN
	SELECT email_address INTO user_email_address FROM users where id = $1;
    SELECT SUBSTRING(user_email_address,POSITION('@' in user_email_address) + 1) = $2 INTO result;
	IF result = false THEN
		raise 'E-mail and Web Address must be the same';
	END IF;
	RETURN result;
END;
$BODY$;



CREATE TABLE public.employers
(
    id integer NOT NULL,
    company_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    web_address character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(12) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_employers PRIMARY KEY (id),
    CONSTRAINT fk_employers_users FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT chk_employers_web_address CHECK (validate_email_by_domain(id, web_address))
)


CREATE TABLE public.job_titles
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_job_titles PRIMARY KEY (id),
    CONSTRAINT uc_job_titles_title UNIQUE (title)
)


CREATE TABLE public.users
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email_address character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(25) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_email_address UNIQUE (email_address)
)

CREATE TABLE public.verification_codes
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    code character varying(38) COLLATE pg_catalog."default" NOT NULL,
    is_verified boolean NOT NULL DEFAULT false,
    CONSTRAINT pk_verification_codes PRIMARY KEY (id),
    CONSTRAINT uc_verification_codes_is_verified UNIQUE (code)
)


CREATE TABLE public.verification_codes_candidates
(
    id integer NOT NULL,
    candidate_id integer NOT NULL,
    CONSTRAINT pk_verification_codes_candidates PRIMARY KEY (id),
    CONSTRAINT fk_verification_codes_candidates_candidates FOREIGN KEY (candidate_id)
        REFERENCES public.candidates (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_verification_codes_candidates_verification_codes FOREIGN KEY (id)
        REFERENCES public.verification_codes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)


CREATE TABLE public.verification_codes_employers
(
    id integer NOT NULL,
    employer_id integer NOT NULL,
    CONSTRAINT pk_verification_codes_employers PRIMARY KEY (id),
    CONSTRAINT fk_verification_codes_employers_employers FOREIGN KEY (employer_id)
        REFERENCES public.employers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_verification_codes_employers_verification_codes FOREIGN KEY (id)
        REFERENCES public.verification_codes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)