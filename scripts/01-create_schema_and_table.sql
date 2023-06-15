create schema if not exists test_schema;

create table if not exists test_schema.test_table (
    "test_id_col" INT NOT NULL,
    "test_col" VARCHAR(100),
    CONSTRAINT PK_TestTable PRIMARY KEY ("test_id_col")
);
