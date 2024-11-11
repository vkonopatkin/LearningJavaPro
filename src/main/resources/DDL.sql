CREATE TABLE public.vk_jp4_users (
	id bigserial NOT NULL,
	username varchar(255) NULL,
	CONSTRAINT vk_jp4_users_pk PRIMARY KEY (id),
	CONSTRAINT vk_jp4_users_un UNIQUE (username)
);