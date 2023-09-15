CREATE TABLE IF NOT EXISTS users (
  id BIGINT NOT NULL PRIMARY KEY,
  username TEXT,
  email TEXT,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS categories (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 200000000) ,
  user_id BIGINT REFERENCES users(id),
  name TEXT,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  type TEXT,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS transactions (
  id BIGINT NOT NULL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id),
  category_id BIGINT REFERENCES categories(id),
  amount BIGINT,
  transaction_date DATE NOT NULL DEFAULT CURRENT_DATE,
  note TEXT,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);