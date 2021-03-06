
package com.khoslalabs.musicplayer.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class Results implements Serializable{

    @Expose
    private List<Collection1> collection1 = new ArrayList<Collection1>();

    /**
     * 
     * @return
     *     The collection1
     */
    public List<Collection1> getCollection1() {
        return collection1;
    }

    /**
     * 
     * @param collection1
     *     The collection1
     */
    public void setCollection1(List<Collection1> collection1) {
        this.collection1 = collection1;
    }

}
