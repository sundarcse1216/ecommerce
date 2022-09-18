CREATE SCHEMA "ecommerce";

CREATE TABLE "ecommerce"."users" (
  "id" SERIAL PRIMARY KEY,
  "user_name" varchar NOT NULL,
  "password" varchar NOT NULL,
  "first_name" varchar NOT NULL,
  "last_name" varchar NULL,
  "nick_name" varchar,
  "email" varchar UNIQUE NOT NULL,
  "mobile_no" varchar NOT NULL,
  "dob" date NOT NULL,
  "status" boolean NOT NULL,
  "created_at" timestamp DEFAULT (now()),
  "created_by" varchar,
  "modified_at" timestamp,
  "modified_by" varchar
);

CREATE TABLE "ecommerce"."products" (
  "id" int PRIMARY KEY,
  "name" varchar,
  "merchant_id" int NOT NULL,
  "price" int,
  "status" boolean,
  "created_at" datetime DEFAULT (now()),
  "created_by" varchar,
  "modified_at" datetime,
  "modified_by" varchar
);

CREATE TABLE "ecommerce"."inventories" (
  "id" int,
  "quantity" int,
  "status" boolean
);

CREATE TABLE "ecommerce"."categories" (
  "id" int,
  "name" varchar,
  "status" boolean,
  "created_at" datetime DEFAULT (now()),
  "created_by" varchar,
  "modified_at" datetime,
  "modified_by" varchar
);

CREATE TABLE "ecommerce"."sub_categories" (
  "id" int,
  "name" varchar,
  "status" boolean,
  "created_at" datetime DEFAULT (now()),
  "created_by" varchar,
  "modified_at" datetime,
  "modified_by" varchar
);

CREATE TABLE "ecommerce"."tags" (
  "id" int,
  "name" varchar,
  "description" varchar,
  "status" boolean,
  "created_at" datetime DEFAULT (now()),
  "created_by" varchar,
  "modified_at" datetime,
  "modified_by" varchar
);

CREATE TABLE "ecommerce"."posts" (
  "id" int,
  "comment" varchar,
  "status" boolean
);

CREATE TABLE "ecommerce"."orders" (
  "id" int,
  "quantity" int
);

CREATE TABLE "ecommerce"."payments" (
  "id" int,
  "amount" float8,
  "paid" float8,
  "status" boolean
);

ALTER TABLE "ecommerce"."orders" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."users" ("id");

ALTER TABLE "ecommerce"."categories" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."products" ("id");

ALTER TABLE "ecommerce"."sub_categories" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."categories" ("id");

ALTER TABLE "ecommerce"."tags" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."products" ("id");

ALTER TABLE "ecommerce"."inventories" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."products" ("id");

ALTER TABLE "ecommerce"."orders" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."payments" ("id");

ALTER TABLE "ecommerce"."products" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."orders" ("id");

ALTER TABLE "ecommerce"."posts" ADD FOREIGN KEY ("id") REFERENCES "ecommerce"."products" ("id");
