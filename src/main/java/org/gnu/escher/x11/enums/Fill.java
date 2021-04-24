package org.gnu.escher.x11.enums;

public enum Fill {
    COMPLEX(0),
    NONCONVEX(1),
    CONVEX(2);
    
    private int code;
    
    Fill(int cd) {
        this.code =cd;
    }
    
    public int getCode() {
        return code;
    }
    
    public static Fill getFamily(int code) {
        switch (code)
        {
            case 0: return Fill.COMPLEX;
            case 1: return Fill.NONCONVEX;
            case 2: return Fill.CONVEX;
            
            default: return Fill.COMPLEX;
        }
    }
}