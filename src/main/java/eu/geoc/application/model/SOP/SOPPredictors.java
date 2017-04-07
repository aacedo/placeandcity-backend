package eu.geoc.application.model.SOP;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */

@XmlRootElement
public class SOPPredictors {
    private Integer na1;
    private Integer na2;
    private Integer na3;
    private Integer na4;

    public SOPPredictors() {
    }

    public SOPPredictors(Integer na1, Integer na2, Integer na3, Integer na4) {
        this.na1 = na1;
        this.na2 = na2;
        this.na3 = na3;
        this.na4 = na4;
    }

    public Integer getNa1() {
        return na1;
    }

    public void setNa1(Integer na1) {
        this.na1 = na1;
    }

    public Integer getNa2() {
        return na2;
    }

    public void setNa2(Integer na2) {
        this.na2 = na2;
    }

    public Integer getNa3() {
        return na3;
    }

    public void setNa3(Integer na3) {
        this.na3 = na3;
    }

    public Integer getNa4() {
        return na4;
    }

    public void setNa4(Integer na4) {
        this.na4 = na4;
    }

}
