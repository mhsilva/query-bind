package com.tr.query.bind.querybind.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genericCollection")
public class GenericCollection {

    @Id
    private String id;

    private String nome;

    private int count;

    /**
     * Gets the attribute id.
     *
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the attribute id.
     *
     * @param id to set the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the attribute nome.
     *
     * @return nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the attribute nome.
     *
     * @param nome to set the nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the attribute count.
     *
     * @return count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the attribute count.
     *
     * @param count to set the count.
     */
    public void setCount(int count) {
        this.count = count;
    }
}
