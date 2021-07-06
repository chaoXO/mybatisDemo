package org.demo.entity;

public class QueryParam {
    private Object p1;
    private Object p2;
    private Object p3;
    private Object p4;
    private Object p5;
    private Object p6;

    public QueryParam() {
    }

    public QueryParam(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
    }

    public Object getP1() {
        return p1;
    }

    public void setP1(Object p1) {
        this.p1 = p1;
    }

    public Object getP2() {
        return p2;
    }

    public void setP2(Object p2) {
        this.p2 = p2;
    }

    public Object getP3() {
        return p3;
    }

    public void setP3(Object p3) {
        this.p3 = p3;
    }

    public Object getP4() {
        return p4;
    }

    public void setP4(Object p4) {
        this.p4 = p4;
    }

    public Object getP5() {
        return p5;
    }

    public void setP5(Object p5) {
        this.p5 = p5;
    }

    public Object getP6() {
        return p6;
    }

    public void setP6(Object p6) {
        this.p6 = p6;
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                ", p5=" + p5 +
                ", p6=" + p6 +
                '}';
    }
}
