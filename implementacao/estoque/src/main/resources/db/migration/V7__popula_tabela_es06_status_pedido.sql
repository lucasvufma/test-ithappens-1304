INSERT INTO public.ES06_STATUS_PEDIDO (ES06_DESCRICAO) VALUES ('Ativo');
INSERT INTO public.ES06_STATUS_PEDIDO (ES06_DESCRICAO) VALUES ('Cancelado');
INSERT INTO public.ES06_STATUS_PEDIDO (ES06_DESCRICAO) VALUES ('Processado');

ALTER SEQUENCE public.ES06_STATUS_PEDIDO_SEQ RESTART WITH 4;