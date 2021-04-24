package org.gnu.escher.x11.enums;

public enum CoordinateMode {
    ORIGIN(0),
    PREVIOUS(1);
    
    private int code;
    
    CoordinateMode(int cd) {
        this.code =cd;
    }
    
    public int getCode() {
        return code;
    }
    
    public static CoordinateMode getOperation(int code) {
        return code == 0 ? CoordinateMode.ORIGIN : CoordinateMode.PREVIOUS;
    }
}