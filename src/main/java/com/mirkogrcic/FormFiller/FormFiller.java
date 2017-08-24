package com.mirkogrcic.FormFiller;

import java.awt.Image;

public interface FormFiller<D> {
    void setData(D data);
    D getData();
    Image getImage();
}
