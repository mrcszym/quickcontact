CREATE TABLE IF NOT EXISTS MESSAGES (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(255),
    sticker_id BIGINT,
    CONSTRAINT fk_message_sticker
    FOREIGN KEY (sticker_id) REFERENCES STICKERS(id) ON DELETE SET NULL
);