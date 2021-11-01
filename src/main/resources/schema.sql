DROP TABLE IF EXISTS LEMMA;

CREATE TABLE LEMMA
(id BIGINT not null auto_increment primary key,
rank_number INTEGER,
lemma VARCHAR(255),
pos VARCHAR(255),
freq DOUBLE
);

