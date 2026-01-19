BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE SIGN (
                          ID NUMBER PRIMARY KEY,
                          ESCALATER_ID NUMBER,
                          STATUS VARCHAR2(20)
    )
    ';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE APPROVAL (
                            ID NUMBER PRIMARY KEY,
                            SIGN_ID NUMBER,
                            STATUS VARCHAR2(20)
    )
    ';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE REVIEW (
                              ID NUMBER PRIMARY KEY,
                              SIGN_ID NUMBER,
                              STATUS VARCHAR2(20)
    )
    ';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE STEP (
                              ID NUMBER PRIMARY KEY,
                              PROCESS_ID NUMBER,
                              DECISION_ID NUMBER,
                              STATUS VARCHAR2(20)
    )
    ';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        END IF;
END;
/