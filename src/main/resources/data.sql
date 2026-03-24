-- 1. ユーザーデータの登録
INSERT INTO users (name, password, role) VALUES ('Admin', 'a123', 'ADMIN');
INSERT INTO users (name, password, role) VALUES ('Alice', 'a123', 'ADMIN');
INSERT INTO users (name, password, role) VALUES ('Bob', 'b456', 'USER');
INSERT INTO users (name, password, role) VALUES ('Charlie', 'c789', 'USER');
INSERT INTO users (name, password, role) VALUES ('Taro', '', 'USER');

-- 2. 資産データの登録
INSERT INTO assets (name, status, location) VALUES ('MacBook Pro', 'LOANED', '東京本社');
INSERT INTO assets (name, status, location) VALUES ('iPad Air', 'LOANED', '大阪支社');
INSERT INTO assets (name, status, location) VALUES ('Dell Monitor 24inch', 'AVAILABLE', '東京本社');
INSERT INTO assets (name, status, location) VALUES ('iPhone 15', 'AVAILABLE', '東京本社');
INSERT INTO assets (name, status, location) VALUES ('Web Camera', 'LOANED', 'リモート');

-- 3. 貸出情報の登録
-- 期限内
INSERT INTO loans (asset_id, user_id, loan_date, period_days) 
VALUES (1, 1, '2026-03-20', 7);

-- 期限切れ（赤字テスト用）
INSERT INTO loans (asset_id, user_id, loan_date, period_days) 
VALUES (2, 2, '2026-03-01', 7);

-- 最近の貸出
INSERT INTO loans (asset_id, user_id, loan_date, period_days) 
VALUES (5, 3, '2026-03-21', 14);
