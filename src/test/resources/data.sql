INSERT INTO users (user_id, username, email, password, phone_number, date_of_birth, gender, created_by, created_at)
VALUES (1, 'testuser', 'testuser@example.com', 'password', '01012345678', '1990-01-01', 'M', 1, CURRENT_TIMESTAMP);

INSERT INTO posts (post_id, user_id, title, content, created_by, created_at)
VALUES (1, 1, '테스트 게시글', '게시글 내용입니다.', 1, CURRENT_TIMESTAMP);
