package eu.geoc.application;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */

@XmlRootElement
public class SOPPredictors {
    private Integer ex1;
    private Integer ex2;
    private Integer ex3;
    private Integer ex4;
    private Integer ex5;
    private Integer ex6;
    private Integer ex7;

    public SOPPredictors() {
    }

    public SOPPredictors(Integer ex1, Integer ex2, Integer ex3, Integer ex4, Integer ex5, Integer ex6, Integer ex7) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.ex3 = ex3;
        this.ex4 = ex4;
        this.ex5 = ex5;
        this.ex6 = ex6;
        this.ex7 = ex7;
    }

    public Integer getEx1() {
        return ex1;
    }

    public void setEx1(Integer ex1) {
        this.ex1 = ex1;
    }

    public Integer getEx2() {
        return ex2;
    }

    public void setEx2(Integer ex2) {
        this.ex2 = ex2;
    }

    public Integer getEx3() {
        return ex3;
    }

    public void setEx3(Integer ex3) {
        this.ex3 = ex3;
    }

    public Integer getEx4() {
        return ex4;
    }

    public void setEx4(Integer ex4) {
        this.ex4 = ex4;
    }

    public Integer getEx5() {
        return ex5;
    }

    public void setEx5(Integer ex5) {
        this.ex5 = ex5;
    }

    public Integer getEx6() {
        return ex6;
    }

    public void setEx6(Integer ex6) {
        this.ex6 = ex6;
    }

    public Integer getEx7() {
        return ex7;
    }

    public void setEx7(Integer ex7) {
        this.ex7 = ex7;
    }
}
