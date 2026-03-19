SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET search_path = public;
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP TABLE IF EXISTS public.comments CASCADE;
DROP TABLE IF EXISTS public.posts CASCADE;
DROP TABLE IF EXISTS public.users CASCADE;


CREATE TABLE public.users (
                              id BIGSERIAL PRIMARY KEY,
                              username character varying(40) NOT NULL,
                              password character varying(30) NOT NULL
);

CREATE TABLE public.posts (
                              id SERIAL PRIMARY KEY,
                              user_id bigint NOT NULL REFERENCES public.users(id) ON DELETE CASCADE,
                              content character varying(255) NOT NULL,
                              created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.comments (
                                 id SERIAL PRIMARY KEY,
                                 user_id bigint NOT NULL REFERENCES public.users(id) ON DELETE CASCADE,
                                 post_id integer NOT NULL REFERENCES public.posts(id) ON DELETE CASCADE,
                                 content character varying(255) NOT NULL,
                                 created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);