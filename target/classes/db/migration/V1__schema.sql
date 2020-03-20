/*==============================================================*/
/* Table: ANALISE_CREDITO                                                */
/*==============================================================*/
create table ANALISE_CREDITO (
   ID                   INT8                 not null,
   data_analise         TIMESTAMP            not null,
   CPF                  VARCHAR(50)          not null,
   NOME                 VARCHAR(200)          not null,
   IDADE                INT8                 not null,
   SEXO                 CHAR(10)             not null,
   ESTADO_CIVIL         CHAR(10)             not null,
   DEPENDENTES          INT8                         ,
   RENDA                NUMERIC(12,8)        not null,
   STATUS               CHAR(10)             not null,
   LIMITE_MIN           NUMERIC(12,2)        null,
   LIMITE_MAX           NUMERIC(12,2)        null,
   constraint PK_ANALISE_CREDITO primary key (ID)
);

create sequence common_seq;