CREATE TABLE public.vk_jp4_users (
	id bigserial NOT NULL,
	username varchar(255) NULL,
	CONSTRAINT vk_jp4_users_pk PRIMARY KEY (id),
	CONSTRAINT vk_jp4_users_un UNIQUE (username)
);


CREATE TABLE public.vk_jp5_products (
	id bigserial NOT NULL,
	productname varchar(255) NULL,
	accnumber varchar(255) NULL,
	balance numeric(38, 2) NULL,
	producttype varchar(20) NULL,
	userid int8 NULL,
	CONSTRAINT vk_jp5_products_pk PRIMARY KEY (id),
	CONSTRAINT vk_jp5_products_un UNIQUE (productname)
);

ALTER TABLE public.vk_jp5_products ADD CONSTRAINT fk_vk_jp5_product_user FOREIGN KEY (userid) REFERENCES public.vk_jp5_users(id);
