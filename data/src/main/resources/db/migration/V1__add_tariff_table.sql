CREATE TABLE tariffs (
    id                  UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name                VARCHAR(255) NOT NULL UNIQUE,
    interest_rate       DOUBLE PRECISION NOT NULL
)