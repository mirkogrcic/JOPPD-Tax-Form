package com.mirkogrcic.FormFiller;


import com.mirkogrcic.FormFiller.Data.JOPPD2Data;
import com.mirkogrcic.FormFiller.Elements.TextElement;
import com.mirkogrcic.utils.Util;

public class JOPPD2 extends FormFillerBase<JOPPD2Data>{

    // Constructor
    public JOPPD2(JOPPD2Data data){
        super(data);

        // Read image and initialize
        image = Util.getImageResource("/images/Obrazac_JOPPD_2.png");
    }
}
