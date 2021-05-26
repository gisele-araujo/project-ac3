insert into genero
(nome)
values
('Musical'),
('Drama'),
('Fantasia'),
('Ficção cientifica'),
('Romance');

insert into filme
(nome, diretor, ano_lancamento, nacional, qtd_dias_locacao, preco_locacao, sinopse, genero_id)
values
('The Rocky Horror Picture Show', 'Jim Sharman', 1975, false, 3, 3.1,
'Os namorados Brad e Janet têm um pneu furado durante uma tempestade e descobrem a misteriosa mansão do louco cientista Dr. Frank-N-Furter, onde  tudo acontece',
 1),
 ('Coringa', 'Todd Phillips', 2019, false, 4, 4.9,
 'Arthur trabalha como palhaço nas ruas da cidade. Após ser ridicularizado por alguns homens, ele se descontrola e decide matá-los, mas acaba iniciando uma guerra contra a alta classe da região',
 2),
 ('Hércules', 'John Musker e Ron Clements', 1997, false, 3, 3.5,
 'O divino Hercules é privado de sua imortalidade e criado na Terra em vez de no Olimpo, onde é forçado a enfrentar Hades e muitos monstros.',
 3),
 ('Matrix', 'Lana Wachowski e Lilly Wachowski', 1999, false, 2, 2.8,
 'Matrix acompanha a aventura de Neo, um jovem hacker que é chamado para o movimento de resistência liderado por Morpheus, na luta contra a dominação dos humanos pelas máquinas',
 4),
 ('Central do Brasil', 'Walter Salles', 1999, true, 1, 1.8,
 'Dora trabalha escrevendo cartas para analfabetos na estação Central do Brasil, no Rio de Janeiro. A escrivã ajuda um menino, após sua mãe ser atropelada, a tentar encontrar o pai que nunca conheceu, no interior do Nordeste.',
 2);