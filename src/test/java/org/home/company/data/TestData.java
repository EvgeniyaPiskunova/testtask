package org.home.company.data;

public enum TestData {
    INVALID_PHONE_NUMBER("1234567890"),
    VALID_PASSWORD("1234567890"),
    BASE_URL("http://tl.af-ctf.ru/#inputForAuth"),
    VALID_NAME("Evgeniya"),
    VALID_EMAIL("eapiskunova@gmail.com");

    TestData(String val) {
        this.val = val;
    }

    public final String val;
}
