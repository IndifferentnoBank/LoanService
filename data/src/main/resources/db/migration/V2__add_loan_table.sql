CREATE TABLE loans (
    id                  UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    tariff_id           UUID NOT NULL,
    bank_account_id     UUID NOT NULL,
    start_date          TIMESTAMP NOT NULL,
    end_date            TIMESTAMP NOT NULL,
    paid_sum            DOUBLE PRECISION NOT NULL,
    monthly_payment     DOUBLE PRECISION NOT NULL,
    debt                DOUBLE PRECISION NOT NULL,

    FOREIGN KEY (tariff_id) REFERENCES tariffs (id)
)