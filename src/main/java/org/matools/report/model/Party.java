package org.matools.report.model;

import java.util.HashMap;
import java.util.Map;

public class Party {

    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final Map<String, String> identifiers;


    private Party(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.identifiers = builder.identifiers;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, String> getIdentifiers() {
        return identifiers;
    }

    public String getIdentifier(String key) {
        return identifiers.get(key);
    }


    public static class Builder {
        private String name;
        private String email;
        private String phone;
        private String address;
        private Map<String, String> identifiers = new HashMap<>();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder identifier(String key, String value) {
            this.identifiers.put(key, value);
            return this;
        }

        public Party build() {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name required");
            }
            return new Party(this);
        }
    }
}