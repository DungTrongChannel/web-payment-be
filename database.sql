PGDMP  '    $                {            postgres    16.0    16.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    public          postgres    false    218   a       �           0    0    account_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.account_seq', 251, true);
          public          postgres    false    217            �           0    0    transaction_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.transaction_seq', 601, true);
          public          postgres    false    219            !           2606    16610    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    216            #           2606    16623    transaction transaction_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_pkey;
       public            postgres    false    218            �   @  x��αr�@@�Z������ca�NH�(�I��� "(|}�L4�L}�s�0;{����pz�Z�yn\��Vů���Ψ�Ν�����M�vR�G�~H��>�
�B�y&�O� =��7��y���EZ�dWv�2���L��>=/L+7���ޖ|�K��똩/�"+ )DB<%T�g���eE��Q�X؍k�Z�[���	cfj�u�G�"�O���� cGT������q`���B��$�=)�6��� ک+J��a��-=W3pJB��b�.u����֖�S����yb�˰>&
�2$Q*��O�^�D�}4�c      �   e  x����n\G�뫧P�ß�p��M��A��MĲ��E��9W��|%9�BP�b�%���w~�לi��͏[�TDطD�l?��o?���n�&٘X�O�=�-�˅l�D����^�w"���L��i�<J��,+'/�듗���f>�2>��z{����_�d�Ļ���	�U���z���Dtz�i�T�5Һ^�������	�U����8ISXU�5�jN�y�7?}�k��޿ſwcJ����������|��%ɞKP���0�Q�uc�&M�)�8EM��?I��l���_��轟�檈�0Zb��k�A}*%���5!w��,}�hTw:UȿEP8(D,�M	��4e�14q4d��R��N�O'W��	mi�jFIQ�~`�Xu�Q-����ۻ3�O�>%�ZX�Ŷ
��F甿x�sU/I�l�)m�i�-ńs�KլE�T)������`��Jg�rUE}��K)�4��f��]ҤXu��#���r�3���f��fpձ�^�M���X/,��.��(���5�uu;�A[_�Z�ÛtmA-Ij�ѣ�%�����b;1��*8�mmd����U{q�/���d�����y=�Q	�K��*�j��2�#&P������hV���c{�X��|WoL>�3`���!����k N��G���0�N�2��,/:�|��bg<���H"��:k*�6n��W.�����"�|�	V�_E̗U)���;���U�^}���^��/8��Y�	�	?%��
����c��8���xC3(�H��D�����o�cc3΢rFpU�q��5�H=��������t�~sO��t$=�]UAt$ ��z�l��C�G
q-1z����a�;�(�%_WAtd��P^��8�ښC�7I��,0�]����㈎))g�P�Cj��l��R��HL��m�V����{����;��0����4`���x�8/���V�Rx9���v�����b�� ?��U���0�=�b{@�	Y��^�yE����O1���R+�z��(���{}���VqЏ�MD�)�T����i���HHω?u��x��S'j.�#�ճ��<���#��/����߻�     