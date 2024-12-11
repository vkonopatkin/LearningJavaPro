CREATE TABLE public.user_limit (
	id bigserial NOT NULL,
	user_id integer NOT NULL,
	limit_value numeric(38, 2) NOT NULL
);

-- В принципе, этого можно не делать, запись для каждого клиента создастся при первом платеже клиента
INSERT INTO public.user_limit (user_id, limit_value) VALUES
	 (1, 10000.0),
	 (2, 10000.0),
	 (3, 10000.0),
	 (4, 10000.0),
	 (5, 10000.0),
	 (6, 10000.0),
	 (7, 10000.0),
	 (8, 10000.0),
	 (9, 10000.0),
	 (10, 10000.0);
