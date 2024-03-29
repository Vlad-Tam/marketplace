<<<<<<<< HEAD:src/main/java/com/vladtam/jspapplication/models/Photo.java
package com.vladtam.jspapplication.models;

public class Photo {
    private Integer id;
    private byte[] imageData;

    public Photo(){}

    public Photo(Integer id, byte[] imageData) {
        this.id = id;
        this.imageData = imageData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
========
package com.vladtam.marketplace.models;

public class Photo {
    private Integer id;
    private byte[] imageData;

    public Photo(){}

    public Photo(Integer id, byte[] imageData) {
        this.id = id;
        this.imageData = imageData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
>>>>>>>> main:task2/src/main/java/com/vladtam/marketplace/models/Photo.java
