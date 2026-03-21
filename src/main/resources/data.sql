-- 1. ユーザーデータの登録
INSERT INTO "users" (name, password) VALUES ('Alice', 'a123');
INSERT INTO "users" (name, password) VALUES ('Bob', 'b456');
INSERT INTO "users" (name, password) VALUES ('Charlie', 'c789');

-- 2. 資産データの登録
-- 初期状態は AVAILABLE (利用可能) にしておきます
INSERT INTO "assets" (name, status) VALUES ('MacBook Pro', 'LOANED');
INSERT INTO "assets" (name, status) VALUES ('iPad Air', 'LOANED');
INSERT INTO "assets" (name, status) VALUES ('Dell Monitor 24inch', 'AVAILABLE');
INSERT INTO "assets" (name, status) VALUES ('iPhone 15', 'AVAILABLE');
INSERT INTO "assets" (name, status) VALUES ('Web Camera', 'LOANED');

-- 3. 貸出情報の登録 (ここが重要！)
-- ID 1: 期限内の貸出 (AliceがMacBookを借りている)
INSERT INTO "loans" (asset_id, user_id, loan_date, period_days) 
VALUES (1, 1, '2026-03-20', 7);

-- ID 2: 期限切れの貸出 (BobがiPadを借りている - 赤字テスト用)
-- 2026-03-01から7日間なので、現在は期限切れになるはずです
INSERT INTO "loans" (asset_id, user_id, loan_date, period_days) 
VALUES (2, 2, '2026-03-01', 7);

-- ID 3: 今日貸し出したもの (CharlieがWebカメラを借りている)
INSERT INTO "loans" (asset_id, user_id, loan_date, period_days) 
VALUES (5, 3, '2026-03-22', 14);
