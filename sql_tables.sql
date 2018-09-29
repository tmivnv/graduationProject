create table DISH
(
  ID    INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_59279C7E_2619_4545_B05D_8112109C58A6)
    primary key,
  NAME  VARCHAR(100) not null,
  PRICE INTEGER      not null
);

create table RESTAURANT
(
  ID   INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_44A9C2D5_31EC_4D58_821D_E345084F2CE1)
    primary key,
  NAME VARCHAR(100) not null
);

create table MENU
(
  DISH_ID       INTEGER not null
    constraint FKSHVM96AMH1MP3F5KTQ6E0NI1N
    references DISH,
  RESTAURANT_ID INTEGER not null
    constraint FKHKRPPM8L4G60KV21XQ70SDIQH
    references RESTAURANT,
  primary key (DISH_ID, RESTAURANT_ID)
);

create table USER
(
  ID                INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_CA18C5EC_BFA1_494C_91C0_89C19D792939)
    primary key,
  NAME              VARCHAR(100),
  PASS_SALT         VARCHAR(255),
  PASS_HASH         VARCHAR(255),
  ROLE              VARCHAR(100),
  ALREADY_VOTED_FOR INTEGER default -1 not null,
  LATEST_VOTE       TIMESTAMP
);

