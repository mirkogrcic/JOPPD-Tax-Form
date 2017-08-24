package com.mirkogrcic.FormFiller.Elements;

import com.mirkogrcic.FormFiller.DrawPosition;

import java.awt.*;

public interface BoxElement extends Element {

    Rectangle getBox();
    void setBox(Rectangle box);

    DrawPosition getDrawPosition();
    void setDrawPosition(DrawPosition drawPosition);
}
