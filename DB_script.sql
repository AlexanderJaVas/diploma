PGDMP                         {            diploma    15.2    15.2 6    7           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            8           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            9           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            :           1262    17129    diploma    DATABASE     �   CREATE DATABASE diploma WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1251';
    DROP DATABASE diploma;
                postgres    false            �            1259    17131    category    TABLE     [   CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    17130    category_id_seq    SEQUENCE     �   CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public          postgres    false    215            ;           0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
          public          postgres    false    214            �            1259    17138    image    TABLE     {   CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    item_id integer NOT NULL
);
    DROP TABLE public.image;
       public         heap    postgres    false            �            1259    17137    image_id_seq    SEQUENCE     �   CREATE SEQUENCE public.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.image_id_seq;
       public          postgres    false    217            <           0    0    image_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;
          public          postgres    false    216            �            1259    17145    item    TABLE     |  CREATE TABLE public.item (
    id integer NOT NULL,
    date_time timestamp(6) without time zone,
    description text NOT NULL,
    price real NOT NULL,
    seller character varying(255) NOT NULL,
    title text NOT NULL,
    warehouse character varying(255) NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT item_price_check CHECK ((price >= (1)::double precision))
);
    DROP TABLE public.item;
       public         heap    postgres    false            �            1259    17155 	   item_cart    TABLE     g   CREATE TABLE public.item_cart (
    id integer NOT NULL,
    item_id integer,
    person_id integer
);
    DROP TABLE public.item_cart;
       public         heap    postgres    false            �            1259    17154    item_cart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.item_cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.item_cart_id_seq;
       public          postgres    false    221            =           0    0    item_cart_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.item_cart_id_seq OWNED BY public.item_cart.id;
          public          postgres    false    220            �            1259    17144    item_id_seq    SEQUENCE     �   CREATE SEQUENCE public.item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.item_id_seq;
       public          postgres    false    219            >           0    0    item_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.item_id_seq OWNED BY public.item.id;
          public          postgres    false    218            �            1259    17162    orders    TABLE       CREATE TABLE public.orders (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp(6) without time zone,
    number character varying(255),
    order_status smallint,
    price real NOT NULL,
    item_id integer NOT NULL,
    person_id integer NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    17161    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    223            ?           0    0    orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    222            �            1259    17169    person    TABLE     �   CREATE TABLE public.person (
    id integer NOT NULL,
    login character varying(100),
    password character varying(255),
    role character varying(255)
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    17168    person_id_seq    SEQUENCE     �   CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public          postgres    false    225            @           0    0    person_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;
          public          postgres    false    224            ~           2604    17134    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215                       2604    17141    image id    DEFAULT     d   ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
 7   ALTER TABLE public.image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    17148    item id    DEFAULT     b   ALTER TABLE ONLY public.item ALTER COLUMN id SET DEFAULT nextval('public.item_id_seq'::regclass);
 6   ALTER TABLE public.item ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    17158    item_cart id    DEFAULT     l   ALTER TABLE ONLY public.item_cart ALTER COLUMN id SET DEFAULT nextval('public.item_cart_id_seq'::regclass);
 ;   ALTER TABLE public.item_cart ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221            �           2604    17165 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    17172 	   person id    DEFAULT     f   ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);
 8   ALTER TABLE public.person ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224    225            *          0    17131    category 
   TABLE DATA           ,   COPY public.category (id, name) FROM stdin;
    public          postgres    false    215   �;       ,          0    17138    image 
   TABLE DATA           7   COPY public.image (id, file_name, item_id) FROM stdin;
    public          postgres    false    217   *<       .          0    17145    item 
   TABLE DATA           h   COPY public.item (id, date_time, description, price, seller, title, warehouse, category_id) FROM stdin;
    public          postgres    false    219   U@       0          0    17155 	   item_cart 
   TABLE DATA           ;   COPY public.item_cart (id, item_id, person_id) FROM stdin;
    public          postgres    false    221   T       2          0    17162    orders 
   TABLE DATA           g   COPY public.orders (id, count, date_time, number, order_status, price, item_id, person_id) FROM stdin;
    public          postgres    false    223   ,T       4          0    17169    person 
   TABLE DATA           ;   COPY public.person (id, login, password, role) FROM stdin;
    public          postgres    false    225   �T       A           0    0    category_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.category_id_seq', 4, true);
          public          postgres    false    214            B           0    0    image_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.image_id_seq', 95, true);
          public          postgres    false    216            C           0    0    item_cart_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.item_cart_id_seq', 6, true);
          public          postgres    false    220            D           0    0    item_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.item_id_seq', 20, true);
          public          postgres    false    218            E           0    0    orders_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.orders_id_seq', 4, true);
          public          postgres    false    222            F           0    0    person_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.person_id_seq', 3, true);
          public          postgres    false    224            �           2606    17136    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    215            �           2606    17143    image image_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.image DROP CONSTRAINT image_pkey;
       public            postgres    false    217            �           2606    17160    item_cart item_cart_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.item_cart
    ADD CONSTRAINT item_cart_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.item_cart DROP CONSTRAINT item_cart_pkey;
       public            postgres    false    221            �           2606    17153    item item_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.item DROP CONSTRAINT item_pkey;
       public            postgres    false    219            �           2606    17167    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    223            �           2606    17176    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    225            �           2606    17180 !   item uk_g8dxjf3vqh1fqb96wr5866idj 
   CONSTRAINT     ]   ALTER TABLE ONLY public.item
    ADD CONSTRAINT uk_g8dxjf3vqh1fqb96wr5866idj UNIQUE (title);
 K   ALTER TABLE ONLY public.item DROP CONSTRAINT uk_g8dxjf3vqh1fqb96wr5866idj;
       public            postgres    false    219            �           2606    17178 !   item uk_iax1mjwxsaxdu18mpjln1ihks 
   CONSTRAINT     c   ALTER TABLE ONLY public.item
    ADD CONSTRAINT uk_iax1mjwxsaxdu18mpjln1ihks UNIQUE (description);
 K   ALTER TABLE ONLY public.item DROP CONSTRAINT uk_iax1mjwxsaxdu18mpjln1ihks;
       public            postgres    false    219            �           2606    17206 "   orders fk1b0m4muwx1t377w9if3w6wwqn    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn FOREIGN KEY (person_id) REFERENCES public.person(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn;
       public          postgres    false    223    225    3220            �           2606    17201 !   orders fk1f1jovhxtx7fax217fq0gi17    FK CONSTRAINT        ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk1f1jovhxtx7fax217fq0gi17 FOREIGN KEY (item_id) REFERENCES public.item(id);
 K   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk1f1jovhxtx7fax217fq0gi17;
       public          postgres    false    223    3210    219            �           2606    17196 %   item_cart fk1q001oi9o3r5iojf7mx07rjp3    FK CONSTRAINT     �   ALTER TABLE ONLY public.item_cart
    ADD CONSTRAINT fk1q001oi9o3r5iojf7mx07rjp3 FOREIGN KEY (person_id) REFERENCES public.person(id);
 O   ALTER TABLE ONLY public.item_cart DROP CONSTRAINT fk1q001oi9o3r5iojf7mx07rjp3;
       public          postgres    false    221    225    3220            �           2606    17186     item fk2n9w8d0dp4bsfra9dcg0046l4    FK CONSTRAINT     �   ALTER TABLE ONLY public.item
    ADD CONSTRAINT fk2n9w8d0dp4bsfra9dcg0046l4 FOREIGN KEY (category_id) REFERENCES public.category(id);
 J   ALTER TABLE ONLY public.item DROP CONSTRAINT fk2n9w8d0dp4bsfra9dcg0046l4;
       public          postgres    false    219    3206    215            �           2606    17191 %   item_cart fkd39yafphc8qvcxvalvbi23ndm    FK CONSTRAINT     �   ALTER TABLE ONLY public.item_cart
    ADD CONSTRAINT fkd39yafphc8qvcxvalvbi23ndm FOREIGN KEY (item_id) REFERENCES public.item(id);
 O   ALTER TABLE ONLY public.item_cart DROP CONSTRAINT fkd39yafphc8qvcxvalvbi23ndm;
       public          postgres    false    221    219    3210            �           2606    17181 !   image fkscew1f5bnpn1nuaokhjg89u58    FK CONSTRAINT        ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkscew1f5bnpn1nuaokhjg89u58 FOREIGN KEY (item_id) REFERENCES public.item(id);
 K   ALTER TABLE ONLY public.image DROP CONSTRAINT fkscew1f5bnpn1nuaokhjg89u58;
       public          postgres    false    217    219    3210            *   a   x�=��	�0��}S8���8SQ�A=��"A]!�ȇo��%����7x<��Yk���t�

�D��"Nx)Vl�l9(�R,:�����*�>�      ,     x�=U˒$7<���	$@����c����V_�0=	$�����n�L��j���_���=�n��O����ߥ�����ZU�4��4N��ľd��#~� Ŧw��k鼕"�����)�x؏|Z���n��Eڮ҈VIr�N`*�~V���D%�~��'e�C6:�,���^@�e���y�vH7+ͽ����YE��KZ?��ᣩT��ҷwJ>Bw�5Ne���4 R��N��ب�hTЏ���'w=/i �l�{;��.Ё#i���ZE��/i �x�<�1Z��Y��h�����nI���{�R[������|G�į���Owp�)���V��F�Cc�qwψ�����2O��ס�;��k4�nj{�I5�I_� �R9�0w�i�2��*��[�'H���7��p.w6���0���"��n�U�Zt��l%�����X�KMOkӾ���[AGc��VX6�9�љ�ý���@�؎��tZ��PM��a���t���"<�����D(0Mh�T�/é7�V��d�xԤ�.#�k���Mm��������nC��$���t��mEbG��.�%�����;�X* ܚ:\k���ko ��zl��[Q1&�]4�����``�a�'��'���F��p���}��OpY�Q�m���pبv�U�雍a��4 :��F};�4�?�N�@Pg�'W����V̗u,���Aj�>��p�0<��K )Ѽ���Q�g�
X� �w�]A���N�\tP��)����m�E�_{`��l~��w��ػ�Y|��:H����ƺ8 ���K������f����Uz|�c1m1΄�'@O�� �.�D�/i ����������Y/�>���V�`�G�&�������J�����.*Q�]�Ƀ[�������@�������?�48<���2_�̮i��Ɵ�o"�w	���H�C+�j�@�Y�����y�[q��Ks��@���?���U�32A�|����ӨnD�������
�sA����9hL�j'q�1����4 �|>�� oa�-      .      x��[[sב~�~��C���!�+o�d�r,;�����T���6�(�KR�F R$m1V�JJq�8���![; 1����/䗤��>���+[*I�`έ/_�}�j�Z�V{���huL�����v�뛝���V��r��H��b^����S���"Y���"7�lN�bT��_Ч\?'�ޠ��1���b�<3ń^=)�eyJ/ѻ�"��e��bF����4���f���)^қ��c�����R�<����-Og�O��s������by�&~�G��b��2SZ$�]��#^D������x��fs�$7�|�ss����b>v�<=ho����9��Y�`�S�=1"�`�r�j�^���G;�	F�W��Q\\���Vt�Dd���z��+� �\`aZ�|�3���~�)�hhҔJ�xʌłE2�7��Z�.�HX4%��|B��N��|��\�7�����=�B�b-�9��X�O�5��i������14���c�3(M��Jd�҈��/XPk��,��mg�%a�8���3�ԁ����*���A�ɪ�T��N0<!�����6Wms%bz�<}��ER8ܙ��$�ϰ�3���M���ݮ��xp���񲵬�Q���8Sd����Z��%f�R�u�5[tkn�:�Vw���Ԋ/pXZ|i��h$	���.��ß������;����~d���>�7��OGw��G��zto߼i�?�7�9<��7~L/|rt�����{w�+��~ݴ6ۍF��mt���0/���y{��-
)O�*����ǿ6��4A2S��>&�eʮ��|8*;&&�����#��T�������cz���0dY�É��!,�w�VŪ{H�BFӱ��?m�� �f�[�1�+Y���\uT���a7�Ow?8�!+Hfgx�+��#m; ձv+�7�F��A�l��s���Y���M�s��m��![�����0��T"��~������%�4>�d^p(�-�Y��#��XBʴ�w�T�;����b�@�e}����J�#�T���CШb��j�jX�R�j��-@$�,�>�N8΅��n �@�0��as.�k)�����X�)oļ�Adn�E�&�?2oߊ̻��=z��;d��F��=�{��|��|���.}�=z���k��vd~�g��a���z���b<��s>�r�3�s�bq����g�,�H0u���/�z�AQ��"�ʖ�*;\�B˳�:���:�sJ��]��بHc�WB,�3���v��muk���������&������_��-��kYw @\���f�p�w��z{g��ެ��*����ß�9��>�+Q�u����-,��#ڭp��S���J>�@���Q$<�h��!�»���6g_����h� �4�s�N�h"������$���\���~zx���Gn�<��q���k�`�>�D�|6e�A�1����M�b@�;g���ˡ�\S��z~gb�!m%�O6g�\+rk\n=��$b�	�P�@��L�R����G���w>�w����Gw��&�hD�;���4�8	�� �S�(x����^�tY� �#��
���9����0Z��p5ǛY�~�Yؒ-i�r�f�Q��6t"�噌�7l lFc�o��6��'�D�6k!�<�R�����@.��	HcW�����S�l\ĢN�d�F��jN��բ�<x�҄�G�
3�e��SL��2b� i�l@���+�x�Z
T�H����$y��`����>�Y� X��Mp1*��nw{��fcj��7)����V���v����4�ڵ�W ���BI�Y(sy��	/��Ǜ�����nݿO���n��9L�gʠ�<�d��<��4�B��W���I2�]��ȞY�#@��vM�-ρ�_���q�,������_��K��y�z�g�ݗ�Wş���9p���Rɠ�����j|�F���|K��q�jhW&C4ֲ� ��{��)�\��N��k%4l�'�� �	eA�B���1���
��)S��N��e�֦F����t"tx����6�yE޼д�ih�L�N<\I��ë<���4$Y1���n*a�T-&�	׹Pk�j���ى&���	R���2=��[�f_y߆T6O _9�m)dG��E�fO�x/�J������%�`(&]�xA���d;�Xp�)�|�$��}!��Vu�>�$Ί��c�V�����P)|*���7��H�OtS�u��ä�9�t[��|�M�R�YOXG����3~\w���V�u�D�y_)#G�ӫ	#Wi�'4(�r!Fj���}�V�җc"+�����j�Z���7��'vw��84ʂք5?@�+@7*u0¹��4�\�\!�d��X��$!}8 4RҸ��	ʆ	reD��5+u���BǙ�1��J|��%���X�m!B������H;z!U�L�|� 8�@W�?y�F����\�s���a�e �(�7�7[m�����?���|���!�ө7���N�xIN2��1���}���|��;Gw�G͆y���������,ݙ:�5c�@�Jٴ�� m�K����#��ț[�Ǐj~��z"A\��ɞ�1���R�ҏu(|�D���)sRp)H���D�+5YW��mq���E�X��J�ѷ�O�Z��f���տ�.N��[��y���\M������D`{%� v�[���"�5��ڶ��\ ӾZ���Z�$=�zrm+�be�>C����C�����x���6��)R�kg8�D�kU��w�� �s�$����r(7�
�/�$8��1�]���̖��j�BC�����QC������5�F6)\_��sl�`��Ԧ�x�<
U]�g�h ������\�U�zj8��h.,H��{�RHE\��ԞbX�3�dyl*+���µ�icG`f��
��A�O|9S�,{��u�D�أpd���wv �b��+Y����Z[7�ӭ*��v;�����$BM��*���\ W��	,�(�U �#�b�g)����e�L�5LN�w�V>��#�+�)�"A���s��v.�O�m�Gt���J��ԑ�R*f��ܾX[?B��R]��p�Rʕ�&�j�F�N���3h,�s$��ؔt/�|� ���"�td6_h�-�Qo�D��:��K}|�^z;��g��B��O�����f<�!��)ٸd!E�$���r^��4� ��Ie;H��UJ�add�.�e�)讀%KHEL�B�
w�G��]M�֟4.F4����?3�oYcXL�J ��y!6�Q@���^�I�-H����+s� ׶�]F�����@��7��k�}��]ŋ�n�Uot;[�N�"�0k��:dps)�1�o�+~:�ؘ�@�zm�` ؉w݉��AK�&g�Ҥ�� ���M��q8~,/�M{��@��of9�)m�B��M��3���ȥ�lλV�.���SI�=�%/l~;��1��^qf1�ɿZ����G�+s,�z��4k��`����U�����n��78>CҀb!ǶrX.f^���H�Y��)�^'i�%B��_���iM�^_C��
�������TڽB��D����S�ys��S+�ŉ&
3k���4��@�Gt�O�¢/K��mQq�\
S��K: u���ԃ����}^�` �����$���V����<�͖��K��A�Oô'452��4I�\)g.X��ʮd�ͮ2��p�ɹ��.'�%�a�W�<l�ڴ��HzB��J��D�ݷl�HX�d�*b�$��e��ڤ*�7�8�h��@a-O�!�C�>Q�NFp{�v�Lj�+�!�	k��@�Wתp<�aFz��w�:�kZ�����J���y���wZy�Z��t< K_1[�-����HP�"Sy�(�h���V�8���qn3am�D����!�l�@�D��mU��5ۿ��J[[w���%�����a��&��EZ2�9V���G*7o�%�,W;���E�5/!D�܅���xƻe�Ł�A�%7S���'K-w���������z�D[vP� �  ��a�hG�,��!rKW]Q<��,��ٚ0�J����P�JR�8 �I�۳��l��y�Ho�jAh1�yf�>����KP��T���-O۪�VDW�E��B��y���H��	�̤7$-#�.�EXR����	D!M�,��0�G����'�3�Va/V~7�!�	Tf^Kl�V�ƌk�R��.�,��W���Ɓ��W�����(��j`5��]MW����/xb�^E�w�D��D������ֈ�%�ed��&��A,�H$$���J�z��<5����'{��=4�т�K7���D
+���J�5���	�^Ûz��}�A��D��"��J�Kb��E��,})�g��δl�K7~ ����!o%2�0z+�$w�8S>�����`�
��~%W���.Dp{H7��Z���j���-�|�I� pT*p��a٭�6���\:^��/�r��������]����Ҏ#_��lo�ބ���RPS�!�����6��x�k�ۧb���~���L��9�6ՋU�/�`<�����q92�];'���Nl���A��'aYR��;�D�	_ۋ-Zlc�Zqa6�e��G˞��1זui�@d��_ �,�bWeHQ,#��m#{�F��i����Պ�:�����ಘ��SK��i�w���?^���9J/>L���74(��ʂs����F�[	��k	������=�Ix�:���s�=�RQ���8�o�h�5F*�U�h%�8�B@ε�.պ3�$�����Աg�Edf�&o*Z�	�8h�>���	����~]�z���®'K=�r�i�~a��ά�l{���P��P�
�s
ws���-W�<�ڧ�[�}�X�Y���R��F�Vn$WH�}Qkv�횣5)���-��k���L_��Wk�j�WP���_�v����      0      x�3�44�4����� L�      2   O   x���� �7T�p�CDk�'�c�%$�֩��A��ڥ:�M��'�~V���c/�i�uߡ~�2�5�3��8�      4   �   x�M��N�0  �g�<��:��n �Pp�e�11t�-��M�K<p�������hf�G6�=K��u����Ren���yq��)X�܋ytc|��H�(x'>]��6zUJt�d���v}��$iv�[�C�h>:��(�����n>�6��� ����]�x��bׯ�@�U��|�9甸����u#:}�_+E)S�����׽A ��JA     