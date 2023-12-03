PGDMP      /                {            postgres    16.0    16.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    5    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    4796                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1259    16585    account    TABLE       CREATE TABLE public.account (
    user_name character varying NOT NULL,
    password character varying NOT NULL,
    created_date timestamp without time zone NOT NULL,
    updated_date timestamp without time zone NOT NULL,
    id integer NOT NULL,
    role integer NOT NULL
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    16600    account_seq    SEQUENCE     u   CREATE SEQUENCE public.account_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.account_seq;
       public          postgres    false            �            1259    16617    transaction    TABLE     �  CREATE TABLE public.transaction (
    user_name character varying NOT NULL,
    bank_name character varying NOT NULL,
    bank_number character varying NOT NULL,
    amount character varying NOT NULL,
    content character varying,
    created_date timestamp with time zone NOT NULL,
    updated_date timestamp without time zone NOT NULL,
    id integer NOT NULL,
    status integer NOT NULL,
    uuid character varying NOT NULL
);
    DROP TABLE public.transaction;
       public         heap    postgres    false            �            1259    16624    transaction_seq    SEQUENCE     y   CREATE SEQUENCE public.transaction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.transaction_seq;
       public          postgres    false            �          0    16585    account 
   TABLE DATA           \   COPY public.account (user_name, password, created_date, updated_date, id, role) FROM stdin;
    public          postgres    false    216          �          0    16617    transaction 
   TABLE DATA           �   COPY public.transaction (user_name, bank_name, bank_number, amount, content, created_date, updated_date, id, status, uuid) FROM stdin;
    public          postgres    false    218   %       �           0    0    account_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.account_seq', 201, true);
          public          postgres    false    217            �           0    0    transaction_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.transaction_seq', 401, true);
          public          postgres    false    219            !           2606    16610    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    216            #           2606    16623    transaction transaction_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_pkey;
       public            postgres    false    218            �     x�}λR�P���"-x�>�:�GA��dl�p5xz͌:V���o�i}l8c��Bb�5��W�[��u�W�����:ٽ"mp�6#2�e0>�N�c��"����� &�)�lR�#||�ᗷ���U��g�d5˟+�e͎V����0�$��\�V+�y��������De��K@��guW�c��]8,{�ݎ���)HK�5��l{?��)^ݟ6E���چe��������qP$�@jȊAU�ie3n'�w���/�dE      �   �   x�u�;n�0Dk���%��7>���H*l�����am$��4S����?���+`���J@	�y"q  � ]�&�GsR�����+Jʚ	0�R���],�k�MD�Zet�&@���?��v������y>�/�7��B���"�ȰѤ,��a�[�.�k�&�o�^�n޶���{�1��>�     