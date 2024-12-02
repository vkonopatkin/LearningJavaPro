CREATE TABLE public.vk_jp7_users (
	id bigserial NOT NULL,
	username varchar(255) NULL,
	CONSTRAINT vk_jp7_users_pk PRIMARY KEY (id),
	CONSTRAINT vk_jp7_users_un UNIQUE (username)
);


CREATE TABLE public.vk_jp7_products (
	id bigserial NOT NULL,
	productname varchar(255) NULL,
	accnumber varchar(255) NULL,
	balance numeric(38, 2) NULL,
	producttype varchar(20) NULL,
	userid int8 NULL,
	CONSTRAINT vk_jp7_products_pk PRIMARY KEY (id),
	CONSTRAINT vk_jp7_products_un UNIQUE (productname)
);

ALTER TABLE public.vk_jp7_products ADD CONSTRAINT fk_vk_jp7_product_user FOREIGN KEY (userid) REFERENCES public.vk_jp7_users(id);



INSERT INTO public.vk_jp7_users (id, username) VALUES
	 (1, 'Клиент 1'),
	 (2, 'Клиент 2'),
	 (3, 'Клиент 3'),
	 (4, 'Клиент 4'),
	 (5, 'Клиент 5');


INSERT INTO public.vk_jp7_products (id, productname,accnumber,balance,producttype,userid) VALUES
	 (1,  'Продукт 1','10001',1000.00,'ACCOUNT',1),
	 (2,  'Продукт 2','10002',2000.00,'ACCOUNT',1),
	 (3,  'Продукт 3',NULL,3000.00,'CARD',1),
	 (4,  'Продукт 4','10004',4000.00,'ACCOUNT',2),
	 (5,  'Продукт 5',NULL,5000.00,'CARD',3),
	 (6,  'Продукт 6',NULL,6000.00,'CARD',3),
	 (7,  'Продукт 7','10007',7000.00,'ACCOUNT',3),
	 (8,  'Продукт 8','10008',8000.00,'ACCOUNT',4),
	 (9,  'Продукт 9','10009',9000.00,'WRONG',4),
	 (10, 'Продукт 10',NULL,10000.00,'CARD',1);

commit;

