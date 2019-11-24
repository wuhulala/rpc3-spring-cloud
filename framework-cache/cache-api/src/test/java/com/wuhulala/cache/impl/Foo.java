package com.wuhulala.cache.impl;

import java.util.Objects;

public class Foo {
    private String name;

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foo foo = (Foo) o;
        return Objects.equals(name, foo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}